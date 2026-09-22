package com.estudo.agenda.application.dto.Pessoa;

import java.util.UUID;
import com.estudo.agenda.domain.model.Pessoa.Pessoa;
import com.estudo.agenda.domain.model.Pessoa.PessoaId;

public record PessoaResponse(
    UUID id,
    String nome,
    String email,
    String telefone,
    String cpf,
    String dataNascimento
) {
 public PessoaResponse(PessoaId id2, String nome2, String email2, String telefone2, String cpfComFormatacao,
            String string) {
        this(id2.identificador(), nome2, email2, telefone2, cpfComFormatacao, string);
    }

 public static PessoaResponse fromDomain(Pessoa pessoa) {
        return new PessoaResponse(
            pessoa.getId(),
            pessoa.getNome(),
            pessoa.getEmail().getEmail(),
            pessoa.getTelefone(),
            pessoa.getCpf().cpfComFormatacao(),
            pessoa.getDataNascimento().toString()
        );
    }   
}
