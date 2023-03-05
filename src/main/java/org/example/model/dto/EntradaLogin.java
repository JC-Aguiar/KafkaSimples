package org.example.model.dto;

import br.com.ppware.app.api.EntradaComum;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@Value
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class EntradaLogin extends EntradaComum {

    String email;
    String senha;

}
