package com.estudo.agenda.infrastructure.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.estudo.agenda.domain.model.Pessoa.Pessoa;
import com.estudo.agenda.domain.repository.PessoaRepository;
import com.estudo.agenda.infrastructure.persistence.entity.PessoaEntity;
import com.estudo.agenda.infrastructure.persistence.mapper.PessoaMapper;
import com.estudo.agenda.shared.VOs.CPF;

@Repository 
public class PessoaRepositoryImpl implements PessoaRepository {
    
    private final PessoaJpaRepository pessoaJpaRepository;

    public PessoaRepositoryImpl(PessoaJpaRepository pessoaJpaRepository) {
        this.pessoaJpaRepository = pessoaJpaRepository;
    }

    @Override 
    public Optional<Pessoa> buscarPorId(UUID id) {
        return pessoaJpaRepository.findById(id).map(PessoaMapper::toDomain);
    }
    
    @Override 
    public Pessoa salvar(Pessoa pessoa) {
        PessoaEntity pessoaExistente = PessoaMapper.toEntity(pessoa);
        return PessoaMapper.toDomain(pessoaJpaRepository.save(pessoaExistente));
    }

    @Override 
    public Page<PessoaEntity> buscarTodos(Pageable pageable) {
        return pessoaJpaRepository.findAll(pageable);
    }

    @Override
    public Optional<Pessoa> buscarPorCpf(CPF cpf) {
        return pessoaJpaRepository.findByCpf(cpf).map(PessoaMapper::toDomain);
    }

}
