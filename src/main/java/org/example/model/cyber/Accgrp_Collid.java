package org.example.model.cyber;

import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * Tabela Accgrp_Collid. Esta tabela contém o relacionamento entre gerentes e grupos de acesso.
 * <ul>
 *     <li>ACACGID      = Grupo de acesso.</li>
 *     <li>ACCLCOLLID   = Identificador do manipulador.</li>
 * </ul>
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Accgrp_Collid {

    Integer ACACGID;    //Grupo de acesso.
    String ACCLCOLLID;  //Identificador do manipulador.

}
