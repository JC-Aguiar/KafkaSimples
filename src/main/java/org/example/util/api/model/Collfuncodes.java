package org.example.util.api.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * Tabela Collfuncodes. Contém informações dos códigos cujo acesso é controlado. Para cada grupo e gerente indique os
 * códigos controles que você tem permissão para usar.
 * <ul>
 *     <li>CLACCTG  = Grupo.</li>
 *     <li>CLCOLLID = Manipulador.</li>
 *     <li>CLFTYPE  = Tipo de código.</li>
 *     <li>CLFUNREQ = Código de função.</li>
 * </ul>
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Collfuncodes {

    String CLACCTG;     //Grupo.
    String CLCOLLID;    //Manipulador.
    String CLFTYPE;     //Tipo de código.
    String CLFUNREQ;    //Código de função.
}
