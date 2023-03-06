package org.example.kafka.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.val;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;
import org.example.util.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.util.Map;

public class GsonDeserializer<T> implements Deserializer<T> {

    public static final String TYPE_CONFIG = "java.lang.String";
    private final Gson gson = new GsonBuilder()
        .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer())
        .create();
    private Class<T> type;
    private final static String ERRO_CLASSNAME = "O nome da classe informado no KafkaServiceConfig não foi encontrado " +
        "no classpath da aplicação";

    @Override
    public void configure(Map<String, ?> kafkaConfigProperties, boolean isKey) {
        val typeName = String.valueOf(kafkaConfigProperties.get(TYPE_CONFIG));
        try {
            type = (Class<T>) Class.forName(typeName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(ERRO_CLASSNAME, e);
        }
    }

    @Override
    public T deserialize(String topic, byte[] data) {
        return gson.fromJson(new String(data), this.type);
    }

    @Override
    public T deserialize(String topic, Headers headers, byte[] data) {
        return Deserializer.super.deserialize(topic, headers, data);
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }
}
