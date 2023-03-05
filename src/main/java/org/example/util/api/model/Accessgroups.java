package org.example.util.api.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * Contém informações sobre os grupos que cada gerente pode gerenciar.
 * Esta tabela substitui as informações que nas versões anteriores eram registradas no campo
 * clrtype da tabela collid, com o valor “1” ou no campo collrevgrp.
 * <ul>
 *     <li>CLCOLLID = Manipulador.</li>
 *     <li>CLREVGRP = Indicador de revisão de outros grupos.</li>
 * </ul>
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Accessgroups {

    String CLCOLLID;    //Manipulador.
    String CLREVGRP;    //Indicador de revisão de outros grupos.

}
