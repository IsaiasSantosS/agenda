package com.estudo.agenda.infrastructure.persistence.mapper;

import com.estudo.agenda.infrastructure.persistence.entity.PessoaEntity;

import com.estudo.agenda.domain.model.Pessoa.Pessoa;
import com.estudo.agenda.domain.model.Pessoa.PessoaId;

public class PessoaMapper {
    public static Pessoa toDomain(PessoaEntity pessoaEntity) {
        return Pessoa.reconstruir(
            new PessoaId(pessoaEntity.getId()),
            pessoaEntity.getNome(),
            pessoaEntity.getEmail(),
            pessoaEntity.getTelefone(),
            pessoaEntity.getCpf(),
            pessoaEntity.getDataNascimento()
        );
    }

    public static PessoaEntity toEntity(Pessoa pessoa) {
        return new PessoaEntity(pessoa.getId().identificador(),
         pessoa.getNome(),
          pessoa.getEmail(),
          pessoa.getTelefone(),
           pessoa.getCpf(), pessoa.getDataNascimento());
    }
}
