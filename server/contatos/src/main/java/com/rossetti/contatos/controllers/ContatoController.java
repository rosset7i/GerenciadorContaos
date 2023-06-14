package com.rossetti.contatos.controllers;

import com.rossetti.contatos.dtos.ContatoOutput;
import com.rossetti.contatos.dtos.UpdateContatoInput;
import com.rossetti.contatos.entities.Contato;
import com.rossetti.contatos.services.ContatoService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@Controller
@AllArgsConstructor
@RequestMapping("api/v1/contatos")
public class ContatoController {

    private final ContatoService contatoService;

    @GetMapping
    public ResponseEntity<Page<ContatoOutput>> getAllContatos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam @Nullable String[] sort
    ){
        Pageable pageable = PageRequest.of(page,size);

        if(sort != null){
            pageable = PageRequest.of(page,size)
                    .withSort(Sort.Direction.fromString(sort[1]), sort[0]);
        }

        return ResponseEntity.ok(contatoService.getAllContatos(pageable));
    }

    @PostMapping
    public ResponseEntity createContato(@RequestBody @Valid Contato contato){
        contatoService.createContato(contato);
        return ResponseEntity
                .created(URI.create("api/v1/contatos"))
                .build();
    }

    @PutMapping
    public ResponseEntity updateContato(@RequestBody @Valid UpdateContatoInput updateContatoInput){
        contatoService.updateContato(updateContatoInput);

        return ResponseEntity.accepted()
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteContato(@PathVariable Long id){
        contatoService.deleteContato(id);

        return ResponseEntity.accepted()
                .build();
    }
}
