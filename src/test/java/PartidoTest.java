import org.Modelos.Equipo;
import org.Modelos.Partido;
import org.Modelos.ResultadoEnum;

import org.junit.Assert;
import org.junit.Test;


public class PartidoTest {
    @Test
    public void probarGana1(){
        Equipo equipo1 = new Equipo("Argentina");
        Equipo equipo2 = new Equipo("Mexico");

        Partido partido = new Partido(equipo1,equipo2, 2,0 );

        ResultadoEnum resultadoReal = partido.obtenerResultadoReal(equipo1);
        Assert.assertEquals(ResultadoEnum.GANADOR, resultadoReal);
    }
    @Test
    public void probarEmpate(){
        Equipo equipo1 = new Equipo("Argentina");
        Equipo equipo2 = new Equipo("Mexico");

        Partido partido = new Partido(equipo1,equipo2, 1,1);

        ResultadoEnum resultadoReal = partido.obtenerResultadoReal(equipo1);
        Assert.assertEquals(ResultadoEnum.EMPATE, resultadoReal);
        }
    @Test
    public void probarGana2(){
        Equipo equipo1 = new Equipo("Argentina");
        Equipo equipo2 = new Equipo("Mexico");

        Partido partido = new Partido(equipo1,equipo2, 0,1);

        ResultadoEnum resultadoReal = partido.obtenerResultadoReal(equipo1);
        Assert.assertEquals(ResultadoEnum.PERDEDOR, resultadoReal);
    }
}
