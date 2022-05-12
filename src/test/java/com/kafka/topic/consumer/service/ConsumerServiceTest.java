package com.kafka.topic.consumer.service;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.kafka.topic.consumer.util.ConsumerConfigUtils;

import java.util.List;

public class ConsumerServiceTest {

    @Test
    public void testGetPartitions() {
        ConsumerService instance = new ConsumerService();
        List<Integer> result = instance.getPartitions();
        Assert.assertEquals(1, result.get(0));

    }

    @Test
    public void testGetTopicName() {
        ConsumerService instance = new ConsumerService();
        Assert.assertEquals("exactlyOnce", instance.getTopicName());

    }

    @Test
    public void testProperty() {
        ConsumerService instance = new ConsumerService();
        Assert.assertEquals("localhost:9092,localhost:9093",
                instance.getProperty(ConsumerConfigUtils.BOOTSTRAP_SERVERS));
        Assert.assertEquals("org.apache.kafka.common.serialization.StringDeserializer",
                instance.getProperty(ConsumerConfigUtils.VALUE_DESERIALIZER));
        Assert.assertEquals("org.apache.kafka.common.serialization.StringDeserializer",
                instance.getProperty(ConsumerConfigUtils.KEY_DESERIALIZER));

        Assert.assertEquals("false",
                instance.getProperty(ConsumerConfigUtils.ENABLE_AUTO_COMMIT));
    }
}