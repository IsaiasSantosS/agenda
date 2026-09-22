package com.estudo.agenda.infrastructure.persistence.entity;

import java.util.UUID;

import com.estudo.agenda.domain.model.Pessoa.StatusCliente;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table (name ="tb_cliente")
@Getter 
@Setter
@NoArgsConstructor 
@AllArgsConstructor  
public class ClienteEntity {

    @Id
    private UUID id;

    @Column (name = "pessoa_id", nullable = false)
    private UUID pessoaId;

    @Column (name = "pontos_fidelidade", nullable = false)
    private Integer pontosFidelidade;

    @Column (name = "status", nullable = false)
    @Enumerated (EnumType.STRING)
    private StatusCliente status;

}
