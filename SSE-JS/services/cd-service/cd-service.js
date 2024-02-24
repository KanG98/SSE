const express = require('express');
const bodyParser = require('body-parser');
const Docker = require('dockerode');
const docker = new Docker();

const app = express();
app.use(bodyParser.json());

app.post('/cd/webhook', (req, res) => {
    console.log(req)
//    const imageName = 'yankang198/service-auth:latest';  // Replace with your image name
//
//    // Pull the latest image
//    docker.pull(imageName, (err) => {
//        if (err) {
//            console.error(`Failed to pull ${imageName}: ${err}`);
//            return;
//        }
//
//        // Stop and remove the old container
//        docker.listContainers((err, containers) => {
//            containers.forEach((containerInfo) => {
//                if (containerInfo.Image === imageName) {
//                    const container = docker.getContainer(containerInfo.Id);
//                    container.stop((err) => {
//                        if (err) {
//                            console.error(`Failed to stop container: ${err}`);
//                            return;
//                        }
//                        container.remove();
//                    });
//                }
//            });
//        });
//
//        // Start a new container with the latest image
//        docker.createContainer({
//            Image: imageName,
//            name: 'my-container',  // Replace with your container name
//            // Add your container options here
//        }).then((container) => {
//            return container.start();
//        }).catch((err) => {
//            console.error(`Failed to start container: ${err}`);
//        });
//    });

    res.sendStatus(200);
});

app.listen(1111, () => {
    console.log('Server is listening on port 1111');
});