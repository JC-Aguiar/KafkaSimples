package org.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import org.example.util.api.EntradaComum;

@Value
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class EntradaLogin extends EntradaComum {

    String email;
    String senha;

}
