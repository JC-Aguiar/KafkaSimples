package org.example.model.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.util.api.CampoObrigatorio;

@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class EntradaConsultaStatusFatura extends EntradaGrupoContrato {

    @CampoObrigatorio(criterio = CampoObrigatorio.Criterio.NAO_VAZIO)
    private String assessoria;
}
