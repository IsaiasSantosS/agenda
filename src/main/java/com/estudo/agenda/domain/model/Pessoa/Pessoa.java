package com.estudo.agenda.domain.model.Pessoa;

import java.time.LocalDate;

import com.estudo.agenda.shared.VOs.CPF;
import com.estudo.agenda.shared.VOs.Email;
import lombok.Getter;

@Getter
public class Pessoa {
    private final PessoaId id;
    private final String nome;
    private final Email email;
    private final String telefone;
    private final CPF cpf;
    private final LocalDate dataNascimento;

    public static Pessoa criar(String nome, Email email, String telefone, CPF cpf, LocalDate dataNascimento) {
        return new Pessoa(PessoaId.gerarNovo(), nome, email, telefone, cpf, dataNascimento);
    }

    public static Pessoa reconstruir(PessoaId id, String nome, Email email, String telefone, CPF cpf, LocalDate dataNascimento) {
        return new Pessoa(id, nome, email, telefone, cpf, dataNascimento);
    }

    private Pessoa(PessoaId id, String nome, Email email, String telefone, CPF cpf, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

}
