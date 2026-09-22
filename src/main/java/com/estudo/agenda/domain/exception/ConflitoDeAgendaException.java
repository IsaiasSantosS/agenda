package com.estudo.agenda.domain.exception;

public class ConflitoDeAgendaException extends RuntimeException {
    public ConflitoDeAgendaException(String message) {
        super(message);
    }
}
