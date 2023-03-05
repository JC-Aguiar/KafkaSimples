package org.example;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.example.kafka.core.KafkaService;
import org.example.kafka.core.KafkaTopics;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Slf4j
public class App {

    public static void main(String[] args) {
        try(val producer = KafkaService.newProducer(App.class.getSimpleName())) {
            for (int i = 0; i < 150; i++) {
                try {
                    testPilotoRecord(producer);
                    sendEmailRecord(producer);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static RecordMetadata testPilotoRecord(KafkaService service)
    throws ExecutionException, InterruptedException {
        val key = UUID.randomUUID().toString() + ":id1548779";
        val value = "{ 'contrato':'1202458045-JM, 'nfe':'125578-45' }";
        return service.send(KafkaTopics.TESTE, key, value);
    }

    private static RecordMetadata sendEmailRecord(KafkaService service)
    throws ExecutionException, InterruptedException {
        val key = UUID.randomUUID().toString() + ":id1548779";
        val value = "Olá. Seu acordo foi realizada com sucesso.";
        return service.send(KafkaTopics.EMAIL, key, value);
    }

}
