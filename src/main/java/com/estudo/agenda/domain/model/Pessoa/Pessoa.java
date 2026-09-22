package com.estudo.agenda.domain.model.Pessoa;

import java.time.LocalDate;

import com.estudo.agenda.shared.VOs.CPF;
import com.estudo.agenda.shared.VOs.Email;

public class Pessoa {
    private PessoaId id;
    private String nome;
    private Email email;
    private String telefone;
    private CPF cpf;
    private LocalDate dataNascimento;

    public static Pessoa criar(String nome, Email email, String telefone, CPF cpf, LocalDate dataNascimento) {
        return new Pessoa(PessoaId.gerarNovo(), nome, email, telefone, cpf, dataNascimento);
    }

    public static Pessoa reconstruir(PessoaId id, String nome, Email email, String telefone, CPF cpf, LocalDate dataNascimento) {
        Pessoa pessoa = new Pessoa(id, nome, email, telefone, cpf, dataNascimento);
        return pessoa;
    }

    private Pessoa(PessoaId id, String nome, Email email, String telefone, CPF cpf, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    public PessoaId getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Email getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public CPF getCpf() {
        return cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
}
