package com.estudo.agenda.application.dto.Pessoa;

import java.util.UUID;

import com.estudo.agenda.domain.model.Pessoa.Cliente;
import com.estudo.agenda.domain.model.Pessoa.Pessoa;

public record ClienteResponse(
        UUID clienteId,
        UUID pessoaId,
        String nome,
        String email,
        String status,
        Integer pontosFidelidade
) {
    public static ClienteResponse fromDomain(Cliente cliente, Pessoa pessoa) {
        return new ClienteResponse(
                cliente.getIdentificadorUUID(),
                pessoa.getIdentificadorUUID(),
                pessoa.getNome(),
                pessoa.getEmailString(),
                cliente.getStatus().name(),
                cliente.getPontosFidelidade()
        );
    }
}
