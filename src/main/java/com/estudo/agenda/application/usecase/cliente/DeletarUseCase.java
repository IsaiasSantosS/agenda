package com.estudo.agenda.application.usecase.cliente;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.estudo.agenda.domain.model.Pessoa.Cliente;
import com.estudo.agenda.domain.model.Pessoa.ClienteId;
import com.estudo.agenda.domain.model.Pessoa.Pessoa;
import com.estudo.agenda.domain.repository.ClienteRepository;
import com.estudo.agenda.domain.repository.PessoaRepository;

@Service 
public class DeletarUseCase {
    private final ClienteRepository clienteRepository;
    private final PessoaRepository pessoaRepository;

    public DeletarUseCase(ClienteRepository clienteRepository, PessoaRepository pessoaRepository) {
        this.clienteRepository = clienteRepository;
        this.pessoaRepository = pessoaRepository;
    }

    public void executar(UUID id) {
        ClienteId clienteId = new ClienteId(id);
        Cliente cliente = clienteRepository.buscarPorId(clienteId.identificador())
                .orElseThrow(IllegalArgumentException::new);

        Pessoa pessoa = pessoaRepository.buscarPorId(cliente.getPessoaId().identificador())
                .orElseThrow(IllegalArgumentException::new);

        clienteRepository.deletar(cliente);
        pessoaRepository.deletar(pessoa);
    }
}
