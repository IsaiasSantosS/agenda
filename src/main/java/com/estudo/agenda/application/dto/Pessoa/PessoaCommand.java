package com.estudo.agenda.application.dto.Pessoa;

public record PessoaCommand(
    String nome,
    String email,
    String telefone,
    String cpf,
    String dataNascimento
) {
    
}
