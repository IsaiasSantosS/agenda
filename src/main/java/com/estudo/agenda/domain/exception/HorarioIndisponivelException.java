package com.estudo.agenda.domain.exception;

public class HorarioIndisponivelException extends RuntimeException {
    public HorarioIndisponivelException(String message) {
        super(message);
    }
}
