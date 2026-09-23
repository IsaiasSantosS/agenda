package com.estudo.agenda.application.dto.Pessoa;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public record ProfissionalCommand(
    @NotNull 
    String nome,
    @NotNull 
    String email,
    String telefone,
    LocalDate dataNascimento,
    @NotNull 
    String cpf,
    String especialidade,
    String registroProfissional
) {
    
}
