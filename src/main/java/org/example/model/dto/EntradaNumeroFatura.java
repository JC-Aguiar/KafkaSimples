package org.example.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EntradaNumeroFatura {

    @NotBlank(message = "Campo obrigatório 'nrFatura' não informado.")
    private String nrFatura;

    public void setNrFatura(@NotBlank(message = "Campo obrigatório 'nrFatura' não informado.") String nrFatura) {
        this.nrFatura = nrFatura;
    }
}
