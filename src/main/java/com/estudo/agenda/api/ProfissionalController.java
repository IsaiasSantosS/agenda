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

import com.estudo.agenda.application.dto.Pessoa.ProfissionalCommand;
import com.estudo.agenda.application.dto.Pessoa.ProfissionalResponse;
import com.estudo.agenda.application.usecase.profissional.AtualizarProfissionalUseCase;
import com.estudo.agenda.application.usecase.profissional.BuscarProfissionalUseCase;
import com.estudo.agenda.application.usecase.profissional.CriarProfissionalUseCase;
import com.estudo.agenda.application.usecase.profissional.DeletarProfissionalUseCase;
import com.estudo.agenda.application.usecase.profissional.ListarProfissionalUseCase;
import com.estudo.agenda.domain.model.Pessoa.ProfissionalId;

import jakarta.validation.Valid;

@RestController 
@RequestMapping ("api/profissionais")
public class ProfissionalController {

    private final CriarProfissionalUseCase criarProfissionalUseCase;
    private final BuscarProfissionalUseCase buscarProfissionalUseCase;
    private final AtualizarProfissionalUseCase atualizarProfissionalUseCase;
    private final DeletarProfissionalUseCase deletarProfissionalUseCase;
    private final ListarProfissionalUseCase listarProfissionalUseCase;

    public ProfissionalController(CriarProfissionalUseCase criarProfissionalUseCase, BuscarProfissionalUseCase buscarProfissionalUseCase, AtualizarProfissionalUseCase atualizarProfissionalUseCase, DeletarProfissionalUseCase deletarProfissionalUseCase, ListarProfissionalUseCase listarProfissionalUseCase) {
        this.criarProfissionalUseCase = criarProfissionalUseCase;
        this.buscarProfissionalUseCase = buscarProfissionalUseCase;
        this.atualizarProfissionalUseCase = atualizarProfissionalUseCase;
        this.deletarProfissionalUseCase = deletarProfissionalUseCase;
        this.listarProfissionalUseCase = listarProfissionalUseCase;
    }

    @PostMapping 
    public ResponseEntity<ProfissionalResponse> criarProfissional(@RequestBody @Valid ProfissionalCommand command) {
     ProfissionalResponse response = criarProfissionalUseCase.execute(command);

     return ResponseEntity.created(URI.create("/api/profissionais/"+response.profissionalId()))
     .body(response);
    }

    @GetMapping ("{id}")
    public ResponseEntity<ProfissionalResponse> obterProfissional(@PathVariable UUID id) {
        ProfissionalResponse response = buscarProfissionalUseCase.execute(new ProfissionalId(id));
        return ResponseEntity.ok(response);
    }

    @GetMapping 
    public ResponseEntity<Page<ProfissionalResponse>> listarProfissionais(Pageable pageable) {
        Page<ProfissionalResponse> response = listarProfissionalUseCase.executar(pageable);
        return ResponseEntity.ok(response);
    }

    @PutMapping ("{id}")
    public ResponseEntity<ProfissionalResponse> atualizarProfissional(@PathVariable UUID id, @RequestBody @Valid ProfissionalCommand command) {
        ProfissionalResponse response = atualizarProfissionalUseCase.executar(command, id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping ("{id}")
    public ResponseEntity<Void> deletarProfissional(@PathVariable UUID id) {
        deletarProfissionalUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }
}
