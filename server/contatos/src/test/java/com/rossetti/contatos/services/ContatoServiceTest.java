package com.rossetti.contatos.services;

import com.rossetti.contatos.dtos.ContatoOutput;
import com.rossetti.contatos.dtos.UpdateContatoInput;
import com.rossetti.contatos.entities.Contato;
import com.rossetti.contatos.enums.ContatoResult;
import com.rossetti.contatos.repositories.ContatoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContatoServiceTest {

    @Mock
    private ContatoRepository contatoRepository;

    @InjectMocks
    private ContatoService contatoService;

    @Test
    void shouldGetAllContatosTest() {
        List<Contato> contatos = Arrays.asList(
                Contato.builder()
                        .id(1L)
                        .primeiroNome("Matheus")
                        .ultimoNome("Rossetti")
                        .telefone("44997090799")
                        .email("mh.rossetti2002@gmail.com")
                        .dataDeCriacao(LocalDateTime.now())
                        .build(),
                Contato.builder()
                        .id(2L)
                        .primeiroNome("Lucas")
                        .ultimoNome("Rossetti")
                        .telefone("44886690799")
                        .email("lucas.rossetti2006@gmail.com")
                        .dataDeCriacao(LocalDateTime.now())
                        .build()
        );

        Pageable pageable = mock(Pageable.class);

        Page<Contato> contatosPage = new PageImpl<>(contatos);
        when(contatoRepository.findAll(pageable)).thenReturn(contatosPage);

        Page<ContatoOutput> result = contatoService.getAllContatos(pageable);

        verify(contatoRepository, times(1)).findAll(pageable);

        assertEquals(2, result.getTotalElements());
        assertEquals(contatos.get(0).getPrimeiroNome(), result.getContent().get(0).getPrimeiroNome());
        assertEquals(contatos.get(0).getUltimoNome(), result.getContent().get(0).getUltimoNome());
        assertEquals(contatos.get(0).getTelefone(), result.getContent().get(0).getTelefone());
        assertEquals(contatos.get(0).getEmail(), result.getContent().get(0).getEmail());
        assertEquals(contatos.get(0).getDataDeCriacao(), result.getContent().get(0).getDataDeCriacao());
    }

    @Test
    void shouldCreateContatoTest() {
        Contato contato = Contato.builder()
                .id(1L)
                .primeiroNome("Matheus")
                .ultimoNome("Rossetti")
                .telefone("44997090799")
                .email("mh.rossetti2002@gmail.com")
                .dataDeCriacao(LocalDateTime.now())
                .build();

        when(contatoRepository.save(contato)).thenReturn(contato);

        ContatoResult result = contatoService.createContato(contato);

        verify(contatoRepository, times(1)).save(contato);

        assertEquals(ContatoResult.OK, result);
    }

    @Test
    void shouldUpdateContatoTest() {
        UpdateContatoInput updateContatoInput = UpdateContatoInput.builder()
                .id(1L)
                .primeiroNome("NovoNome")
                .ultimoNome("NomeUltimoNome")
                .telefone("44997096666")
                .email("mh.rossetti2222@gmail.com")
                .build();

        Contato contato = Contato.builder()
                .id(1L)
                .primeiroNome("Matheus")
                .ultimoNome("Rossetti")
                .telefone("44997090799")
                .email("mh.rossetti2002@gmail.com")
                .dataDeCriacao(LocalDateTime.now())
                .build();

        when(contatoRepository.findById(contato.getId())).thenReturn(Optional.of(contato));

        ContatoResult result = contatoService.updateContato(updateContatoInput);

        verify(contatoRepository, times(1)).findById(updateContatoInput.getId());
        verify(contatoRepository, times(1)).save(contato);

        assertEquals(ContatoResult.OK, result);
        assertEquals(contato.getId(), updateContatoInput.getId());
        assertEquals(contato.getPrimeiroNome(), updateContatoInput.getPrimeiroNome());
        assertEquals(contato.getUltimoNome(), updateContatoInput.getUltimoNome());
        assertEquals(contato.getTelefone(), updateContatoInput.getTelefone());
        assertEquals(contato.getEmail(), updateContatoInput.getEmail());
    }

    @Test
    void shouldDeleteContatoTest() {
        Long idToDelete = 1L;

        ContatoResult result = contatoService.deleteContato(idToDelete);

        verify(contatoRepository, times(1)).deleteById(idToDelete);

        assertEquals(ContatoResult.OK, result);
    }
}