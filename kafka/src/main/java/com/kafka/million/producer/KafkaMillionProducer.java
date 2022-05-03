package com.kafka.million.producer;

import com.kafka.million.KafkaConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.Properties;

public class KafkaMillionProducer {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        LOGGER.info("Creating kafka million producer");


        KafkaProducer<Integer , String> producer = new KafkaProducer<Integer, String>(getKafkaProperties());
        LOGGER.info("Start sending  messages");

        for (int i = 0 ; i< KafkaConfig.numEvents; i++) {
            producer.send(new ProducerRecord<>(KafkaConfig.topicName , i , "Message " + i));
        }
        LOGGER.info("Completed sending  messages in "+ (System.currentTimeMillis() - start) + " in milli second.");

        producer.close();
    }
    private static Properties getKafkaProperties(){
        Properties properties = new Properties();
        properties.put(ProducerConfig.CLIENT_ID_CONFIG , KafkaConfig.applicationID);//source of message
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG , KafkaConfig.bootstrapServers);//broker server config
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());//
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());//
        return properties;
    }
}
