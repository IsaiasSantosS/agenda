package com.estudo.agenda.application.usecase.profissional;

import org.springframework.stereotype.Service;

import com.estudo.agenda.application.dto.Pessoa.ProfissionalResponse;
import com.estudo.agenda.domain.model.Pessoa.Pessoa;
import com.estudo.agenda.domain.model.Pessoa.Profissional;
import com.estudo.agenda.domain.model.Pessoa.ProfissionalId;
import com.estudo.agenda.domain.repository.PessoaRepository;
import com.estudo.agenda.domain.repository.ProfissionalRepository;

@Service 
public class BuscarProfissionalUseCase {

    private final ProfissionalRepository profissonalRepository;
    private final PessoaRepository pessoaRepository;

    public BuscarProfissionalUseCase(ProfissionalRepository profissonalRepository, PessoaRepository pessoaRepository){
        this.profissonalRepository = profissonalRepository;
        this.pessoaRepository = pessoaRepository;
    }

    public ProfissionalResponse execute(ProfissionalId profissionalId) {
        Profissional profissional = profissonalRepository.buscarPorId(profissionalId.identificador())
        .orElseThrow(IllegalArgumentException::new);


        Pessoa pessoa = pessoaRepository.buscarPorId(profissional.getPessoaId().identificador()).orElseThrow(IllegalArgumentException::new);

        return  ProfissionalResponse.fromDomain(profissional, pessoa);

    }
}