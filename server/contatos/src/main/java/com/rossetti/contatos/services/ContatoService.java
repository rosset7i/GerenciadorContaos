package com.rossetti.contatos.services;

import com.rossetti.contatos.dtos.ContatoOutput;
import com.rossetti.contatos.dtos.UpdateContatoInput;
import com.rossetti.contatos.entities.Contato;
import com.rossetti.contatos.enums.ContatoResult;
import com.rossetti.contatos.repositories.ContatoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ContatoService {

    private final ContatoRepository contatoRepository;

    public Page<ContatoOutput> getAllContatos(Pageable pageable){
        Page<Contato> contatosPage = contatoRepository.findAll(pageable);

        List<ContatoOutput> contatos = Contato.convertToOutputList(contatosPage);

        return new PageImpl<>(contatos, pageable, contatosPage.getTotalElements());
    }

    public ContatoResult createContato(Contato contato){
        contatoRepository.save(contato);

        return ContatoResult.OK;
    }

    public ContatoResult updateContato(UpdateContatoInput updateContatoInput){
        if(updateContatoInput.getId() == null){
            return ContatoResult.ERROR;
        }
        Contato contato = contatoRepository.findById(updateContatoInput.getId()).orElseThrow();

        contato.update(updateContatoInput);

        contatoRepository.save(contato);

        return ContatoResult.OK;
    }

    public ContatoResult deleteContato(Long id){
        contatoRepository.deleteById(id);

        return ContatoResult.OK;
    }

}
