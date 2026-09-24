package com.estudo.agenda.application.usecase.cliente;

import org.springframework.stereotype.Service;

import com.estudo.agenda.application.dto.Pessoa.ClienteResponse;
import com.estudo.agenda.domain.model.Pessoa.Cliente;
import com.estudo.agenda.domain.model.Pessoa.ClienteId;
import com.estudo.agenda.domain.model.Pessoa.Pessoa;
import com.estudo.agenda.domain.repository.ClienteRepository;
import com.estudo.agenda.domain.repository.PessoaRepository;
import com.estudo.agenda.infrastructure.persistence.entity.ClienteEntity;
import com.estudo.agenda.infrastructure.persistence.mapper.ClienteMapper;

@Service 
public class BuscarClienteUseCase {

    private final ClienteRepository clienteRepository;
    private final PessoaRepository pessoaRepository;

    public BuscarClienteUseCase(ClienteRepository clienteRepository, PessoaRepository pessoaRepository){
        this.clienteRepository = clienteRepository;
        this.pessoaRepository = pessoaRepository;
    }

    public ClienteResponse execute(ClienteId clienteId){
        Cliente cliente = clienteRepository.buscarPorId(clienteId.identificador())
        .orElseThrow(IllegalArgumentException::new);


        Pessoa pessoa = pessoaRepository.buscarPorId(cliente.getPessoaId().identificador()).orElseThrow(IllegalArgumentException::new);

        return  ClienteResponse.fromDomain(cliente, pessoa);

    }
}