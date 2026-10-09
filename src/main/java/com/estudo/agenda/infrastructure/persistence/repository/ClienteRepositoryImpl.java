package com.estudo.agenda.infrastructure.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import com.estudo.agenda.domain.model.Pessoa.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.estudo.agenda.domain.model.Pessoa.PessoaId;
import com.estudo.agenda.domain.repository.ClienteRepository;
import com.estudo.agenda.infrastructure.persistence.entity.ClienteEntity;
import com.estudo.agenda.infrastructure.persistence.mapper.ClienteMapper;

@Repository 
public class ClienteRepositoryImpl implements ClienteRepository {
    
    private final ClienteJpaRepository clienteJpaRepository;

    public ClienteRepositoryImpl(ClienteJpaRepository clienteJpaRepository) {
        this.clienteJpaRepository = clienteJpaRepository;
    }

    @Override 
    public Optional<Cliente> buscarPorId(UUID id) {
        return clienteJpaRepository.findById(id).map(ClienteMapper::toDomain);
    }
    
    @Override
    public Cliente salvar(Cliente cliente) {
        ClienteEntity clienteEntity = ClienteMapper.toEntity(cliente);
        clienteEntity = clienteJpaRepository.save(clienteEntity);

        return cliente;
    }

    @Override 
    public Page<Cliente> buscarTodos(Pageable pageable) {
        return clienteJpaRepository.findAll(pageable).map(ClienteMapper::toDomain);
    }

    @Override
    public Optional<Cliente> existePessoaId(PessoaId id) {
        return clienteJpaRepository.findByPessoaId(id.identificador()).map(ClienteMapper::toDomain);
    }

    @Override
    public void deletar(Cliente cliente) {
        clienteJpaRepository.deleteById(cliente.getId().identificador());
    }

}
