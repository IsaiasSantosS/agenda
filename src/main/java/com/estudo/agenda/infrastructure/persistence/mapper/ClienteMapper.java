package com.estudo.agenda.infrastructure.persistence.mapper;

import com.estudo.agenda.domain.model.Pessoa.Cliente;
import com.estudo.agenda.domain.model.Pessoa.ClienteId;
import com.estudo.agenda.domain.model.Pessoa.PessoaId;
import com.estudo.agenda.infrastructure.persistence.entity.ClienteEntity;

public class ClienteMapper {
    public static ClienteEntity toEntity(Cliente cliente) {
        if (cliente == null) {
            return null;
        }
        ClienteEntity entity = new ClienteEntity();
        entity.setId(cliente.getId().identificador());
        entity.setPessoaId(cliente.getPessoaId().identificador());
        entity.setPontosFidelidade(cliente.getPontosFidelidade());
        entity.setStatus(cliente.getStatus());
        return entity;
    }

    public static Cliente toDomain(ClienteEntity entity) {
        if (entity == null) {
            return null;
        }
        Cliente cliente = Cliente.reconstruir(
            new ClienteId(entity.getId()),
            new PessoaId(entity.getPessoaId()),
            entity.getStatus(),
            entity.getPontosFidelidade()
        );
        return cliente;
    }
    
}
