package com.estudo.agenda.application.usecase.profissional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.estudo.agenda.application.dto.Pessoa.ProfissionalResponse;
import com.estudo.agenda.domain.model.Pessoa.Pessoa;
import com.estudo.agenda.domain.repository.PessoaRepository;
import com.estudo.agenda.domain.repository.ProfissionalRepository;

@Service
public class ListarProfissionalUseCase {

    private final ProfissionalRepository profissionalRepository;
    private final PessoaRepository pessoaRepository;

    public ListarProfissionalUseCase(ProfissionalRepository profissionalRepository, PessoaRepository pessoaRepository) {
        this.profissionalRepository = profissionalRepository;
        this.pessoaRepository = pessoaRepository;
    }

    public Page<ProfissionalResponse> executar(Pageable pageable) {
        return profissionalRepository.buscarTodos(pageable)
                .map(profissional -> {
                    Pessoa pessoa = pessoaRepository.buscarPorId(profissional.getPessoaId().identificador())
                            .orElseThrow(IllegalArgumentException::new);

                    return ProfissionalResponse.fromDomain(profissional, pessoa);
                });
    }
}
