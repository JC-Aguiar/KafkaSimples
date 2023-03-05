package org.example.kafka.core;

import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.io.IOException;

@Slf4j
@SuperBuilder
@RequiredArgsConstructor
public abstract class KafkaConsumerFactory<T> {

    private static final Long CYCLE_TIME = 1000L;
    private String group;
    private KafkaTopics topic;
    private Class<T> classType;

    public final void run() {
        log.info("Criando novo Kafka Consumer...");
        try(val consumer = new KafkaService<T>(group, classType)) {
            log.info("Iniciando observer...");
            while(true) {
                consumer.get(topic, CYCLE_TIME);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
