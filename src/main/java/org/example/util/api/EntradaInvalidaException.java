package org.example.util.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntradaInvalidaException extends Exception {

    private ValidadorResultado resultado;

    public EntradaInvalidaException(ValidadorResultado resultado) {
        super(resultado.getMensagem());
        this.resultado = resultado;
    }

}
