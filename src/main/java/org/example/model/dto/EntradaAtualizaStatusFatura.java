package org.example.model.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.example.util.api.CampoObrigatorio;

import java.util.List;

@Getter
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class EntradaAtualizaStatusFatura extends EntradaGrupoContrato {

    @CampoObrigatorio(criterio = CampoObrigatorio.Criterio.NAO_VAZIO)
    private List<EntradaNumeroFatura> faturas;

    public void setFaturas(List<EntradaNumeroFatura> faturas) {
        this.faturas = faturas;
    }
}
