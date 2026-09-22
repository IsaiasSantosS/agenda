package com.estudo.agenda.api.rest;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudo.agenda.application.dto.Pessoa.ClienteCommand;
import com.estudo.agenda.application.dto.Pessoa.ClienteResponse;
import com.estudo.agenda.application.usecase.CriarClienteUseCase;

import jakarta.validation.Valid;

@RestController 
@RequestMapping ("api/clientes")
public class ClienteController {
    
    private final CriarClienteUseCase criarClienteUseCase;

    public ClienteController(CriarClienteUseCase criarClienteUseCase) {
        this.criarClienteUseCase = criarClienteUseCase;
    }

    @PostMapping 
    public ResponseEntity<ClienteResponse> criarCliente(@RequestBody  @Valid ClienteCommand command) {
        ClienteResponse response = criarClienteUseCase.execute(command);
        
         return ResponseEntity
                .created(URI.create("/api/clientes/" + response.clienteId()))
                .body(response);
    }
    
}
