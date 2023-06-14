package com.rossetti.contatos.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateContatoInput {

    @NotNull
    private Long id;

    @NotEmpty
    private String primeiroNome;

    @NotEmpty
    private String ultimoNome;

    @NotEmpty
    private String telefone;

    @NotEmpty
    private String email;

}
