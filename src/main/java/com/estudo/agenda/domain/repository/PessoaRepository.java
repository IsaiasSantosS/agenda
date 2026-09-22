package com.estudo.agenda.domain.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.estudo.agenda.domain.model.Pessoa.Pessoa;
import com.estudo.agenda.infrastructure.persistence.entity.PessoaEntity;
import com.estudo.agenda.shared.VOs.CPF;

public interface PessoaRepository {
    Optional<Pessoa> buscarPorId(UUID id);    
    Optional<Pessoa> buscarPorCpf(CPF cpf);
    Pessoa salvar(Pessoa pessoa);
    Page<PessoaEntity> buscarTodos(Pageable pageable);
}
