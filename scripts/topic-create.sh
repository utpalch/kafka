#kafka-topics.bat --create --zookeeper localhost:2181 --topic hello-producer-topic --partitions 5 --replication-factor 3
#kafka-topics --create --bootstrap-server localhost:2181 --replication-factor 3 --partitions 5 --topic chandafamily-topic
kafka-topics --create --bootstrap-server localhost:2181 --replication-factor 1 --partitions 1 --topic chandafamily-topic

