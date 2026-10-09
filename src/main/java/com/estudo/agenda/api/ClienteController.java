package com.estudo.agenda.api;

import java.net.URI;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudo.agenda.application.dto.Pessoa.AtualizarClienteCommand;
import com.estudo.agenda.application.dto.Pessoa.ClienteCommand;
import com.estudo.agenda.application.dto.Pessoa.ClienteResponse;
import com.estudo.agenda.application.usecase.cliente.AtualizarUseCase;
import com.estudo.agenda.application.usecase.cliente.BuscarClienteUseCase;
import com.estudo.agenda.application.usecase.cliente.CriarClienteUseCase;
import com.estudo.agenda.application.usecase.cliente.DeletarUseCase;
import com.estudo.agenda.application.usecase.cliente.ListarUseCase;
import com.estudo.agenda.domain.model.Pessoa.ClienteId;

import jakarta.validation.Valid;

@RestController 
@RequestMapping ("api/clientes")
public class ClienteController {
    
    private final CriarClienteUseCase criarClienteUseCase;
    private final BuscarClienteUseCase buscarClienteUseCase;
    private final ListarUseCase listarUseCase;
    private final AtualizarUseCase atualizarUseCase;
    private final DeletarUseCase deletarUseCase;

    public ClienteController(CriarClienteUseCase criarClienteUseCase, BuscarClienteUseCase buscarClienteUseCase, AtualizarUseCase atualizarUseCase, DeletarUseCase deletarUseCase, ListarUseCase listarUseCase) {
        this.criarClienteUseCase = criarClienteUseCase;
        this.buscarClienteUseCase = buscarClienteUseCase;
        this.listarUseCase = listarUseCase;
        this.atualizarUseCase = atualizarUseCase;
        this.deletarUseCase = deletarUseCase;
    }

    @PostMapping 
    public ResponseEntity<ClienteResponse> criar(@RequestBody  @Valid ClienteCommand command) {
        ClienteResponse response = criarClienteUseCase.execute(command);
        
         return ResponseEntity
                .created(URI.create("/api/clientes/" + response.clienteId()))
                .body(response);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<ClienteResponse> buscarPorId(@PathVariable UUID id) {
        ClienteResponse response = buscarClienteUseCase.execute(new ClienteId(id));

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<ClienteResponse>> listar(Pageable pageable) {
        return ResponseEntity.ok(listarUseCase.executar(pageable));
    }

    @PutMapping ("/{id}")
    public ResponseEntity<ClienteResponse> atualizar(
        @PathVariable UUID id, @RequestBody @Valid AtualizarClienteCommand command
    ){
        return ResponseEntity.ok(atualizarUseCase.executar(command, id));
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        deletarUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }


    
}
