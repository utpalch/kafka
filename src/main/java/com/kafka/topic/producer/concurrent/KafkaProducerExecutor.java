package com.kafka.topic.producer.concurrent;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class KafkaProducerExecutor implements Callable<TopicEntity> {

    private static final Logger LOGGER = LogManager.getLogger();
    private TopicEntity entity;

    public KafkaProducerExecutor(TopicEntity entity) {
        this.entity = entity;
    }

    @Override
    public TopicEntity call() {

        long start = System.currentTimeMillis();
        LOGGER.info("Creating kafka concurrent producer");
        try {
            File file = new File(entity.getFile());

            Scanner scanner = new Scanner(file);
            LOGGER.info("Start sending  messages");
            int i = 0;
            while (scanner.hasNext()) {
                entity.getProducer().send(new ProducerRecord<>(entity.getTopic(), i++, scanner.nextLine()));
            }
            LOGGER.info("Completed sending  messages in " + (System.currentTimeMillis() - start) + " in milli second.");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            entity.getProducer().close();
        }
        return entity;
    }
}
