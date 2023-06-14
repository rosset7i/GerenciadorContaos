package com.rossetti.contatos.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContatoOutput {
    @NotEmpty
    private Long id;

    @NotEmpty
    private String primeiroNome;

    @NotEmpty
    private String ultimoNome;

    @NotEmpty
    private String telefone;

    @NotEmpty
    private String email;

    @NotNull
    private LocalDateTime dataDeCriacao;

    private LocalDateTime dataDeModificacao;

}
