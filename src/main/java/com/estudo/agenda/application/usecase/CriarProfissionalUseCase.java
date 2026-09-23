package com.estudo.agenda.application.usecase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estudo.agenda.application.dto.Pessoa.ProfissionalCommand;
import com.estudo.agenda.application.dto.Pessoa.ProfissionalResponse;
import com.estudo.agenda.domain.model.Pessoa.Pessoa;
import com.estudo.agenda.domain.model.Pessoa.Profissional;
import com.estudo.agenda.domain.repository.PessoaRepository;
import com.estudo.agenda.domain.repository.ProfissionalRepository;
import com.estudo.agenda.shared.VOs.CPF;
import com.estudo.agenda.shared.VOs.Email;

@Service
public class CriarProfissionalUseCase {
    private final PessoaRepository pessoaRepository;
    private final ProfissionalRepository profissionalRepository;

    public CriarProfissionalUseCase(PessoaRepository pessoaRepository, ProfissionalRepository profissionalRepository) {
        this.pessoaRepository = pessoaRepository;
        this.profissionalRepository = profissionalRepository;
    }

    @Transactional
    public ProfissionalResponse execute(ProfissionalCommand command) {
        Pessoa pessoa = pessoaRepository.buscarPorCpf(new CPF((command.cpf())))
                .orElseGet(() -> {
                    Pessoa novaPessoa = Pessoa.criar(command.nome(), new Email(command.email()), command.telefone(),
                            new CPF(command.cpf()), command.dataNascimento());
                    return pessoaRepository.salvar(novaPessoa);
                });

        if (profissionalRepository.existePessoaId(pessoa.getId()).isPresent()) {
            throw new IllegalArgumentException("Profissional já cadastrado.");
        }

        Profissional profissional = Profissional.criar(pessoa.getId(), command.especialidade(), command.registroProfissional());
        profissionalRepository.salvar(profissional);

        return ProfissionalResponse.fromDomain(profissional, pessoa);

    }
}
