package org.example.kafka.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class SimpleMessage implements Serializable {

    private String message;

    public SimpleMessage(String message) {
        this.message = message;
    }
}
