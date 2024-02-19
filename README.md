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