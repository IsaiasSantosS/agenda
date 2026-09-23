package com.estudo.agenda.domain.model.Pessoa;

import java.util.UUID;

import lombok.Getter;

@Getter 
public class Profissional {
    private ProfissionalId id;
    private PessoaId pessoaId;
    private String especialidade;
    private String registroProfissional;

    public static Profissional criar(PessoaId pessoaId, String especialidade, String registroProfissional) {
        Profissional profissional = new Profissional();
        profissional.id = ProfissionalId.gerarNovo();
        profissional.pessoaId = pessoaId;
        profissional.especialidade = especialidade;
        profissional.registroProfissional = registroProfissional;

        return profissional;
    }

    public static Profissional reconstruir(ProfissionalId profissionalId, 
        PessoaId pessoaId, String especialidade, String registroProfissional
    ){
        Profissional profissional = new Profissional();
        profissional.id = profissionalId;
        profissional.pessoaId = pessoaId;
        profissional.especialidade = especialidade;
        profissional.registroProfissional = registroProfissional;

        return profissional;
    }

    public UUID getIdentificadorUUID(){
        return id.identificador();
    }
}
