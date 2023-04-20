package org.Modelos;

import java.util.List;

public class Pronostico {
    private Partido partido;
    private Equipo equipo;
    private Persona persona;
    private ResultadoEnum resultadoPred;
    private Ronda ronda;
    private Fase fase;


    public Pronostico(Partido partido, Equipo equipo, ResultadoEnum resultadoPred, Persona persona, Ronda ronda, Fase fase) {
        this.partido = partido;
        this.equipo = equipo;
        this.resultadoPred = resultadoPred;
        this.persona = persona;
        this.ronda = ronda;
        this.fase = fase;
    }
    public Persona getPersona() {
        return persona;
    }

    public ResultadoEnum getResultadoPred() {
        return resultadoPred;
    }

    public Ronda getRonda(){
        return ronda;
    }

    public Fase getFase() {
        return fase;
    }

    public boolean acertó(ResultadoEnum resultado) {
        // this.resultado -> pred
        ResultadoEnum resultadoReal = partido.obtenerResultadoReal(equipo);
        if (resultado.equals(resultadoReal)) {
            return true;
        } else {
            return false;
        }
    }

}
