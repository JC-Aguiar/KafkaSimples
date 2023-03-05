package org.example.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class GeradorAleatorio {

    private static final String DATA_FORMATO = "yy/MM/dd HH:mm:ss:SS";

    private static List<String> nome = new ArrayList<String>() {{
        add("Maria");
        add("José");
        add("João");
        add("Ana");
        add("Pedro");
        add("Juliana");
        add("Felipe");
        add("Gabriela");
        add("Gabrielle");
        add("Rafael");
        add("Daniel");
        add("Maurício");
        add("Augusto");
        add("Maura");
        add("Carla");
        add("Carlos");
        add("Carolina");
        add("Caroline");
        add("Eliana");
        add("Isabela");
        add("Isabelle");
        add("Fabio");
        add("Carol");
        add("Lucas");
        add("Thiago");
        add("Roberta");
        add("Guilherme");
        add("Beatriz");
        add("Leonardo");
        add("Valentina");
        add("Rafaela");
        add("Marcela");
        add("Rodrigo");
        add("Paola");
        add("Aline");
        add("Alberto");
        add("Luna");
        add("Bruno");
        add("Bruna");
        add("Lais");
        add("Victor");
        add("Juliana");
        add("Henrique");
        add("Mariana");
        add("Fernand");
        add("John");
        add("Jane");
        add("Sarah");
        add("Michael");
        add("Emily");
    }};

    private static List<String> sobrenome = new ArrayList<String>() {{
        add("Silva");
        add("Santos");
        add("Oliveira");
        add("Souza");
        add("Rodrigues");
        add("Ferreira");
        add("Alves");
        add("Aguiar");
        add("Assis");
        add("Almeida");
        add("Pereira");
        add("Barbosa");
        add("Barros");
        add("Batista");
        add("Borges");
        add("Campos");
        add("Cardoso");
        add("Carvalho");
        add("Castro");
        add("Costa");
        add("Costal");
        add("Dias");
        add("Duarte");
        add("Freitas");
        add("Fernandes");
        add("Garcia");
        add("Gomes");
        add("Gonçalves");
        add("Lima");
        add("Lopes");
        add("Machado");
        add("Marques");
        add("Martins");
        add("Medeiros");
        add("Melo");
        add("Mello");
        add("Mendes");
        add("Miranda");
        add("Monteiro");
        add("Smith");
        add("Johnson");
        add("Williams");
        add("Brown");
        add("Jones");
    }};

    public static String getNomeCompleto() {
        return getNome() + " " + getSobrenome();
    }

    public static String getNome() {
        Random rand = new Random();
        int index = rand.nextInt(nome.size());
        return nome.get(index);
    }

    public static String getSobrenome() {
        Random rand = new Random();
        int index = rand.nextInt(sobrenome.size());
        return sobrenome.get(index);
    }


    public static LocalDate getData() {
        final DateTimeFormatter formato = DateTimeFormatter.ofPattern(DATA_FORMATO);
        return LocalDate.parse(getDataString(), formato);
    }

    public static LocalDateTime getDataHora() {
        final DateTimeFormatter formato = DateTimeFormatter.ofPattern(DATA_FORMATO);
        return LocalDateTime.parse(getDataString(), formato);
    }

    public static Timestamp getTimestamp() {
        return Timestamp.valueOf(getDataString());
    }

   //TODO: criar gerador de tempo aleatório
    public static String getDataString() {
        Random rand = new Random();
        int minData = (int) LocalDate.of(1920, 1, 1).toEpochDay();
        int maxData = (int) LocalDate.of(2020, 12, 31).toEpochDay();
        long randomData = minData + rand.nextInt(maxData - minData);
        LocalDate data = LocalDate.ofEpochDay(randomData);
        return data.toString() + "T12:30:00.000+0300";
    }

    //CPF sem calcular o dígito verificar
    public static int getCpf() {
        return Integer.parseInt(getCpfString());
    }

    //CPF sem dígito verificar calculado
    public static String getCpfString() {
        return getXNumerosString(11);
    }

    public static Float getXNumerosFloat(int caracteres) {
        try {
            return Float.parseFloat(getXNumerosString(caracteres));
        } catch (Exception e) {
            return Float.MAX_VALUE;
        }
    }

    public static long getXNumerosLong(int caracteres) {
        try {
            return Long.parseLong(getXNumerosString(caracteres));
        } catch (Exception e) {
            return Long.MAX_VALUE;
        }
    }

    public static int getXNumerosInt(int caracteres) {
        try {
            return Integer.parseInt(getXNumerosString(caracteres));
        } catch (Exception e) {
            return Integer.MAX_VALUE;
        }
    }

    public static String getXNumerosString(int caracteres) {
        final List<Integer> numeros = new ArrayList<>();
        for(int i = 0; i < caracteres; i++) {
            numeros.add(getInt());
        }
        return numeros
            .stream()
            .map(String::valueOf)
            .collect(Collectors.joining());
    }

    public static int getInt() {
        return new Random().nextInt(9);
    }

    public static int getInt(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    public static long getLong(int decimais) {
        Random rand = new Random();
        return (long) (rand.nextDouble() * Math.pow(10, decimais));
    }

    public static BigDecimal getBigDecimal(int decimais) {
        Random rand = new Random();
        double valor = rand.nextDouble();
        return new BigDecimal(valor).setScale(decimais, RoundingMode.HALF_UP);
    }

}
