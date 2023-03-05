package org.example.kafka;


import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.example.kafka.core.KafkaConsumerFactory;
import org.example.kafka.core.KafkaTopics;
import org.example.model.dto.EmailDto;

@Slf4j
@SuperBuilder
public class EmailConsumer extends KafkaConsumerFactory<EmailDto> {

    public static void main(String[] args) {
        EmailConsumer.builder()
            .group(EmailConsumer.class.getSimpleName())
            .topic(KafkaTopics.EMAIL)
            .classType(EmailDto.class)
            .build()
            .run();
    }

}
