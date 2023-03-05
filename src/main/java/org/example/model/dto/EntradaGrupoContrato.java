package org.example.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.example.util.api.CampoObrigatorio;
import org.example.util.api.EntradaComum;

import java.io.IOException;
import java.io.Writer;

@Getter
@Setter
@SuperBuilder
@ToString
@EqualsAndHashCode(callSuper = false)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EntradaGrupoContrato extends EntradaComum {

    @CampoObrigatorio(criterio = CampoObrigatorio.Criterio.NAO_VAZIO)
    private String grupo;

    @CampoObrigatorio(criterio = CampoObrigatorio.Criterio.NAO_VAZIO)
    private String contrato;

    public EntradaGrupoContrato() { super(); }

    public void writeTo(Writer w) throws IOException {
        boolean objNull = true;
        w.append("{");
        if (grupo != null) {
            w.append("\"grupo\":\"");
            w.append(grupo);
            w.append("\"");
            objNull = false;
        }
        if (contrato != null) {
            if (!objNull) {
                w.append(",");
            }
            w.append("\"contrato\":\"");
            w.append(contrato);
            w.append("\"");
        }
        w.append("}");
    }

}
