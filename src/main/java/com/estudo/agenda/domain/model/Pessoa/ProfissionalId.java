package com.estudo.agenda.domain.model.Pessoa;

import java.util.Objects;
import java.util.UUID;

public record ProfissionalId(UUID identificador) {

    public ProfissionalId {
        Objects.requireNonNull(identificador, "O identificador do profissional não pode ser nulo");
    }

    public static ProfissionalId gerarNovo() {
        return new ProfissionalId(UUID.randomUUID());
    }

    public static ProfissionalId deString(String id) {
        return new ProfissionalId(UUID.fromString(id));
    }

    @Override 
    public String toString() {
        return identificador.toString();
    }
    
}
