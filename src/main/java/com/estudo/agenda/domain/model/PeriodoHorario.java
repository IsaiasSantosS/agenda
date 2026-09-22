package com.estudo.agenda.domain.model;

public class PeriodoHorario {
    private String horaInicio;
    private String horaFim;

    public PeriodoHorario(String horaInicio, String horaFim) {
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
    }

    public boolean isPeriodoValido() {
        return this.horaInicio.compareTo(this.horaFim) < 0;
    }
}
