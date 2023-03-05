package org.example.kafka;


import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.example.kafka.core.KafkaConsumerFactory;
import org.example.kafka.core.KafkaService;
import org.example.kafka.core.KafkaTopics;
import org.example.kafka.core.SimpleMessage;

import java.io.IOException;

@Slf4j
@SuperBuilder
public class FraudeConsumer extends KafkaConsumerFactory<SimpleMessage> {

    public static void main(String[] args) {
//        log.info("Criando novo Kafka Consumer...");
//        try(val consumer = new KafkaService<SimpleMessage>(FraudeConsumer.class.getSimpleName(), SimpleMessage.class)) {
//            log.info("Iniciando observer...");
//            while(true) {
//                consumer.get(KafkaTopics.TESTE, 1000L);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        FraudeConsumer.builder()
            .group(FraudeConsumer.class.getSimpleName())
            .topic(KafkaTopics.TESTE)
            .classType(SimpleMessage.class)
            .build()
            .run();
    }

}
