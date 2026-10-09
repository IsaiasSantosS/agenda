package com.estudo.agenda.application.usecase.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.estudo.agenda.application.dto.Pessoa.ClienteResponse;
import com.estudo.agenda.domain.model.Pessoa.Pessoa;
import com.estudo.agenda.domain.repository.ClienteRepository;
import com.estudo.agenda.domain.repository.PessoaRepository;

@Service
public class ListarUseCase {

    private final ClienteRepository clienteRepository;
    private final PessoaRepository pessoaRepository;

    public ListarUseCase(ClienteRepository clienteRepository, PessoaRepository pessoaRepository) {
        this.clienteRepository = clienteRepository;
        this.pessoaRepository = pessoaRepository;
    }

    public Page<ClienteResponse> executar(Pageable pageable) {
        return clienteRepository.buscarTodos(pageable)
                .map(cliente -> {
                    Pessoa pessoa = pessoaRepository.buscarPorId(cliente.getPessoaId().identificador())
                            .orElseThrow(IllegalArgumentException::new);

                    return ClienteResponse.fromDomain(cliente, pessoa);
                });
    }
}
