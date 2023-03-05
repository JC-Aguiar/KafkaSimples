package org.example.model.dto;

import br.com.ppware.app.api.CampoObrigatorio;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class EntradaConsultaStatusFatura extends EntradaGrupoContrato {

    @JsonProperty("usuarioAssessoria")
    @CampoObrigatorio(criterio = CampoObrigatorio.Criterio.NAO_VAZIO)
    private String assessoria;
}
