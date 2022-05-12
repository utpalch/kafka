package com.kafka.topic.consumer.model;

public class ConsumerDTO {

    private String topic;
    private Integer partition;
    private Long offset;
    private String recordKey;
    private String recordvalue;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Integer getPartition() {
        return partition;
    }

    public void setPartition(Integer partition) {
        this.partition = partition;
    }

    public Long getOffset() {
        return offset;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public String getRecordKey() {
        return recordKey;
    }

    public void setRecordKey(String recordKey) {
        this.recordKey = recordKey;
    }

    public String getRecordvalue() {
        return recordvalue;
    }

    public void setRecordvalue(String recordvalue) {
        this.recordvalue = recordvalue;
    }

    @Override
    public String toString() {
        return "ConsumerDTO{" +
                "topic='" + topic + '\'' +
                ", partition=" + partition +
                ", offset=" + offset +
                ", recordKey='" + recordKey + '\'' +
                ", recordvalue='" + recordvalue + '\'' +
                '}';
    }
}
