package com.estudo.agenda.application.dto.Pessoa;

import java.util.UUID;

import com.estudo.agenda.domain.model.Pessoa.Cliente;
import com.estudo.agenda.domain.model.Pessoa.Pessoa;

public record ClienteResponse(
        UUID clienteId,
        UUID pessoaId,
        String nome,
        String email,
        String status
) {
    public static ClienteResponse fromDomain(Cliente cliente, Pessoa pessoa) {
        return new ClienteResponse(
                cliente.getId().identificador(),
                pessoa.getId().identificador(),
                pessoa.getNome(),
                pessoa.getEmail().getEmail(),
                cliente.getStatus().name()
        );
    }
}
