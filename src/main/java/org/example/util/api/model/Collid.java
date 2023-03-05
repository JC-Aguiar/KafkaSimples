package org.example.util.api.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

/**
 * Tabela Collid. Esta tabela contém todos os dados gerais dos gestores.
 * Nesta versão, o campo clrtype, que indica o tipo de registro, pode ter o valor 0 para indicar que o registro
 * tem os dados de cada gerente.
 * <ul
 *     <li>CLAGENCY     = Agência onde o gerente trabalha.</li>
 *     <li>CLCOLLID     = Código do	gerenciador.</li>
 *     <li>CLCTYPE      = Tipo de gestor: Nº de CyberLegal (1=Auxiliar, 2=Gerente, 3=Supervisor).</li>
 *     <li>CLDEPT       = Departamento ao qual o gerente pertence.</li>
 *     <li>CLENABLED    = Indica se	o	usuário	está ativo.</li>
 *     <li>CLEXT        = Ramal	telefônico</li>
 *     <li>CLGROUP      = Permissão para revisar contas de outro supervisor (S=sim, N=não).</li>
 *     <li>CLLAT        = Latitude	do	ponto inicial do manipulador.</li>
 *     <li>CLLDAPID     = Identificador	no LDAP (se aplicável).</li>
 *     <li>CLLNG        = Longitude	do ponto inicial do manipulador.</li>
 *     <li>CLLSTACSDT   = Data do último acesso.</li>
 *     <li>CLLTYPE      = Tipo de gerente para CyberLegal.</li>
 *     <li>CLMAIL       = E-mail.</li>
 *     <li>CLMODE       = NÃO USADO</li>
 *     <li>CLNAME       = Nome do gerenciador (usuário).</li>
 *     <li>NIVEL        = Posição.</li>
 *     <li>CLPASSWD     = Senha.</li>
 *     <li>CLPHONE      = Telefone do gerente.</li>
 *     <li>CLPRINT      = Impressora de	destino.</li>
 *     <li>CLPROF       = Perfil do	usuário.</li>
 *     <li>CLRONLY      = Sinalizador para indicar se o usuário tem acesso somente a leitura.
 *                            Opção válida apenas para assistentes e gerentes (não para supervisores).</li>
 *     <li>CLRTYPE      = NÃO USADO.</li>
 *     <li>CLSALCD      = Código de	saudação. Tem LOV chamado SALCODES.</li>
 *     <li>CLSECS       = Número de	segundos de	inatividade	permitidos para o programa de gerenciamento.</li>
 *     <li>CLSSNUM      = Número de	identificação do gerenciador exclusivo (RFC).</li>
 *     <li>CLSTATUS     = Status da	senha.(1=Expirado, 2=Aberto, etc).</li>
 *     <li>CLSUPV       = Gerent supervisor.</li>
 *     <li>CLTITLE      = Posição.</li>
 *     <li>CLUTYPE      = Tipo	de	usuário (1=Interno, 2=Externo).</li>
 * </ul>
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Collid {

    //TODO: averiguar se mantem Date ou muda para LocalDate/LocalDateTime
    String CLAGENCY;    //Agência onde o gerente trabalha.
    String CLCOLLID;    //Código do	gerenciador.
    Integer CLCTYPE;    //Tipo de gestor: Nº de CyberLegal (1=Auxiliar, 2=Gerente, 3=Supervisor).
    String CLDEPT;      //Departamento ao qual o gerente pertence.
    String CLENABLED;   //Indica se	o	usuário	está ativo.
    String CLEXT;       //Ramal	telefônico
    String CLGROUP;     //Permissão para revisar contas de outro supervisor (S=sim, N=não).
    Integer CLLAT;      //Latitude	do	ponto inicial do manipulador.
    String CLLDAPID;    //Identificador	no LDAP (se aplicável).
    Integer CLLNG;      //Longitude	do ponto inicial do manipulador.
    Date CLLSTACSDT;    //Data do último acesso.
    Integer CLLTYPE;    //Tipo de gerente para CyberLegal.
    String CLMAIL;      //E-mail.
    String CLMODE;      //NÃO USADO
    String CLNAME;      //Nome do gerenciador (usuário).
    String NIVEL;       //Posição.
    String CLPASSWD;    //Senha.
    String CLPHONE;     //Telefone do gerente.
    String CLPRINT;     //Impressora de	destino.
    String CLPROF;      //Perfil do	usuário.
    String CLRONLY;     //Sinalizador para indicar se o usuário tem acesso somente a leitura.
                        //Opção válida apenas para assistentes e gerentes (não para supervisores).
    Integer CLRTYPE;    //NÃO USADO.
    Integer CLSALCD;    //Código de	saudação. Tem LOV chamado SALCODES.
    Integer CLSECS;     //Número de	segundos de	inatividade	permitidos para o programa de gerenciamento.
    String CLSSNUM;     //Número de	identificação do gerenciador exclusivo (RFC).
    String CLSTATUS;    //Status da	senha.(1=Expirado, 2=Aberto, etc).
    String CLSUPV;      //Gerent supervisor.
    String CLTITLE;     //Posição.
    String CLUTYPE;     //Tipo	de	usuário (1=Interno, 2=Externo).

}
