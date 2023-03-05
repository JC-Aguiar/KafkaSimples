package org.example.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RetornoListaFaturas extends RetornoGrupoContrato {

    @JsonProperty("faturas")
    List<RetornoFatura> faturas = new ArrayList<>();

    public RetornoListaFaturas(
        @NotBlank(message = "Campo obrigatório 'grupo' deve ser informado e não ser vazio.")
        String grupo,
        @NotBlank(message = "Campo obrigatório 'contrato' deve ser informado e não ser vazio.")
        String contrato) {
        //corpo
        super(grupo, contrato);
    }

    public RetornoListaFaturas addFatura(
        @NotNull(message = "Objeto RetornoFatura está nulo")
        RetornoFatura faturas) {
        //corpo
        this.faturas.add(faturas);
        return this;
    }

    public RetornoListaFaturas addFatura(
        @NotNull(message = "Lista de RetornoFatura veio nula")
        List<@NotNull(message = "Objeto RetornoFatura está nulo") RetornoFatura> faturas) {
        //corpo
        this.faturas.addAll(faturas);
        return this;
    }

}
