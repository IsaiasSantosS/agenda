package com.estudo.agenda.application.usecase.profissional;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estudo.agenda.application.dto.Pessoa.ProfissionalCommand;
import com.estudo.agenda.application.dto.Pessoa.ProfissionalResponse;
import com.estudo.agenda.domain.model.Pessoa.Pessoa;
import com.estudo.agenda.domain.model.Pessoa.Profissional;
import com.estudo.agenda.domain.model.Pessoa.ProfissionalId;
import com.estudo.agenda.domain.repository.PessoaRepository;
import com.estudo.agenda.domain.repository.ProfissionalRepository;
import com.estudo.agenda.shared.VOs.CPF;
import com.estudo.agenda.shared.VOs.Email;

@Service 
public class AtualizarProfissionalUseCase {

    private final ProfissionalRepository profissionalRepository;
    private final PessoaRepository pessoaRepository;

    public AtualizarProfissionalUseCase(ProfissionalRepository profissionalRepository, PessoaRepository pessoaRepository) {
        this.profissionalRepository = profissionalRepository;
        this.pessoaRepository = pessoaRepository;
    }

    @Transactional 
    public ProfissionalResponse executar(ProfissionalCommand command, UUID id) {
        Profissional profissional = profissionalRepository.buscarPorId(id)
                .orElseThrow(IllegalArgumentException::new);

        profissional = Profissional.reconstruir(
            new ProfissionalId(id),
             profissional.getPessoaId(),
             command.especialidade(),
             command.registroProfissional());

        Pessoa pessoa = pessoaRepository.buscarPorId(profissional.getPessoaId().identificador())
                .orElseThrow(IllegalArgumentException::new);

        pessoa = Pessoa.reconstruir(pessoa.getId(), command.nome(), new Email(command.email()), command.telefone(), new CPF(command.cpf()), command.dataNascimento());

        profissionalRepository.salvar(profissional);
        pessoaRepository.salvar(pessoa);

        return ProfissionalResponse.fromDomain(profissional, pessoa);
    }
    
}
