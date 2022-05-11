package com.work.kafka.consumer;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ConsumerServiceTest {

    @Test
    public void testGetPartitions() {
        ConsumerService myUnit = new ConsumerService();
        List<Integer> result = myUnit.getPartitions();
        Assert.assertEquals(1, result.get(0));

    }

    @Test
    public void testGetTopicName() {
        ConsumerService myUnit = new ConsumerService();
        Assert.assertEquals("exactlyOnce", myUnit.getTopicName());

    }
}