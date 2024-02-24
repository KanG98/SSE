Safe sx express (SSE)

安捷达

Kafka: 
 - start zookeeper: 
   - `bin/zookeeper-server-start.sh config/zookeeper.properties`
 - start kafka broker:
   - `bin/kafka-server-start.sh config/server.properties`
 - create topic:
   - `bin/kafka-topics.sh --bootstrap-server localhost:9092 --replication-factor 1 --partitions 3 --create --topic sse-create-orders-service`
 - list topics:
   - `bin/kafka-topics.sh --bootstrap-server localhost:9092 --list`

Run service-discovery:
 - `cd service-discovery`
 - `mvn spring-boot:run`

Run service-order:
 - `cd service-order`
 - `mvn spring-boot:run`

Run location-service:
 - `chmod +x SSE-JS/services/location-service.sh`
 - `./SSE-JS/services/location-service.sh`

Run all services in docker:
 - `docker pull yankang198/service-discovery-server:latest`
 - `docker run --network my-network --name discovery-server -p 8761:8761 --rm yankang198/service-discovery-server`
 - `docker pull yankang198/service-auth:latest`
 - `docker run --network my-network -v /Users/kylexue/sse/secret:/Users/kylexue/sse/secret --name auth-server -p 50231:50231 --rm yankang198/service-auth`
