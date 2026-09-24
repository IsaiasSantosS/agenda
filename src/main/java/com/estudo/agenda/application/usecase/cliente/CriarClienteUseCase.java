package com.estudo.agenda.application.usecase.cliente;

import com.estudo.agenda.domain.model.Pessoa.Cliente;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estudo.agenda.application.dto.Pessoa.ClienteCommand;
import com.estudo.agenda.application.dto.Pessoa.ClienteResponse;
import com.estudo.agenda.domain.model.Pessoa.Pessoa;
import com.estudo.agenda.domain.repository.ClienteRepository;
import com.estudo.agenda.domain.repository.PessoaRepository;
import com.estudo.agenda.shared.VOs.CPF;
import com.estudo.agenda.shared.VOs.Email;

@Service
public class CriarClienteUseCase {
    private final PessoaRepository pessoaRepository;
    private final ClienteRepository clienteRepository;

    public CriarClienteUseCase(PessoaRepository pessoaRepository, ClienteRepository clienteRepository) {
        this.pessoaRepository = pessoaRepository;
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public ClienteResponse execute(ClienteCommand command) {

        Pessoa pessoa = pessoaRepository.buscarPorCpf(new CPF((command.cpf())))
                .orElseGet(() -> {
                    Pessoa novaPessoa = Pessoa.criar(command.nome(), new Email(command.email()), command.telefone(),
                            new CPF(command.cpf()), command.dataNascimento());
                    return pessoaRepository.salvar(novaPessoa);
                });

        
                    
        if (clienteRepository.existePessoaId(pessoa.getId()).isPresent()) {
            throw new IllegalArgumentException("Cliente já existe com o CPF informado.");
        }

        Cliente cliente = Cliente.criar(pessoa.getId());
        clienteRepository.salvar(cliente);
        
        return ClienteResponse.fromDomain(cliente, pessoa);
    }
}