package org.example.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.model.cyber.*;

/**
 * Entidade responsável pelo acesso ao sistema. <br/>
 * Senha Criptografada: Qwert123    = 'EA8182F5B9B79598B79A5DEB81932488' (MD5) <br/>
 * Tabelas:
 * <ul>
 *    <li>  {@link Collid}          = Usuarios do sistema (Configuração) </li>
 *    <li>  {@link CollPwd}         = Senhas dos Usuarios do sistema (Configuração) </li>
 *    <li>  {@link Accessgroups }   = Grupos de Acesso (Configuração) </li>
 *    <li>  {@link Accgrp_Collid}   = Associação dos Usuários com os grupos de acesso para acesso as contas (Configuração) </li>
 *    <li>  {@link Collrevgroups}   = Associação dos Usuários com os grupos de acesso para revisão das contas (Configuração) </li>
 *    <li>  Collrevqs               = Associação dos Usuários com as filas de cobrança </li>
 *    <li>  Collautoqs              = Associação dos Usuários com as filas de cobrança (automático) </li>
 *    <li>  {@link Collfuncodes}    = Associação dos Usuários com os CA CR CL </li>
 *    <li>  Progcat                 = Cadastro dos Programas </li>
 *    <li>  Menu                    = Cadastro dos Menus (Console) </li>
 *    <li>  Prgcat_accgrp => Associação Menu x Grupo de acesso </li>
 * </ul>
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EntidadeUsuario  {

    Collid collid;
    CollPwd collPwd;
    Accessgroups accessgroups;
    Accgrp_Collid accgrpCollid;
    Collrevgroups collrevgroups;

}
