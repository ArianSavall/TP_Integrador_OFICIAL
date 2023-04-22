import org.junit.BeforeClass;
import org.junit.Test;
import org.utilities.LectorDB;
import org.utilities.LectorCSV;

import java.nio.file.Path;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PuntosconsecutivosTest {
    private static LectorDB lectorDB;
    @BeforeClass
    public static void start() {
        LectorCSV lectorCSV = new LectorCSV(Path.of("src/main/resources/Resultados.csv"), Path.of("src/main/resources/config.csv"));
        lectorCSV.leerResultados();
        List<String> datosUsuario = lectorCSV.leerConfig();
        lectorDB = new LectorDB(lectorCSV);
        lectorDB.leerPronosticos(datosUsuario);
    }

    @Test
    public void testPuntosConsecutivos() {
        int fase = 1;
        int ronda1 = 1;
        int ronda2 = 2;
        String persona = "Mariana";
        int puntos1erRonda = 3;
        int puntos2daRonda = 2;
        int resul1 = lectorDB.puntosEnRondaDePersona(fase, ronda1, persona);
        int resul2 = lectorDB.puntosEnRondaDePersona(fase, ronda2, persona);
        assertEquals(puntos1erRonda, resul1);
        assertEquals(puntos2daRonda, resul2);
    }
}
