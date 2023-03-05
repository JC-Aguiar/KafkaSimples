package org.example;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.example.kafka.core.KafkaService;
import org.example.kafka.core.KafkaTopics;
import org.example.kafka.core.SimpleMessage;
import org.example.model.dto.EmailDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import static org.example.util.GeradorAleatorio.*;

@Slf4j
public class App {

    public static void main(String[] args) {
        for (int i = 0; i < 150; i++) {
            try(val testeProducer = new KafkaService<>(App.class.getSimpleName(), SimpleMessage.class);
                val emailProducer = new KafkaService<>(App.class.getSimpleName(), EmailDto.class)) {
                    testNFRecord(testeProducer);
                    sendEmailRecord(emailProducer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static RecordMetadata testNFRecord(KafkaService<SimpleMessage> service)
    throws ExecutionException, InterruptedException {
        val key = UUID.randomUUID().toString() + ":id1548779";
        val contrato = getXNumerosInt(10) + "-JM";
        val nf = getXNumerosInt(6) + "-" + getInt();
        val message = new SimpleMessage(contrato + ", " + nf);
        return service.send(KafkaTopics.TESTE, key, message);
    }

    private static RecordMetadata sendEmailRecord(KafkaService<EmailDto> service)
    throws ExecutionException, InterruptedException {
        val key = UUID.randomUUID().toString() + ":id1548779";
        val emailsAlvos = new ArrayList<String>();
        val quantidadeEnvios = getInt() + 1;
        for(int i = 0; i < quantidadeEnvios; i++) {
            emailsAlvos.add(getNome().toLowerCase(Locale.ROOT) + "@gmail.com");
        }
        val email = EmailDto.builder()
            .userName(getNomeCompleto())
            .email(getNome().toLowerCase(Locale.ROOT) + "@gmail.com")
            .sendDate(LocalDateTime.now())
            .build();
        email.addEmails(emailsAlvos);
        return service.send(KafkaTopics.EMAIL, key, email);
    }

}
