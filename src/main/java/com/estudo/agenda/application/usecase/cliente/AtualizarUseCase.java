package com.estudo.agenda.application.usecase.cliente;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estudo.agenda.application.dto.Pessoa.AtualizarClienteCommand;
import com.estudo.agenda.application.dto.Pessoa.ClienteResponse;
import com.estudo.agenda.domain.model.Pessoa.Cliente;
import com.estudo.agenda.domain.model.Pessoa.ClienteId;
import com.estudo.agenda.domain.model.Pessoa.Pessoa;
import com.estudo.agenda.domain.model.Pessoa.StatusCliente;
import com.estudo.agenda.domain.repository.ClienteRepository;
import com.estudo.agenda.domain.repository.PessoaRepository;
import com.estudo.agenda.shared.VOs.CPF;
import com.estudo.agenda.shared.VOs.Email;

@Service 
public class AtualizarUseCase {

    private final ClienteRepository clienteRepository;
    private final PessoaRepository pessoaRepository;

    public AtualizarUseCase(ClienteRepository clienteRepository, PessoaRepository pessoaRepository) {
        this.clienteRepository = clienteRepository;
        this.pessoaRepository = pessoaRepository;
    }

    @Transactional 
    public ClienteResponse executar(AtualizarClienteCommand command, UUID id) {
        Cliente cliente = clienteRepository.buscarPorId(id)
                .orElseThrow(IllegalArgumentException::new);

        cliente = Cliente.reconstruir(new ClienteId(id), cliente.getPessoaId(), StatusCliente.valueOf(command.status()), command.pontosFidelidade());

        Pessoa pessoa = pessoaRepository.buscarPorId(cliente.getPessoaId().identificador())
                .orElseThrow(IllegalArgumentException::new);

        pessoa = Pessoa.reconstruir(pessoa.getId(), command.nome(), new Email(command.email()), command.telefone(), new CPF(command.cpf()), command.dataNascimento());

        clienteRepository.salvar(cliente);
        pessoaRepository.salvar(pessoa);

        return ClienteResponse.fromDomain(cliente, pessoa);
    }
    
}
