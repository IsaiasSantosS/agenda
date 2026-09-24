package com.estudo.agenda.api;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudo.agenda.application.dto.Pessoa.ClienteCommand;
import com.estudo.agenda.application.dto.Pessoa.ClienteResponse;
import com.estudo.agenda.application.usecase.cliente.BuscarClienteUseCase;
import com.estudo.agenda.application.usecase.cliente.CriarClienteUseCase;
import com.estudo.agenda.domain.model.Pessoa.ClienteId;

import jakarta.validation.Valid;

@RestController 
@RequestMapping ("api/clientes")
public class ClienteController {
    
    private final CriarClienteUseCase criarClienteUseCase;
    private final BuscarClienteUseCase buscarClienteUseCase;

    public ClienteController(CriarClienteUseCase criarClienteUseCase, BuscarClienteUseCase buscarClienteUseCase) {
        this.criarClienteUseCase = criarClienteUseCase;
        this.buscarClienteUseCase = buscarClienteUseCase;
    }

    @PostMapping 
    public ResponseEntity<ClienteResponse> criarCliente(@RequestBody  @Valid ClienteCommand command) {
        ClienteResponse response = criarClienteUseCase.execute(command);
        
         return ResponseEntity
                .created(URI.create("/api/clientes/" + response.clienteId()))
                .body(response);
    }

    @GetMapping ("{id}")
    public ResponseEntity<ClienteResponse> buscarCliente(@PathVariable UUID id) {
        ClienteResponse response = buscarClienteUseCase.execute(new ClienteId(id));

        return ResponseEntity.ok(response);
    }
    
}
