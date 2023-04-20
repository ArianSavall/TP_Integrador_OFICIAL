package org.Modelos;

import java.util.List;

public class Fase {
    private int nroFase;
    private List<Ronda> rondas;

    public Fase(int nroFase, List<Ronda> rondas) {
        this.nroFase = nroFase;
        this.rondas = rondas;
    }

    public Fase(int nroFase) {
        this.nroFase = nroFase;
    }

    public int getNroFase() {
        return nroFase;
    }

    public void setNroFase(int nroFase) {
        this.nroFase = nroFase;
    }

    public List<Ronda> getRondas() {
        return rondas;
    }

    public void setRondas(List<Ronda> rondas) {
        this.rondas = rondas;
    }
}
