package org.example.util.api;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
public @interface CampoObrigatorio {

        Criterio criterio();
        int valor() default 0;

        public enum Criterio {
                NAO_NULO("O campo está ausente e deve ser informado"),
                NAO_VAZIO("O campo está vazio, sem valor ou zerado"),
                NAO_NEGATIVO("O campo informa valor abaixo de zero"),
                MIN("O campo deve ter um tamanho/valor acima do mínimo"),
                MAX("O campo deve ter um tamanho/valor abaixo do máximo"),
                EXATO("O campo deve ter um tamanho/valor exato"),
                EMAIL("O campo não segue o padrão E-mail internacional"),
                SENHA("O campo não segue o padrão LGPD de senha");

                final public String descricao;

                Criterio(String descricao) {
                        this.descricao = descricao;
                }
        }


}
