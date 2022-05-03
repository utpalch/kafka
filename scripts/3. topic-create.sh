#Create TOPIC:
# --zookeeper is deprecated, use --bootstrap-server instead
kafka-topics --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic chandafamily