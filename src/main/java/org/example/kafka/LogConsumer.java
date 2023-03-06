package org.example.kafka;


import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.example.kafka.core.KafkaConsumerFactory;
import org.example.kafka.core.KafkaTopics;

@Slf4j
@SuperBuilder
public class LogConsumer extends KafkaConsumerFactory<String> {

    public static void main(String[] args) {
        LogConsumer.builder()
            .group(LogConsumer.class.getSimpleName())
            .topic(KafkaTopics.ALL)
            .classType(String.class)
            .build()
            .run();
    }

}
