package org.Modelos;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PartidoTest {
    List<Ronda> rondas = new ArrayList<>();


    @Test
    public void puntosTest() {
        ResultadoEnum ganador = ResultadoEnum.GANADOR;
        ResultadoEnum perdedor = ResultadoEnum.PERDEDOR;
        ResultadoEnum empate = ResultadoEnum.EMPATE;

        Persona nata = new Persona("Nata", 1);

        Equipo boca = new Equipo("Boca");
        Equipo sanLorenzo = new Equipo("San Lorenzo");

        Equipo racing = new Equipo("Racing");
        Equipo independiente = new Equipo("San Lorenzo");

        Partido partido1 = new Partido(boca, sanLorenzo);
        partido1.setCantGoles1(2);
        partido1.setCantGoles2(1);

        Partido partido2 = new Partido(racing, independiente);
        partido2.setCantGoles1(2);
        partido2.setCantGoles2(1);

        Ronda ronda1 = new Ronda(1);

        ronda1.agregarPartidos(partido1);
        ronda1.agregarPartidos(partido2);

        Equipo river = new Equipo("River");
        Equipo cruceroDelNorte = new Equipo("Crucero del norte");
        Equipo fluminense = new Equipo("Fluminense");
        Equipo flamengo = new Equipo("Flamengo");

        Partido partido3 = new Partido(river, fluminense);
        partido3.setCantGoles1(2);
        partido3.setCantGoles2(1);

        Partido partido4 = new Partido(cruceroDelNorte, flamengo);
        partido4.setCantGoles1(2);
        partido4.setCantGoles2(1);

        Ronda ronda2 = new Ronda(2);


        rondas.add(ronda1);
        rondas.add(ronda2);

        Fase fase1 = new Fase(1, rondas);

        List<Pronostico> pronosticos = new ArrayList<>();
        Pronostico pronostico1 = new Pronostico(partido1, boca, ganador, nata, ronda1, fase1);
        Pronostico pronostico2 = new Pronostico(partido2, racing, ganador, nata, ronda1, fase1);

        Pronostico pronostico3 = new Pronostico(partido3, fluminense, perdedor, nata, ronda2, fase1);
        Pronostico pronostico4 = new Pronostico(partido4, cruceroDelNorte, ganador, nata, ronda2, fase1);

        pronosticos.add(pronostico1);
        pronosticos.add(pronostico2);

        pronosticos.add(pronostico3);
        pronosticos.add(pronostico4);

        for (Pronostico p : pronosticos) {
            if (p.getRonda().getNro() == 1) {
                if (!p.acertó(p.getResultadoPred())) {
                    continue;
                }
                nata.sumarPuntos(1);
            }
            if (p.getRonda().getNro() == 2) {
                if (!p.acertó(p.getResultadoPred())) {
                    continue;
                }
                nata.sumarPuntos(1);
            }
        }
        assertEquals(4, nata.getPuntaje());
    }

}