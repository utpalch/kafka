package com.kafka.topic.producer.transaction;

import com.kafka.topic.producer.utils.KafkaConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class KafkaTransactionProducerClient {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        LOGGER.info("Starting  KafkaTransaction ProducerClient ");

        KafkaProducer<Integer , String> producer = new KafkaProducer<Integer, String>(getKafkaProperties());

        producer.initTransactions();
        producer.beginTransaction();
        try {
            LOGGER.info("Begining  KafkaTransaction ");

            for (int i = 0; i < 2; i++) {
                producer.send(new ProducerRecord<>(KafkaConfig.TRAN_TOPIC_NAME_1 , i , "simple message topic 1 =>" + 1));
                producer.send(new ProducerRecord<>(KafkaConfig.TRAN_TOPIC_NAME_2 , i , "simple message topic 2 =>" + 1));
            }
            producer.commitTransaction();
            LOGGER.info("Comitting  KafkaTransaction ");

        }catch (Exception ex){
            producer.abortTransaction();
            ex.printStackTrace();
        }finally {
            producer.close();
            LOGGER.info("Complete  KafkaTransaction ProducerClient ");
        }
    }

    private static Properties getKafkaProperties(){
        Properties properties = new Properties();
        properties.put(ProducerConfig.CLIENT_ID_CONFIG , KafkaConfig.applicationID);//source of message
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG , KafkaConfig.bootstrapServers);//broker server config
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());//
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());//
        properties.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG , KafkaConfig.TRANSACTION_ID);

        return properties;
    }
}
