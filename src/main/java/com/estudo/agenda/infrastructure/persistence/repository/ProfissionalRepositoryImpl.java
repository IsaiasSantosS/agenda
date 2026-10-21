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
    
    private final ProfissionalJpaRepository jpaProfissionalRepository;

    public ProfissionalRepositoryImpl(ProfissionalJpaRepository jpaProfissionalRepository) {
        this.jpaProfissionalRepository = jpaProfissionalRepository;
    }

    @Override 
    public Optional<Profissional> buscarPorId(UUID id) {
        return jpaProfissionalRepository.findById(id).map(ProfissionalMapper::toDomain);
    }
    
    @Override
    public Profissional salvar(Profissional profissional) {
        ProfissionalEntity ProfissionalEntity = ProfissionalMapper.toEntity(profissional);
        return ProfissionalMapper.toDomain(jpaProfissionalRepository.save(ProfissionalEntity));
    }

    @Override 
    public Page<Profissional> buscarTodos(Pageable pageable) {
        return jpaProfissionalRepository.findAll(pageable).map(ProfissionalMapper::toDomain);
    }

    @Override
    public Optional<Profissional> existePessoaId(PessoaId id) {
        return jpaProfissionalRepository.findByPessoaId(id.identificador()).map(
            ProfissionalMapper::toDomain
        );
    }

    @Override
    public void deletar(Profissional profissional) {
        jpaProfissionalRepository.delete(ProfissionalMapper.toEntity(profissional));
    }
    
}
