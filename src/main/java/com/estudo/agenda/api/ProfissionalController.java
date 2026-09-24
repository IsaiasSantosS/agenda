package com.estudo.agenda.api;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudo.agenda.application.dto.Pessoa.ProfissionalCommand;
import com.estudo.agenda.application.dto.Pessoa.ProfissionalResponse;
import com.estudo.agenda.application.usecase.profissional.CriarProfissionalUseCase;

import jakarta.validation.Valid;

@RestController 
@RequestMapping ("api/profissionais")
public class ProfissionalController {

    private final CriarProfissionalUseCase criarProfissionalUseCase;

    public ProfissionalController(CriarProfissionalUseCase criarProfissionalUseCase) {
        this.criarProfissionalUseCase = criarProfissionalUseCase;
    }

    @PostMapping 
    public ResponseEntity<ProfissionalResponse> criarProfissional(@RequestBody @Valid ProfissionalCommand command) {
     ProfissionalResponse response = criarProfissionalUseCase.execute(command);

     return ResponseEntity.created(URI.create("/api/profissionais/"+response.profissionalId()))
     .body(response);
    }
    
}
