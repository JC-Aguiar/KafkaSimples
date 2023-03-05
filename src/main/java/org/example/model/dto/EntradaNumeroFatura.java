package org.example.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EntradaNumeroFatura {

    private String nrFatura;

    public void setNrFatura(String nrFatura) {
        this.nrFatura = nrFatura;
    }
}
