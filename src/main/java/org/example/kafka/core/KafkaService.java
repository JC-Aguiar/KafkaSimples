package org.example.kafka.core;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;

@Slf4j
@Getter
@ToString
@EqualsAndHashCode
public class KafkaService implements Closeable {

    private final Properties properties = KafkaServiceConfig.KAFKA_PROPERTIES;
    private final String group;
    private KafkaProducer<String, String> producer;
    private KafkaConsumer<String, String> consumer;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yy/MM/dd HH:mm:ss:SS");

    private KafkaService(String group) {
        this.group = group;
        log.info("Criando novo Kafka Service: group={}, properties={}", this.group, this.properties);
    }

    public static KafkaService newConsumer(String group) {
        val kafka = new KafkaService(group);
        kafka.properties.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "1");
        kafka.properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, group);
        kafka.consumer = new KafkaConsumer<String, String>(kafka.properties);
        return kafka;
    }

    public static KafkaService newProducer(String group) {
        val kafka = new KafkaService(group);
        kafka.producer = new KafkaProducer<String, String>(kafka.properties);
        return kafka;
    }

    public RecordMetadata send(KafkaTopics topic, String key, String value) throws ExecutionException, InterruptedException {
        val record = new ProducerRecord<>(topic.topicName, key, value);
        return producer.send(record, this::callback).get();
    }

    private void callback(RecordMetadata record, Exception ex) {
        if(ex != null)  ex.printStackTrace();
        else sendlLog(record);
    }

    private void sendlLog(RecordMetadata record) {
        val date = new Timestamp(record.timestamp()).toLocalDateTime().format(dateTimeFormatter);
        log.info("Evento enviado com sucesso: [{}] date={}, partition={}, offset={}",
            record.topic(), date, record.partition(), record.offset());
    }

    public List<String> get(KafkaTopics topic, Long duration) {
        duration = duration < 50 ? 50 : duration;
        log.info("Procurando por eventos sob tópico '{}'...", topic.topicName);
        if(topic.equals(KafkaTopics.ALL))
            consumer.subscribe(Pattern.compile(topic.topicName));
        else
            consumer.subscribe(Collections.singletonList(topic.topicName));
        val records = consumer.poll(Duration.ofMillis(duration));
        return recordsHandler(records);
    }

    private List<String> recordsHandler(ConsumerRecords<String, String> records) {
        if(records.isEmpty()) {
            log.info("Nenhum evento disponível");
            return null;
        }
        else {
            log.info("Identificado total de {} eventos.", records.count());
            val recordsValues = new ArrayList<String>();
            for(ConsumerRecord<String, String> record : records) {
                recordsValues.add(getRecordValue(record));
            }
            return recordsValues;
        }
    }

    private String getRecordValue(ConsumerRecord<String, String> record) {
        val date = new Timestamp(record.timestamp()).toLocalDateTime().format(dateTimeFormatter);
        log.info("Evento: [{}] date={}, key={}, value={}", record.topic(), date, record.key(), record.value());
        log.info("Processado evento...");
        try {
            Thread.sleep(500);
            return record.value();

        } catch (InterruptedException e) {
            log.warn("Erro. Evento não processado.");
            return null;

        } finally {
            log.info("Evento processado com sucesso.");
        }
    }

    @Override
    public void close() throws IOException {
        if(producer != null) producer.close();
        if(consumer != null) consumer.close();
    }
}
