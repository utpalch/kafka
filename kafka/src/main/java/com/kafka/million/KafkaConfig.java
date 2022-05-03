package com.kafka.million;

public final class KafkaConfig {
    public final static String applicationID = "ChandaFamilyProducer";
    public final static String bootstrapServers = "localhost:9092";
    public final static String topicName = "chandafamily";
    public final static int numEvents = 1000000;

    //USED FOR TRANSACTIONS
    public final static String TRAN_TOPIC_NAME_1 = "chandafamily-1";
    public final static String TRAN_TOPIC_NAME_2 = "chandafamily-2";
    public final static String TRANSACTION_ID = "chandafamily-producer-tans";

}
