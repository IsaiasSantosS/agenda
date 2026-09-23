package com.estudo.agenda.domain.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.estudo.agenda.domain.model.Pessoa.PessoaId;
import com.estudo.agenda.domain.model.Pessoa.Profissional;
import com.estudo.agenda.infrastructure.persistence.entity.ProfissionalEntity;

public interface ProfissionalRepository {
    Optional<ProfissionalEntity> buscarPorId(UUID id);    
    ProfissionalEntity salvar(Profissional profissional);
    Page<ProfissionalEntity> buscarTodos(Pageable pageable);
    Optional<Profissional> existePessoaId(PessoaId id);
}
