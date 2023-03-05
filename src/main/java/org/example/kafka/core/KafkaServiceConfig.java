package org.example.kafka.core;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaServiceConfig {
    protected static final Properties KAFKA_PROPERTIES = new Properties();

    static {
        KAFKA_PROPERTIES.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        KAFKA_PROPERTIES.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, GsonSerializer.class.getName());
        KAFKA_PROPERTIES.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, GsonSerializer.class.getName());
        KAFKA_PROPERTIES.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, GsonSerializer.class.getName());
        KAFKA_PROPERTIES.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, GsonSerializer.class.getName());
    }

}
