package org.example.kafka.core;

public enum KafkaTopics {

    TESTE("TESTE_PILOTO2"),
    EMAIL("SEND_EMAIL"),
    ALL(".*");

    public final String topicName;

    KafkaTopics(String topicName) {
        this.topicName = topicName;
    }
}
