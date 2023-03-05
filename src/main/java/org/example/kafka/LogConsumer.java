package org.example.kafka;


import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.example.kafka.core.KafkaConsumerFactory;
import org.example.kafka.core.KafkaTopics;
import org.example.kafka.core.SimpleMessage;

@Slf4j
@SuperBuilder
public class LogConsumer extends KafkaConsumerFactory<SimpleMessage> {

    public static void main(String[] args) {
        FraudeConsumer.builder()
            .group(LogConsumer.class.getSimpleName())
            .topic(KafkaTopics.ALL)
            .classType(SimpleMessage.class)
            .build()
            .run();
    }

}
