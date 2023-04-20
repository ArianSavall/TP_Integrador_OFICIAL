import org.utilities.LectorDB;
import org.utilities.LectorCSV;

import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        LectorCSV lectorCSV = new LectorCSV(Path.of(args[0]), Path.of(args[1]));
        System.out.println(args[1]);
        lectorCSV.leerResultados();
        List<String> datosUsuario = lectorCSV.leerConfig();

        LectorDB lectorDB = new LectorDB(lectorCSV);
        lectorDB.leerPronosticos(datosUsuario);
        //lectorDB.calcularPuntos()
    }
}