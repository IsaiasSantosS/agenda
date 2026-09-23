package com.estudo.agenda.infrastructure.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudo.agenda.infrastructure.persistence.entity.ProfissionalEntity;

public interface ProfissionalJpaRepository extends JpaRepository<ProfissionalEntity, UUID> {
    Optional<ProfissionalEntity> findByPessoaId(UUID pessoaId);
}
