package org.example.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Optional;

@Getter
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RetornoFatura implements Serializable {

    String nrFatura;

    String codigoRetorno;

    String mensagem;

    public enum StatusFatura{
        COD00("COD00", "Baixa realizada com sucesso."),
        COD01("COD01", "Já baixada anteriormente."),
        COD02("COD02", "Não encontrada."),
        COD03("COD03", "Paga."),
        COD04("COD04", "Pendente."),
        COD99("COD99", "Erro inesperado.");
        public final String codigo;
        public final String mensagem;
        StatusFatura(String codigo, String mensagem) {
            this.codigo = codigo;
            this.mensagem = mensagem;
        }
    };

    private RetornoFatura(String nrFatura, StatusFatura codigoRetorno) {
        this.nrFatura = nrFatura;
        this.codigoRetorno = codigoRetorno.codigo;
        this.mensagem = codigoRetorno.mensagem;
    }

    public static RetornoFatura baixaRealizada(String nrFatura) {
        return new RetornoFatura(nrFatura, StatusFatura.COD00);
    }

    public static RetornoFatura jaBaixada(String nrFatura) {
        return new RetornoFatura(nrFatura, StatusFatura.COD01);
    }

    public static RetornoFatura inexistente(String nrFatura) {
        return new RetornoFatura(nrFatura, StatusFatura.COD02);
    }

    public static RetornoFatura paga(String nrFatura) {
        return new RetornoFatura(nrFatura, StatusFatura.COD03);
    }

    public static RetornoFatura pendente(String nrFatura) {
        return new RetornoFatura(nrFatura, StatusFatura.COD04);
    }

    public static RetornoFatura erro(String nrFatura) {
        return new RetornoFatura(nrFatura, StatusFatura.COD99);
    }

    public static RetornoFatura validarStatusFatura(String fatura, String status) {
        status = Optional.ofNullable(status).orElse("");
        return status.isEmpty() ? pendente(fatura) : paga(fatura);
    }

    public StatusFatura getStatusEnum() {
        return  StatusFatura.valueOf(this.codigoRetorno);
    }

}
