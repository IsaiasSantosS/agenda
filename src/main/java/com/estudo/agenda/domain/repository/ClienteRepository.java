package com.estudo.agenda.domain.repository;

import java.util.Optional;
import java.util.UUID;

import com.estudo.agenda.domain.model.Pessoa.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.estudo.agenda.domain.model.Pessoa.PessoaId;
import com.estudo.agenda.infrastructure.persistence.entity.ClienteEntity;

public interface ClienteRepository {
    Optional<ClienteEntity> buscarPorId(UUID id);    
    ClienteEntity salvar(Cliente cliente);
    Page<ClienteEntity> buscarTodos(Pageable pageable);
    Optional<Cliente> existePessoaId(PessoaId id);
}
