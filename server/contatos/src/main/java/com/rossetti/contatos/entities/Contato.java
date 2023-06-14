package com.rossetti.contatos.entities;

import com.rossetti.contatos.dtos.ContatoOutput;
import com.rossetti.contatos.dtos.UpdateContatoInput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "contato")
@Table(name = "contato")
@Builder
@Getter
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name = "primeiro_nome")
    private String primeiroNome;

    @NotEmpty
    @Column(name = "ultimo_nome")
    private String ultimoNome;

    @NotEmpty
    @Column(name = "telefone")
    private String telefone;

    @NotEmpty
    @Column(name = "email")
    private String email;

    @Column(name = "data_de_criacao", nullable = false)
    private LocalDateTime dataDeCriacao;

    @Column(name = "data_de_modificacao")
    private LocalDateTime dataDeModificacao;

    @PrePersist
    private void setDataDeCriacao(){
        dataDeCriacao = LocalDateTime.now();
    }

    @PreUpdate
    private void setDataDeModificacao(){
        dataDeModificacao = LocalDateTime.now();
    }

    public void update(UpdateContatoInput updateContatoInput){
        primeiroNome = updateContatoInput.getPrimeiroNome();
        ultimoNome = updateContatoInput.getUltimoNome();
        email = updateContatoInput.getEmail();
        telefone = updateContatoInput.getTelefone();
    }

    public static ContatoOutput convertToOutput(Contato contato){
        return ContatoOutput.builder()
                .id(contato.getId())
                .primeiroNome(contato.getPrimeiroNome())
                .ultimoNome(contato.getUltimoNome())
                .telefone(contato.getTelefone())
                .email(contato.getEmail())
                .dataDeCriacao(contato.getDataDeCriacao())
                .dataDeModificacao(contato.getDataDeModificacao())
                .build();
    }

    public static List<ContatoOutput> convertToOutputList(Page<Contato> contato){
        return contato.stream()
                .map(Contato::convertToOutput)
                .collect(Collectors.toList());
    }
}
