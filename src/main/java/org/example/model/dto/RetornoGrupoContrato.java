package org.example.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Getter
@AllArgsConstructor
@SuperBuilder
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RetornoGrupoContrato {

    String grupo;

    String contrato;

    public void setGrupo(
        String grupo) {
        this.grupo = grupo;
    }

    public void setContrato(
        String contrato) {
        this.contrato = contrato;
    }
}
