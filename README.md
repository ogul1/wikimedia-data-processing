# wikimedia-data-processing

Processing a stream of Wikimedia data using Apache Kafka and Spring.

```
bin/kafka-console-consumer.sh --topic wikimedia_recent_changes --from-beginning --bootstrap-server localhost:9092
./gradlew jar -PscalaVersion=2.13.10
bin/zookeeper-server-start.sh config/zookeeper.properties
bin/kafka-server-start.sh config/server.properties 
```
