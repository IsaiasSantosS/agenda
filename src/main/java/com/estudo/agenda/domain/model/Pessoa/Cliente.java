package com.estudo.agenda.domain.model.Pessoa;


public class Cliente {
    private ClienteId id;
    private PessoaId pessoaId;
    private StatusCliente status;
    private Integer pontosFidelidade;

    public static Cliente criar(PessoaId pessoaId) {
        Cliente cliente = new Cliente();
        cliente.id = ClienteId.gerarNovo();
        cliente.pessoaId = pessoaId;
        cliente.status = StatusCliente.ATIVO;
        cliente.pontosFidelidade = 0;

        return cliente;
    }

    public static Cliente reconstruir(ClienteId id, PessoaId pessoaId, StatusCliente status, Integer pontosFidelidade) {
        Cliente cliente = new Cliente();
        cliente.id = id;
        cliente.pessoaId = pessoaId;
        cliente.status = status;
        cliente.pontosFidelidade = pontosFidelidade;

        return cliente;
    }

    public void bloquear() {
        if (this.status == StatusCliente.BLOQUEADO) {
            throw new IllegalStateException("O cliente já está bloqueado");
        }
        this.status = StatusCliente.BLOQUEADO;
    }

    public ClienteId getId() {
        return id;
    }

    public PessoaId getPessoaId() {
        return pessoaId;
    }

    public StatusCliente getStatus() {
        return status;
    }

    public Integer getPontosFidelidade() {
        return pontosFidelidade;
    }
}