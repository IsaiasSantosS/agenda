package com.estudo.agenda.domain.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.estudo.agenda.domain.model.Pessoa.PessoaId;
import com.estudo.agenda.domain.model.Pessoa.Profissional;

public interface ProfissionalRepository {
    Optional<Profissional> buscarPorId(UUID id);    
    Profissional salvar(Profissional profissional);
    Page<Profissional> buscarTodos(Pageable pageable);
    Optional<Profissional> existePessoaId(PessoaId id);
}
