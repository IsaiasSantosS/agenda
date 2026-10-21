package com.estudo.agenda.application.usecase.profissional;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.estudo.agenda.domain.model.Pessoa.Pessoa;
import com.estudo.agenda.domain.model.Pessoa.Profissional;
import com.estudo.agenda.domain.model.Pessoa.ProfissionalId;
import com.estudo.agenda.domain.repository.PessoaRepository;
import com.estudo.agenda.domain.repository.ProfissionalRepository;

@Service 
public class DeletarProfissionalUseCase {
    private final ProfissionalRepository profissionalRepository;
    private final PessoaRepository pessoaRepository;

    public DeletarProfissionalUseCase(ProfissionalRepository profissionalRepository, PessoaRepository pessoaRepository) {
        this.profissionalRepository = profissionalRepository;
        this.pessoaRepository = pessoaRepository;
    }

    public void executar(UUID id) {
        ProfissionalId profissionalId = new ProfissionalId(id);
        Profissional profissional = profissionalRepository.buscarPorId(profissionalId.identificador())
                .orElseThrow(IllegalArgumentException::new);

        Pessoa pessoa = pessoaRepository.buscarPorId(profissional.getPessoaId().identificador())
                .orElseThrow(IllegalArgumentException::new);

        profissionalRepository.deletar(profissional);
        pessoaRepository.deletar(pessoa);
    }
}
