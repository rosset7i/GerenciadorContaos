package com.rossetti.contatos.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rossetti.contatos.dtos.ContatoOutput;
import com.rossetti.contatos.dtos.UpdateContatoInput;
import com.rossetti.contatos.entities.Contato;
import com.rossetti.contatos.enums.ContatoResult;
import com.rossetti.contatos.services.ContatoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)

class ContatoControllerTest {

    @Mock
    private ContatoService contatoService;

    @InjectMocks
    private ContatoController contatoController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(contatoController).build();
    }

    @Test
    void shouldGetAllContatosTest() throws Exception {
        List<ContatoOutput> contatos = Arrays.asList(
                ContatoOutput.builder()
                        .id(1L)
                        .primeiroNome("Matheus")
                        .ultimoNome("Rossetti")
                        .telefone("44997090799")
                        .email("mh.rossetti2002@gmail.com")
                        .dataDeCriacao(LocalDateTime.now())
                        .build(),
                ContatoOutput.builder()
                        .id(2L)
                        .primeiroNome("Lucas")
                        .ultimoNome("Rossetti")
                        .telefone("44886690799")
                        .email("lucas.rossetti2006@gmail.com")
                        .dataDeCriacao(LocalDateTime.now())
                        .build()
        );

        Pageable pageable = PageRequest.of(0, 5);
        Page<ContatoOutput> userPage = new PageImpl<>(contatos, pageable, contatos.size());

        when(contatoService.getAllContatos(any(Pageable.class))).thenReturn(userPage);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/contatos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(contatos.size()))
                .andExpect(jsonPath("$.content[0].id").value(contatos.get(0).getId()))
                .andExpect(jsonPath("$.content[0].primeiroNome").value(contatos.get(0).getPrimeiroNome()))
                .andExpect(jsonPath("$.content[0].ultimoNome").value(contatos.get(0).getUltimoNome()))
                .andExpect(jsonPath("$.content[0].email").value(contatos.get(0).getEmail()))
                .andExpect(jsonPath("$.content[0].telefone").value(contatos.get(0).getTelefone()));

        verify(contatoService, times(1)).getAllContatos(pageable);
    }

    @Test
    void shouldCreateContatoTest() throws Exception {
        Contato contato = Contato.builder()
                .id(1L)
                .primeiroNome("Matheus")
                .ultimoNome("Rossetti")
                .telefone("44997090799")
                .email("mh.rossetti2002@gmail.com")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String contatoAsJson = objectMapper.writeValueAsString(contato);

        when(contatoService.createContato(any(Contato.class))).thenReturn(ContatoResult.OK);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/contatos")
                        .contentType(APPLICATION_JSON)
                        .content(contatoAsJson))
                        .andExpect(status().isCreated());

        verify(contatoService, times(1)).createContato(any(Contato.class));
    }

    @Test
    void shouldUpdateContatoTest() throws Exception {
        UpdateContatoInput updateContatoInput = UpdateContatoInput.builder()
                .id(1L)
                .primeiroNome("Matheus")
                .ultimoNome("Rossetti")
                .telefone("44997090799")
                .email("mh.rossetti2002@gmail.com")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String contatoAsJson = objectMapper.writeValueAsString(updateContatoInput);

        when(contatoService.updateContato(any(UpdateContatoInput.class))).thenReturn(ContatoResult.OK);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/contatos")
                        .contentType(APPLICATION_JSON)
                        .content(contatoAsJson))
                        .andExpect(status().isAccepted());

        verify(contatoService, times(1)).updateContato(any(UpdateContatoInput.class));
    }

    @Test
    void shouldDeleteContatoTest() throws Exception {
        when(contatoService.deleteContato(any(Long.class))).thenReturn(ContatoResult.OK);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/contatos/{id}", 1L))
                .andExpect(status().isAccepted());

        verify(contatoService, times(1)).deleteContato(1L);
    }
}