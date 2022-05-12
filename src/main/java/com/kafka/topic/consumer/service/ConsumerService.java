package com.kafka.topic.consumer.service;

import com.kafka.topic.consumer.model.ConsumerDTO;
import com.kafka.topic.consumer.dao.OffSetDAO;
import com.kafka.topic.consumer.util.ConsumerConfigUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class ConsumerService {

    private OffSetDAO offSetDAO;
    private Properties topicProperties = new Properties();

    public ConsumerService(){
        offSetDAO = new OffSetDAO();
        loadProperties();
    }

    /**
     * System performs below steps
     *  a) System externalize the offset and manages using persistence storage.
     *  b) {@link org.apache.kafka.clients.consumer.Consumer} seeks offset from DB for a given partition.
     *  c) After fetching recprds from topic. System update offset and records within transactions.
     * @throws Exception
     */
    public void consumeRequest()throws Exception{
        int recordCount = 0;
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(getConsumerProperties());

        List<Integer> partitionIds = getPartitions();

        List<TopicPartition> topics = prepareTopicPartitions(partitionIds);
        consumer.assign(topics);

        for (TopicPartition partition : topics) {
            consumer.seek(partition ,offSetDAO.getOffsetFromDB(partition.topic() , partition.partition()));
        }

        try {
            do {
                ConsumerRecords<String, String> records = consumer.poll(1000);
                System.out.println("Record polled " + records.count());
                recordCount = records.count();
                for (ConsumerRecord<String, String> record : records) {
                    ConsumerDTO consumerDTO = new ConsumerDTO();
                    consumerDTO.setTopic(record.topic());
                    consumerDTO.setPartition(record.partition());
                    consumerDTO.setOffset(record.offset()+1);
                    consumerDTO.setRecordKey(record.key());
                    consumerDTO.setRecordvalue(record.value());

                    offSetDAO.updateOffsetAndRequest(consumerDTO);
                }
            } while (recordCount > 0);

        } catch (Exception ex) {
            System.out.println("Exception in main.");
        } finally {
            consumer.close();
        }
    }

    /**
     * Get the consumer properties, populates into {@link Properties} and returns.
     * @return The properties {@link Properties}.
     */
    private Properties getConsumerProperties(){
        Properties props = new Properties();
        props.put("bootstrap.servers", getProperty(ConsumerConfigUtils.BOOTSTRAP_SERVERS));
        props.put("key.deserializer", getProperty(ConsumerConfigUtils.KEY_DESERIALIZER));
        props.put("value.deserializer", getProperty(ConsumerConfigUtils.VALUE_DESERIALIZER));
        props.put("enable.auto.commit", getProperty(ConsumerConfigUtils.ENABLE_AUTO_COMMIT));

        return props;
    }

    /**
     * Prepare {@link TopicPartition} for the given partition(s) and topic name and return the list.
     * @param partitionIds The list of partition name.
     * @return The list for {@link TopicPartition}.
     */
    protected List<TopicPartition> prepareTopicPartitions(List<Integer> partitionIds){
        List<TopicPartition> partitionList = new ArrayList<>();

        for (Integer partitionId : partitionIds) {
            TopicPartition currentPartition = new TopicPartition(getTopicName(), partitionId);
            partitionList.add(currentPartition);
        }
        return partitionList;
    }

    protected String getTopicName(){
        return (String) topicProperties.get("topic");
    }

    protected String getProperty(String property){
        return (String) topicProperties.get(property);
    }

    /**
     * Get the list of partition associated with the consumer client. Ideally one consumer client will be
     * <br> assigned to single partition but single consumer may be assigned to multiple partition.
     * @return The partition list.
     */
    protected List<Integer> getPartitions(){
        String partitions = (String) topicProperties.get("partitions");
        return Arrays.stream(partitions.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }


    private void loadProperties(){
        InputStream iStream = null;
        try {
            iStream = this.getClass().getClassLoader().getResourceAsStream("topic.cfg");
            if(iStream == null){
                throw new IOException("File not found");
            }
            topicProperties.load(iStream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(iStream != null){
                    iStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
