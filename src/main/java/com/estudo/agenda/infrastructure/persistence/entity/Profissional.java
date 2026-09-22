package com.estudo.agenda.infrastructure.persistence.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity 
@Table (name ="tb_profissional")
@Getter 
@Setter 
public class Profissional
{

    @Id 
    private UUID id;

    @Column (name = "pessoa_id", nullable = false)
    private UUID pessoaId;

    @Column (nullable = false, length = 100)    
    private String especialidade;

    @Column (nullable = false, unique = true)
    private String registroProfissional;
    
}
