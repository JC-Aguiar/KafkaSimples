package org.example.util.api;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Slf4j(topic = "ValidadorElemento")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ValidadorElemento {

    Field campo;
    String campoNome;
    Optional<?> campoValor;
    CampoObrigatorio restricao;

    @SneakyThrows
    private ValidadorElemento(Field campo, CampoObrigatorio restricao, Object entrada) {
        campo.setAccessible(true);
        this.campo = campo;
        this.campoNome = campo.getName();
        this.campoValor = Optional.ofNullable(campo.get(entrada));
        this.restricao = restricao;
    }

    public static List<ValidadorElemento> identificar(@NonNull EntradaComum entrada) {
        log.info("INICIANDO: identificar");
        log.info("PARÂMETROS: entrada={}", entrada);
        final List<ValidadorElemento> alvosTotais = new ArrayList<>();
        alvosTotais.addAll(getAlvosSuperclasse(entrada));
        alvosTotais.addAll(getAlvosClasseSubclasse(entrada));
        log.info("SAINDO: identificar");
        return alvosTotais;
    }

    //Adiciona na lista objetosParaValidar a maior superclasse do objeto de entrada (exceto EntradaComum)
    private static List<ValidadorElemento> getAlvosSuperclasse(@NonNull EntradaComum entrada) {
        log.info("PROCESSO: Buscando por campos nas Super-Classes");
        Class<? extends EntradaComum> classeAlvo = entrada.getClass();
        final List<ValidadorElemento> alvos = new ArrayList<>();
        while(classeAlvo != EntradaComum.class) {
            classeAlvo = (Class<? extends EntradaComum>) classeAlvo.getSuperclass();
            log.info("PROCESSO: Analisando Super-Classes: " + classeAlvo.getSimpleName());
            final List<Field> campos = Arrays.asList(classeAlvo.getDeclaredFields());
            alvos.addAll(
                campos.stream()
                    .filter(ValidadorElemento::validarCampo)
                    .map(campo -> new ValidadorElemento(
                        campo,
                        campo.getAnnotation(CampoObrigatorio.class),
                        entrada))
                    .collect(Collectors.toList())
            );
        }
        return alvos;
    }

    //O loop abaixo acessa objetos para investigar se há campos com @CampoObrigatorio
    //Se estes forem objetos do tipo Collection/EntradaComum, são adicionados na lista do loop
    private static List<ValidadorElemento> getAlvosClasseSubclasse(Object entrada) {
        log.info("PROCESSO: Buscando por campos na Sub-Classes");
        final List<Object> objetosParaValidar = new ArrayList<>();
        objetosParaValidar.add(entrada);
        final List<ValidadorElemento> alvosTotais = new ArrayList<>();
        while(!objetosParaValidar.isEmpty()) {
            //Avança no índice dos objetosParaValidar
            final Object objetoAtual = objetosParaValidar.remove(0);
            log.info("PROCESSO: Analisando Classe: " + objetoAtual.getClass().getSimpleName());
            //Mapeia se há campos obrigatórios e os adiciona aos alvos e alvosTotais
            final List<ValidadorElemento> alvos = mapearAlvos(objetoAtual);
            alvosTotais.addAll(alvos);
            if(!alvos.isEmpty()) {
                alvos.stream()
                    .map(ValidadorElemento::getCampoValor)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .filter(valor -> Collection.class.isAssignableFrom(valor.getClass()))
                    .map(valor -> (Collection<?>) valor)
                    .forEach(objetosParaValidar::addAll);
                alvos.stream()
                    .map(ValidadorElemento::getCampoValor)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .filter(valor -> EntradaComum.class.isAssignableFrom(valor.getClass()))
                    .map(valor -> (EntradaComum) valor)
                    .forEach(objetosParaValidar::add);
            }
        }
        return alvosTotais;
    }

    private static List<ValidadorElemento> mapearAlvos(Object campoObjeto) {
        //Coleta listagem de todos os atributos
        final List<Field> atributos = Arrays
            .stream(campoObjeto.getClass().getDeclaredFields())
            .collect(Collectors.toList());
        //Valida se há atributos com anotação @CampoObrigatorio para retornar em forma de lista
        return atributos
             .stream()
             .filter(ValidadorElemento::validarCampo)
             .map(campo -> new ValidadorElemento(
                 campo,
                 campo.getAnnotation(CampoObrigatorio.class),
                 campoObjeto))
             .collect(Collectors.toList());
    }

    private static boolean validarCampo(Field campo) {
        log.info("PROCESSO: Campo: " + campo);
        return filtrarAnotacoes(campo.getDeclaredAnnotations());
    }

    private static boolean filtrarAnotacoes(Annotation[] ann) {
        log.info("PROCESSO: Anotações: " + Arrays.deepToString(ann));
        return Arrays
            .stream(ann)
            .map(Annotation::annotationType)
            .anyMatch(annt -> annt.equals(CampoObrigatorio.class));
    }
}
