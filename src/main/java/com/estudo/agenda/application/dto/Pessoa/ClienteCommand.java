package com.estudo.agenda.application.dto.Pessoa;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public record ClienteCommand(
    @NotNull 
    String nome,
    @NotNull 
    String email,
    String telefone,
    @NotNull 
    LocalDate dataNascimento,
    @NotNull 
    String cpf,
    Integer pontosFidelidade,
    String status    
) {
    
}