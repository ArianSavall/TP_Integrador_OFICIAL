package org.Modelos;

import java.util.List;

public class Pronostico {
    private Partido partido;
    private Equipo equipo;
    private Persona persona;
    private ResultadoEnum resultadoPred;
    private Fase fase;


    public Pronostico(Partido partido, Equipo equipo, ResultadoEnum resultadoPred, Persona persona, Fase fase) {
        this.partido = partido;
        this.equipo = equipo;
        this.resultadoPred = resultadoPred;
        this.persona = persona;
        this.fase = fase;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public ResultadoEnum getResultadoPred() {
        return resultadoPred;
    }

    public Fase getFase() {
        return fase;
    }

    public void setFase(Fase fase) {
        this.fase = fase;
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

//    public void sumarPuntos(int puntosASumar) {
//        // this.resultado -> pred
//        int puntos = this.persona.getPuntaje();
//        puntos += puntosASumar;
//        this.persona.setPuntaje(puntos);
//    }

//    public void sumarPronosticoAcertado(){
//        int cantPronosticosAcertados = this.persona.getCantPronosticos();
//        cantPronosticosAcertados += 1;
//        this.persona.setCantPronosticos(cantPronosticosAcertados);
//    }

}
