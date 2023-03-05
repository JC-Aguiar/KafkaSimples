package org.example.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@SuperBuilder
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RetornoGrupoContrato {

    @NotBlank(message = "Campo obrigatório 'grupo' deve ser informado e não ser vazio.")
    String grupo;

    @NotBlank(message = "Campo obrigatório 'contrato' deve ser informado e não ser vazio.")
    String contrato;

    public void setGrupo(
        @NotBlank(message = "Campo obrigatório 'grupo' deve ser informado e não ser vazio.")
        String grupo) {
        this.grupo = grupo;
    }

    public void setContrato(
        @NotBlank(message = "Campo obrigatório 'contrato' deve ser informado e não ser vazio.")
        String contrato) {
        this.contrato = contrato;
    }
}
