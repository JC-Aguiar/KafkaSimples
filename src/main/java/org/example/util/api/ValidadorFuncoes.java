package org.example.util.api;

import lombok.SneakyThrows;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.example.util.api.CampoObrigatorio.Criterio.NAO_NULO;


public class ValidadorFuncoes {

    private static final String CAMPO_VALOR_INVALIDO =
        "Não foi possível realizar a validação do campo %s. " +
        "O valor do campo deve ser um objeto: Objeto ComumIn, Collection, String ou Integer.";

    @SneakyThrows
    public static boolean validar(ValidadorElemento alvo) {
        final CampoObrigatorio restricao = alvo.getRestricao();
        if(!alvo.getCampoValor().isPresent()) return false;
        final Object campoValor = alvo.getCampoValor().get();
        if(Collection.class.isAssignableFrom(campoValor.getClass()))
            return validar(restricao, (Collection<?>) campoValor);
        else if(String.class.isAssignableFrom(campoValor.getClass()))
            return validar(restricao, (String) campoValor);
        else if(Integer.class.isAssignableFrom(campoValor.getClass()))
            return validar(restricao, (Integer) campoValor);
        else if(EntradaComum.class.isAssignableFrom(campoValor.getClass()))
            return validar(restricao, (EntradaComum) campoValor);
        else throw new NoSuchMethodException(String.format(CAMPO_VALOR_INVALIDO, alvo.getCampoNome()));
    }

    private static boolean validar(CampoObrigatorio restricao, String campoValor) {
        final CampoObrigatorio.Criterio criterio = restricao.criterio();
        final int valor = restricao.valor();
        switch(criterio) {
            case NAO_NULO: return naoNulo(campoValor);
            case NAO_VAZIO: return naoVazio(campoValor);
            case MIN: return min(campoValor, valor);
            case MAX: return max(campoValor, valor);
            case EXATO: return exato(campoValor, valor);
            case EMAIL: return email(campoValor);
            case SENHA: return senha(campoValor);
            default: return true;
        }
    }

    private static boolean validar(CampoObrigatorio restricao, Integer campoValor) {
        final CampoObrigatorio.Criterio criterio = restricao.criterio();
        final int valor = restricao.valor();
        switch(criterio) {
            case NAO_NULO: return naoNulo(campoValor);
            case NAO_VAZIO: return naoVazio(campoValor);
            case NAO_NEGATIVO: return naoNegativo(campoValor);
            case MIN: return min(campoValor, valor);
            case MAX: return max(campoValor, valor);
            case EXATO: return exato(campoValor, valor);
            default: return true;
        }
    }

    private static boolean validar(CampoObrigatorio restricao, Collection<?> campoValor) {
        final CampoObrigatorio.Criterio criterio = restricao.criterio();
        final int valor = restricao.valor();
        switch(criterio) {
            case NAO_NULO: return naoNulo(campoValor);
            case NAO_VAZIO: return naoVazio(campoValor);
            case MIN: return min(campoValor, valor);
            case MAX: return max(campoValor, valor);
            case EXATO: return exato(campoValor, valor);
            default: return true;
        }
    }

    private static boolean validar(CampoObrigatorio restricao, EntradaComum campoValor) {
        if(restricao == null) return true;
        if (restricao.criterio() == NAO_NULO)
            return naoNulo(campoValor);
        return true;
    }

    private static boolean naoNulo(Object object) {
        return object != null;
    }

    private static boolean naoVazio(String texto) {
        return naoNulo(texto) && !texto.trim().isEmpty();
    }

    private static boolean naoVazio(Integer valor) {
        return naoNulo(valor) && valor != 0;
    }

    private static boolean naoVazio(Collection colecao) {
        return naoNulo(colecao) && !colecao.isEmpty();
    }

    private static boolean naoNegativo(Integer valor) {
        return naoNulo(valor) && valor >= 0;
    }

    private static boolean min(String texto, int min) {
        return naoNulo(texto) && texto.trim().length() >= min;
    }

    private static boolean min(Integer valor, int min) {
        return naoNulo(valor) && valor >= min;
    }

    private static boolean min(Collection<?> colecao, int min) {
        return naoNulo(colecao) && colecao.size() >= min;
    }

    private static boolean max(String texto, int max) {
        return naoNulo(texto) && texto.trim().length() <= max;
    }

    private static boolean max(Integer valor, int max) {
        return naoNulo(valor) && valor <= max;
    }

    private static boolean max(Collection<?> colecao, int max) {
        return naoNulo(colecao) && colecao.size() <= max;
    }

    private static boolean exato(String texto, int tamanho) {
        return naoNulo(texto) && texto.trim().length() == tamanho;
    }

    private static boolean exato(Integer valor, int tamanho) {
        return naoNulo(valor) && String.valueOf(valor).trim().length() == tamanho;
    }

    private static boolean exato(Collection<?> colecao, int tamanho) {
        return naoNulo(colecao) && colecao.size() == tamanho;
    }

    private static boolean email(String texto) {
        final String regex = "^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        return naoNulo(texto) && validarRegex(regex, texto);
    }

    private static boolean senha(String texto) {
        final String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[$*&@#])[0-9a-zA-Z$*&@#]{8,}$";
        return naoNulo(texto) && validarRegex(regex, texto);
    }

    private static boolean validarRegex(String regex, String texto) {
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(texto);
        int matches = 0;
        while (matcher.find()) {
            matches++;
        }
        return matches != 0;
    }

}
