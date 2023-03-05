package org.example.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RetornoListaFaturas extends RetornoGrupoContrato {

    List<RetornoFatura> faturas = new ArrayList<>();

    public RetornoListaFaturas(String grupo, String contrato) {
        super(grupo, contrato);
    }

    public RetornoListaFaturas addFatura(RetornoFatura faturas) {
        this.faturas.add(faturas);
        return this;
    }

    public RetornoListaFaturas addFatura(List<RetornoFatura> faturas) {
        this.faturas.addAll(faturas);
        return this;
    }

}
