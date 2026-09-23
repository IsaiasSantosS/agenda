package com.estudo.agenda.infrastructure.persistence.mapper;

import com.estudo.agenda.domain.model.Pessoa.Profissional;
import com.estudo.agenda.domain.model.Pessoa.ProfissionalId;
import com.estudo.agenda.domain.model.Pessoa.PessoaId;
import com.estudo.agenda.infrastructure.persistence.entity.ProfissionalEntity;

public class ProfissionalMapper {
    public static ProfissionalEntity toEntity(Profissional Profissional) {
        if (Profissional == null) {
            return null;
        }
        ProfissionalEntity entity = new ProfissionalEntity();
        entity.setId(Profissional.getIdentificadorUUID());
        entity.setPessoaId(Profissional.getPessoaId().identificador());
        entity.setEspecialidade(Profissional.getEspecialidade());
        entity.setRegistroProfissional(Profissional.getRegistroProfissional());
        return entity;
    }

    public static Profissional toDomain(ProfissionalEntity entity) {
        if (entity == null) {
            return null;
        }
        Profissional profissional = Profissional.reconstruir(
            new ProfissionalId(entity.getId()),
            new PessoaId(entity.getPessoaId()),
            entity.getEspecialidade(),
            entity.getRegistroProfissional()
        );
        return profissional;
    }
    
}
