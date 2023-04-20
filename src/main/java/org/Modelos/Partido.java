package org.Modelos;

import java.util.Objects;

public class Partido {
    private Equipo equipo1;
    private Equipo equipo2;
    private int cantGoles1;
    private int cantGoles2;

    public Partido(Equipo equipo1, Equipo equipo2) {
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
    }


    public Equipo getEquipo1() {
        return equipo1;
    }

    public Equipo getEquipo2() {
        return equipo2;
    }


    public void setCantGoles1(int cantGoles1) {
        this.cantGoles1 = cantGoles1;
    }

    public void setCantGoles2(int cantGoles2) {
        this.cantGoles2 = cantGoles2;
    }

    public ResultadoEnum obtenerResultadoReal(Equipo equipo) {
        if (cantGoles1 == cantGoles2) {
            return ResultadoEnum.EMPATE;
        }
        if (equipo.getNombre().equals(equipo1.getNombre())) {
            if (cantGoles1 > cantGoles2) {
                return ResultadoEnum.GANADOR;
            } else {
                return ResultadoEnum.PERDEDOR;
            }
        } else {
            // como equipo no es equipo1, entonces es equipo2
            if (cantGoles2 > cantGoles1) {
                return ResultadoEnum.GANADOR;
            } else {
                return ResultadoEnum.PERDEDOR;
            }
        }

    }
}
