package com.estudo.agenda.infrastructure.persistence.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

import com.estudo.agenda.infrastructure.persistence.converter.CPFConverter;
import com.estudo.agenda.infrastructure.persistence.converter.EmailConverter;
import com.estudo.agenda.shared.VOs.CPF;
import com.estudo.agenda.shared.VOs.Email;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table (name ="tb_pessoa")
@Getter 
@Setter
@NoArgsConstructor
public class PessoaEntity {

    @Id
    private UUID id;

    @Column (nullable = false, length = 100)
    private String nome;

    @Column (nullable = true, unique = true, length = 100)
    @Convert (converter = EmailConverter.class)
    private Email email;
    
    @Column (nullable = true, length = 20)
    private String telefone;

    @Convert (converter = CPFConverter.class)
    @Column (nullable = false, unique = true, length = 11)
    private CPF cpf;

    @Column (nullable = false, length = 10)
    private LocalDate dataNascimento;

    @Column (nullable = true)
    private LocalDateTime dataCadastro;

    public PessoaEntity(UUID id, String nome, Email email, String telefone, CPF cpf, LocalDate dataNascimento){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    @PrePersist 
    public void prePersist() {
        this.dataCadastro = LocalDateTime.now(ZoneId.systemDefault());
    }
}
