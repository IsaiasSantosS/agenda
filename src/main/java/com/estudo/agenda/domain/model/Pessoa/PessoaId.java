package com.estudo.agenda.domain.model.Pessoa;

import java.util.Objects;
import java.util.UUID;

public record PessoaId(UUID identificador) {

    public PessoaId {
        Objects.requireNonNull(identificador, "O identificador da pessoa não pode ser nulo");
    }

    public static PessoaId gerarNovo() {
        return new PessoaId(UUID.randomUUID());
    }

    public static PessoaId deString(String id) {
        return new PessoaId(UUID.fromString(id));
    }

    @Override 
    public String toString() {
        return identificador.toString();
    }
    
}
