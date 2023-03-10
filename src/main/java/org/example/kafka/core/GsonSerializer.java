package org.example.kafka.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.val;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;
import org.example.util.LocalDateTimeSerializer;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Map;

public class GsonSerializer<T> implements Serializer<T> {

    private final Gson gson = new GsonBuilder()
        .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer())
        .create();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    @Override
    public byte[] serialize(String topic, T data) {
        return gson.toJson(data).getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public byte[] serialize(String topic, Headers headers, T data) {
        return Serializer.super.serialize(topic, headers, data);
    }

    @Override
    public void close() {
        Serializer.super.close();
    }
}
