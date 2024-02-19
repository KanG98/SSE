const WebSocket = require('ws');
const http = require('http');

const server = http.createServer((req, res) => {
  res.writeHead(200, { 'Content-Type': 'text/plain' });
  res.end('WebSocket server running on port 9090\n');
});

const wss = new WebSocket.Server({ noServer: true });

const redis = require('redis');

let redisPort = 6379;  // Replace with your redis port
//let redisHost = "127.0.0.1";  // Replace with your redis host
let redisHost = "my-redis";  // redis host in docker network

// Create a Redis client
const redisClient = redis.createClient({
  socket: {
    port: redisPort,
    host: redisHost,
  }
});

(async () => {
  // Connect to redis server
  await redisClient.connect();
})();

function redisSetKey(pathname, latestLocation) {
  var dmId = pathname.replace("dm/", "")
  redisClient.set(dmId, latestLocation, (err, reply) => {
    if (err) {
      console.error(err);
    } else {
      console.log('Key set in Redis:', reply);
    }
  });
}

wss.on('connection', (ws) => {
  console.log(`Client connected to channel: ${ws.channel}`);

  // Periodically send messages based on the channel
  const interval = setInterval(async () => {
    const dmId = ws.channel.replace("dm/", "")
    console.log(dmId)
    const latestLocation = await redisClient.get(dmId);
    if(latestLocation) {
      ws.send(latestLocation)
    }
  }, 1000);

  // Handle messages from the client
  ws.on('message', (latestLocationFromMessage) => {
    if(ws.channel.includes("dm/")) {
      // only delivery man can change the location 
      redisSetKey(ws.channel, latestLocationFromMessage)
    }
    console.log(`Received message: ${latestLocationFromMessage}`);
  });

  // Handle client disconnection
  ws.on('close', () => {
    console.log(`Client disconnected from channel: ${ws.channel}`);
    clearInterval(interval); // Stop sending messages when the client disconnects
  });
});

server.on('upgrade', (request, socket, head) => {
  const pathname = new URL(request.url, `http://${request.headers.host}`).pathname;
  wss.handleUpgrade(request, socket, head, (ws) => {
    ws.channel = pathname.slice(1)
    wss.emit('connection', ws, request);
  });
});

server.listen(9090, () => {
  console.log('WebSocket server listening on port 9090');
});
