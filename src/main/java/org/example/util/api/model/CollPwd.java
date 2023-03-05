package org.example.util.api.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

/**
 * Tabela Coll_pwd. Esta tabela contém informações históricas das senhas atribuídas aos usuários.
 * <ul>
 *     <li>CPWCOLLID    = ID Usuário.</li>
 *     <li>CPWPASSWD    = Senha</li>
 *     <li>CPWREGDT     = Data em que a senha foi definida.</li>
 *     <li>CPWSTATUS    = Status da senha.</li>
 * </ul>
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CollPwd {

    //TODO: averiguar se mantem Date ou muda para LocalDate/LocalDateTime
    String CPWCOLLID;    //ID Usuário.
    String CPWPASSWD;    //Senha
    Date CPWREGDT;       //Data em que a senha foi definida.
    String CPWSTATUS;    //Status da senha.

}
