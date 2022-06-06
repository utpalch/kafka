package com.kafka.topic.stream.receiver;

import com.kafka.topic.stream.model.Notification;
import com.kafka.topic.stream.model.PosInvoice;
import com.kafka.topic.stream.serdes.AppSerdes;
import com.kafka.topic.stream.util.SteamAppConfig;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

public class PosStreamConsumerApp {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        new PosStreamConsumerApp().process();
    }

    public void process(){
        Properties properties = getProperties();
        //BUILDING STREAM AND FETCH FROM TOPIC
        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, PosInvoice> KS0 = builder.stream(SteamAppConfig.posTopicName,
                Consumed.with(AppSerdes.String(), AppSerdes.PosInvoice()));

        filterAndBuildShipmentTopic(KS0);

        filterAndBuildNotificationTopic(KS0);

        KafkaStreams streams = new KafkaStreams(builder.build(), properties);
        streams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Stopping Stream");
            streams.close();
        }));

    }

    private void filterAndBuildShipmentTopic(KStream<String, PosInvoice> KS0){
        KS0.filter((k, v) ->
                        v.getDeliveryType().equalsIgnoreCase(SteamAppConfig.DELIVERY_TYPE_HOME_DELIVERY))
                .to(SteamAppConfig.shipmentTopicName, Produced.with(AppSerdes.String(), AppSerdes.PosInvoice()));
    }


    private void filterAndBuildNotificationTopic(KStream<String, PosInvoice> KS0){
        KS0.filter((k, v) ->
                        v.getCustomerType().equalsIgnoreCase(SteamAppConfig.CUSTOMER_TYPE_PRIME))
                .mapValues(invoice -> getNotification(invoice))
                .to(SteamAppConfig.notificationTopic, Produced.with(AppSerdes.String(), AppSerdes.Notification()));
    }


    public Properties getProperties(){
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, SteamAppConfig.applicationID);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, SteamAppConfig.bootstrapServers);
        props.put(StreamsConfig.NUM_STREAM_THREADS_CONFIG , 3);
        props.put(StreamsConfig.PROCESSING_GUARANTEE_CONFIG, StreamsConfig.EXACTLY_ONCE);
        return props;
    }


    /**
     * Transform PosInvoice to Notification
     *
     * @param invoice PosInvoice Object
     * @return Notification Object
     */
    static Notification getNotification(PosInvoice invoice) {
        Notification notification = new Notification();
        notification.setCustomerIDNo(invoice.getCustomerCardNo());
        notification.setInvoiceNumber(invoice.getInvoiceNumber());
        notification.setTotalAmount(invoice.getTotalAmount());
        notification.setEarnedLoyaltyPoints(invoice.getTotalAmount() * SteamAppConfig.LOYALTY_FACTOR);
        return notification;
    }
}
