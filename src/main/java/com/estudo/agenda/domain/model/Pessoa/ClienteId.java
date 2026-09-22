package com.estudo.agenda.domain.model.Pessoa;

import java.util.Objects;
import java.util.UUID;

public record ClienteId(UUID identificador) {

    public ClienteId {
        Objects.requireNonNull(identificador, "O identificador do cliente não pode ser nulo");
    }

    public static ClienteId deString(String id) {
        return new ClienteId(UUID.fromString(id));
    }

    public static ClienteId gerarNovo() {
        return new ClienteId(UUID.randomUUID());
    }

    @Override 
    public String toString() {
        return identificador.toString();
    }
    
}
