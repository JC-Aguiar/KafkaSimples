package org.example.util.api;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@ToString
@Slf4j(topic = "Validador")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Validador {

    final EntradaComum entrada;
    final ValidadorResultado resultado = new ValidadorResultado();
    final List<ValidadorElemento> alvos = new ArrayList<>();
    final static String SEM_CAMPOS_OBRIGATORIOS = "PROCESSO: O serviço solicitado não tem campos obrigatórios.";
    final static String COM_CAMPOS_OBRIGATORIOS = "PROCESSO: O serviço solicitado possui %d campos obrigatórios.";
    final static String ENTRADA_INVALIDA = "RESULTADO: A requisição foi enviada sem nenhuma informação.";
    final static String ENTRADA_COM_ERROS = "RESULTADO: A requisição possui alguns campos inválidos.";

    public Validador(EntradaComum entrada) throws EntradaInvalidaException {
        log.info("INICIANDO: Validador");
        log.info("PARÂMETROS: entrada={}", entrada);
        this.entrada = entrada;
        Optional.ofNullable(entrada).orElseThrow(() -> {
            this.resultado.setMensagem(ENTRADA_INVALIDA);
            return new EntradaInvalidaException(this.resultado);
        });
        this.alvos.addAll(ValidadorElemento.identificar(entrada));
        this.exibaAlvos();
        this.gereResultado();
        log.info("SAINDO: Validador");
    }

    public static Validador de(EntradaComum entrada) throws EntradaInvalidaException, NoSuchMethodException {
        return new Validador(entrada);
    }

    private void exibaAlvos() {
        if (alvos.isEmpty()) log.info(SEM_CAMPOS_OBRIGATORIOS);
        else log.info(String.format(COM_CAMPOS_OBRIGATORIOS, alvos.size()));
    }

    private Validador gereResultado() throws EntradaInvalidaException {
        this.alvos.stream()
            .filter(alvo -> !ValidadorFuncoes.validar(alvo))
            .forEach(alvo -> resultado.addErro(alvo.getCampoNome(), alvo.getRestricao().criterio().descricao));
        if (!resultado.getErros().isEmpty()) {
            this.resultado.setMensagem(ENTRADA_COM_ERROS);
            throw new EntradaInvalidaException(this.resultado);
        }
        return this;
    }

}
