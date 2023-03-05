package org.example.util.api;

import lombok.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ValidadorResultado implements Serializable {

    private String mensagem;

    private final Map<String, String> erros = new HashMap<>();

    public ValidadorResultado addErro(String campo, String mensagem) {
        this.erros.put(campo, mensagem);
        return this;
    }

    public ValidadorResultado addErro(Map<String, String> erros) {
        this.erros.putAll(erros);
        return this;
    }

    public String getResumo() {
        final StringBuilder resumo = new StringBuilder()
            .append(mensagem).append("\n");
        erros.keySet().forEach(
            campo -> resumo.append(campo).append(": ").append(erros.get(campo)).append("\n"));
        return resumo.toString();
    }

}
