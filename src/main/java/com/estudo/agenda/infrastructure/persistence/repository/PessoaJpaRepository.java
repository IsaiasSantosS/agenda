package com.estudo.agenda.infrastructure.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudo.agenda.infrastructure.persistence.entity.PessoaEntity;
import com.estudo.agenda.shared.VOs.CPF;

public interface PessoaJpaRepository extends JpaRepository<PessoaEntity, UUID> {
    Optional<PessoaEntity> findByCpf(CPF cpf);
}
