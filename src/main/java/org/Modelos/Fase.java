package org.Modelos;

import java.util.ArrayList;
import java.util.List;

public class Fase {
    private Integer nro;
    private List<Ronda> rondas = new ArrayList<>();

    public Fase(Integer nro, List<Ronda> rondas) {
        this.nro = nro;
        this.rondas = rondas;
    }

    public Fase(int nro) {
        this.nro = nro;
    }

    public int getNro() {
        return nro;
    }

    public void setNroFase(int nroFase) {
        this.nro = nroFase;
    }

    public List<Ronda> getRondas() {
        return rondas;
    }

    public void setRondas(List<Ronda> rondas) {
        this.rondas = rondas;
    }

    public void agregarRondas(Ronda ronda){
        this.rondas.add(ronda);
    }
}
