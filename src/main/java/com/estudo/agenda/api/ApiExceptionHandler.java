package com.estudo.agenda.api;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.estudo.agenda.application.dto.ErroResponse;
import com.estudo.agenda.domain.exception.ConflitoDeAgendaException;

@RestControllerAdvice 
public class ApiExceptionHandler {

    @ExceptionHandler (ConflitoDeAgendaException.class)
    public ResponseEntity<ErroResponse> handleConflitoAgenda(ConflitoDeAgendaException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(new ErroResponse("CONFLITO_AGENDA", ex.getMessage()));
    }

    @ExceptionHandler (IllegalArgumentException.class)
    public ResponseEntity<ErroResponse> handleValidacao(IllegalArgumentException ex) {
        return ResponseEntity.badRequest()
        .body(new ErroResponse("DADOS_INVALIDOS", ex.getMessage()));
    }

    @ExceptionHandler (MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> handleBeanValidation(MethodArgumentNotValidException ex) {
        String mensagem = ex.getBindingResult().getFieldErrors().stream()
        .map(f -> f.getField() + ": " + f.getDefaultMessage())
    .collect(Collectors.joining(", "));

    return ResponseEntity.badRequest()
    .body(new ErroResponse("VALIDACAO", mensagem));
    }
}
