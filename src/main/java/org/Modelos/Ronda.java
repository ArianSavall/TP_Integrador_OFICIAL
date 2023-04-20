package org.Modelos;

import java.util.ArrayList;
import java.util.List;

public class Ronda {
    private Integer nro;
    private List<Partido> partidos = new ArrayList<>();

    public Ronda(Integer nro, List<Partido> partidos) {
        this.nro = nro;
        this.partidos = partidos;
    }

    public Ronda(Integer nro) {
        this.nro = nro;
    }

    public Integer getNro() {
        return nro;
    }

    public void setNro(Integer nro) {
        this.nro = nro;
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
    }

    public void agregarPartidos(Partido partido){
        this.partidos.add(partido);
    }

    public Partido buscarPartido(Equipo equipo1, Equipo equipo2) {
        for (Partido partido : this.partidos){
            if(equipo1.getNombre().equals(partido.getEquipo1().getNombre()) &&
                    equipo2.getNombre().equals(partido.getEquipo2().getNombre())){
                return partido;
        }
        }
        return null;
    }
    //Dentro de la clase ronda, hacer un método que reciba dos equipos por parámetro y que me devuelva el partido
//                con esos dos equipos

}
