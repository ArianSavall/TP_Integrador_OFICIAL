import org.Modelos.Fase;
import org.Modelos.Persona;
import org.Modelos.Pronostico;
import org.Modelos.Ronda;
import org.utilities.LectorDB;
import org.utilities.LectorCSV;

import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        LectorCSV lectorCSV = new LectorCSV(Path.of(args[0]), Path.of(args[1]));
        lectorCSV.leerResultados();
        List<String> datosUsuario = lectorCSV.leerConfig();
        Integer puntajePorAcierto = Integer.parseInt(datosUsuario.get(3));
        Integer puntajeExtraRonda = Integer.parseInt(datosUsuario.get(4));
        Integer puntajeExtraFase = Integer.parseInt(datosUsuario.get(5));

        LectorDB lectorDB = new LectorDB(lectorCSV);
        lectorDB.leerPronosticos(datosUsuario);
        calcularPuntos(lectorDB, puntajePorAcierto, puntajeExtraRonda, puntajeExtraFase);
        lectorDB.imprimirResultados();
    }


    private static void calcularPuntos(LectorDB lectorDB, Integer puntajePorAcierto, Integer puntajeExtraRonda, Integer puntajeExtraFase){
        for(Pronostico p : lectorDB.getPronosticos()){
            if(p.acertó(p.getResultadoPred())){
                p.getPersona().sumarPuntos(puntajePorAcierto);
                p.getPersona().sumarPronosticoAcertado();
            }
        }

        for(Persona p : lectorDB.getPersonas()){
            for(Fase f : lectorDB.getLectorCSV().getFases()){
                List<Pronostico> pronosticos = lectorDB
                        .getPronosticos()
                        .stream()
                        .filter(pro -> pro.getPersona().getNombre().equals(p.getNombre())
                                && pro.getFase().getNro() == f.getNro())
                        .toList();
                 if(pronosticos.stream().allMatch(pro -> pro.acertó(pro.getResultadoPred()))){
                     p.sumarPuntos(puntajeExtraFase);
                 }
            }
        }

        for(Persona p : lectorDB.getPersonas()){
            for(Fase f : lectorDB.getLectorCSV().getFases()){
                for(Ronda r : f.getRondas()){
                    List<Pronostico> pronosticos = lectorDB
                            .getPronosticos()
                            .stream()
                            .filter(pro -> pro.getPersona().getNombre().equals(p.getNombre())
                                    && pro.getFase().getNro() == f.getNro()
                                    && pro.getRonda().getNro() == r.getNro())
                            .toList();
                    if(pronosticos.stream().allMatch(pro -> pro.acertó(pro.getResultadoPred()))){
                        p.sumarPuntos(puntajeExtraRonda);
                    }
                }
            }
        }
    }
}