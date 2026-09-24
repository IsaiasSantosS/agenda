package com.estudo.agenda.infrastructure.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.estudo.agenda.domain.model.Pessoa.PessoaId;
import com.estudo.agenda.domain.model.Pessoa.Profissional;
import com.estudo.agenda.domain.repository.ProfissionalRepository;
import com.estudo.agenda.infrastructure.persistence.entity.ProfissionalEntity;
import com.estudo.agenda.infrastructure.persistence.mapper.ProfissionalMapper;

@Repository 
public class ProfissionalRepositoryImpl implements ProfissionalRepository {
    
    private final ProfissionalJpaRepository jpaRepository;

    public ProfissionalRepositoryImpl(ProfissionalJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override 
    public Optional<Profissional> buscarPorId(UUID id) {
        return jpaRepository.findById(id).map(ProfissionalMapper::toDomain);
    }
    
    @Override
    public Profissional salvar(Profissional profissional) {
        ProfissionalEntity ProfissionalEntity = ProfissionalMapper.toEntity(profissional);
        return ProfissionalMapper.toDomain(jpaRepository.save(ProfissionalEntity));
    }

    @Override 
    public Page<Profissional> buscarTodos(Pageable pageable) {
        return jpaRepository.findAll(pageable).map(ProfissionalMapper::toDomain);
    }

    @Override
    public Optional<Profissional> existePessoaId(PessoaId id) {
        return jpaRepository.findByPessoaId(id.identificador()).map(
            ProfissionalMapper::toDomain
        );
    }
    
}
