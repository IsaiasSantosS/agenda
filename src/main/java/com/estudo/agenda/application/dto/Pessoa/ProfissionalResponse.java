package com.estudo.agenda.application.dto.Pessoa;

import java.util.UUID;

import com.estudo.agenda.domain.model.Pessoa.Pessoa;
import com.estudo.agenda.domain.model.Pessoa.Profissional;

public record ProfissionalResponse(
    UUID profissionalId,
    UUID pessoaId,
    String nome,
    String email,
    String especialidade
) {
    public static ProfissionalResponse fromDomain(Profissional profissional, Pessoa pessoa) {
        return new ProfissionalResponse(
            profissional.getIdentificadorUUID(),
             pessoa.getIdentificadorUUID(),
             pessoa.getNome(),
             pessoa.getEmailString(),
             profissional.getEspecialidade()
            );
    }
}
