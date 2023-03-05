package org.example.kafka;


import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.example.kafka.core.KafkaService;
import org.example.kafka.core.KafkaTopics;

@Slf4j
public class FraudeConsumer {

    private static final Long CYCLE_TIME = 1000L;

    public static void main(String[] args) {
        log.info("Criando novo Kafka Consumer...");
        val consumer = KafkaService.newConsumer(FraudeConsumer.class.getSimpleName());
        log.info("Iniciando observer...");
        while(true) {
            consumer.get(KafkaTopics.PILOTO2, CYCLE_TIME);
        }
    }

}
