package org.example.kafka.core;

public enum KafkaTopics {

    PILOTO2("TESTE_PILOTO2"),
    EMAIL("SEND_EMAIL"),
    ALL(".*");

    public final String value;

    KafkaTopics(String value) {
        this.value = value;
    }
}
