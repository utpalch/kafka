package com.kafka.topic.producer.concurrent;

import com.kafka.topic.producer.utils.KafkaConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class KafkaProducerClient {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        LOGGER.info("Starting  KafkaProducerClient ");

        ThreadPoolExecutor executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(2);
        KafkaProducer<Integer , String> producer = new KafkaProducer<Integer, String>(getKafkaProperties());

        List<Future<TopicEntity>> resultList = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            TopicEntity entity = new TopicEntity();
            entity.setProducer(producer);
            entity.setTopic(KafkaConfig.topicName);
            entity.setFile("data/FILE_"+(i+1)+".csv");

            KafkaProducerExecutor producerExecutor  = new KafkaProducerExecutor(entity);
            Future<TopicEntity> results  = executor.submit(producerExecutor);
        }

        for (Future<TopicEntity> future: resultList) {
            TopicEntity currentEntity = future.get();

            LOGGER.info("Future result for " +currentEntity.getFile() + " and task status is " + future.isDone());
        }
        LOGGER.info("Complete KafkaProducerClient ");

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
