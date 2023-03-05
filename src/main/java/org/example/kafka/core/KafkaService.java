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
import java.lang.reflect.ParameterizedType;
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
public class KafkaService<T> implements Closeable {

    private final Properties properties = KafkaServiceConfig.KAFKA_PROPERTIES;
    private final String group;
    private final Class<T> classType;
    private KafkaProducer<String, T> producer;
    private KafkaConsumer<String, T> consumer;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yy/MM/dd HH:mm:ss:SS");

    public KafkaService(String group, Class<T> classType) {
        this.group = group;
        this.classType = classType;
        newConsumer(this);
        newProducer(this);
        log.info("Criando novo Kafka Service: group={}, type={}, properties={}",
            this.group, this.classType.getSimpleName(), this.properties);
    }

    private static void newConsumer(KafkaService<?> service) {
        service.properties.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "1");
        service.properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, service.group);
        service.consumer = new KafkaConsumer<>(service.properties);
    }

    private static void newProducer(KafkaService<?> service) {
        service.producer = new KafkaProducer<>(service.properties);
    }

    public RecordMetadata send(KafkaTopics topic, String key, T value) throws ExecutionException, InterruptedException {
        val record = new ProducerRecord<String, T>(topic.topicName, key, value);
        return producer.send(record, this::callback).get();
    }

    private void callback(RecordMetadata record, Exception ex) {
        if(ex != null)  ex.printStackTrace();
        else sendLog(record);
    }

    private void sendLog(RecordMetadata record) {
        val date = new Timestamp(record.timestamp()).toLocalDateTime().format(dateTimeFormatter);
        log.info("Evento enviado com sucesso: [{}] date={}, partition={}, offset={}",
            record.topic(), date, record.partition(), record.offset());
    }

    public List<T> get(KafkaTopics topic, Long duration) {
        duration = duration < 50 ? 50 : duration;
        log.info("Procurando por eventos sob tópico '{}'...", topic.topicName);
        if(topic.equals(KafkaTopics.ALL))
            consumer.subscribe(Pattern.compile(topic.topicName));
        else
            consumer.subscribe(Collections.singletonList(topic.topicName));
        val records = consumer.poll(Duration.ofMillis(duration));
        return recordsHandler(records);
    }

    private List<T> recordsHandler(ConsumerRecords<String, T> records) {
        if(records.isEmpty()) {
            log.info("Nenhum evento disponível");
            return null;
        }
        else {
            log.info("Identificado total de {} eventos.", records.count());
            val recordsValues = new ArrayList<T>();
            for(ConsumerRecord<String, T> record : records) {
                recordsValues.add(getRecordValue(record));
            }
            return recordsValues;
        }
    }

    private T getRecordValue(ConsumerRecord<String, T> record) {
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
