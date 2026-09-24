package com.estudo.agenda.domain.repository;

import java.util.Optional;
import java.util.UUID;

import com.estudo.agenda.domain.model.Pessoa.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.estudo.agenda.domain.model.Pessoa.PessoaId;

public interface ClienteRepository {
    Optional<Cliente> buscarPorId(UUID id);    
    Cliente salvar(Cliente cliente);
    Page<Cliente> buscarTodos(Pageable pageable);
    Optional<Cliente> existePessoaId(PessoaId id);
}
