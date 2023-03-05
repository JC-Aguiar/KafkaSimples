package org.example.util.api.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * Tabela Collrevgroups. Nos grupos de acesso, o número do grupo e seu nome ou descrição são indicados para vinculá-lo
 * aos programas e em seguida, defina os usuários inscritos em cada grupo.
 * Podem ser definidos no máximo 32 grupos de acesso.
 * <ul>
 *     <li> ACGID   = Identificador do grupo de acesso.</li>
 *     <li>ACGNAME  = Descrição do grupo de acesso.</li>
 * </ul>
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Collrevgroups {

    Integer ACGID;      //Identificador do grupo de acesso.
    String ACGNAME;     //Descrição do grupo de acesso.

}
