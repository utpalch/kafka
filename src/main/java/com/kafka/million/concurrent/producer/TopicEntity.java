package com.kafka.million.concurrent.producer;

import org.apache.kafka.clients.producer.KafkaProducer;

public class TopicEntity {
    private KafkaProducer<Integer, String> producer;
    private String topic;
    private String file;

    public KafkaProducer<Integer, String> getProducer() {
        return producer;
    }

    public void setProducer(KafkaProducer<Integer, String> producer) {
        this.producer = producer;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
