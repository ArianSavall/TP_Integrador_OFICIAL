package org.utilities;

import org.Modelos.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LectorDB {



    Connection conexion = null;
    Statement consulta = null;
    LectorCSV lectorCSV;
    private List<Pronostico> pronosticos = new ArrayList<>();
    private List<Persona> personas = new ArrayList<>();


    public LectorDB(LectorCSV lectorCSV) {
        this.lectorCSV = lectorCSV;
    }

    public Persona buscarPersona(String i) {
        for (Persona p : personas) {
            if (p.getNombre().equals(i)) {
                return p;
            }
        }
        return null;
    }

    public void leerPronosticos(List<String> datosUsuario) {
        int puntos = 0;
        int cantPronosticosAcertados = 0;
        Ronda rondapatron = new Ronda(1);
        try {

            //abrir conexion
            conexion = DriverManager.getConnection(datosUsuario.get(0), datosUsuario.get(1), datosUsuario.get(2));
            //Ejecutar consulta
            consulta = conexion.createStatement();

            String sql;
            sql = "select * from nuevo_tp_integrador.pronostico";

            ResultSet resultado = consulta.executeQuery(sql);

            while (resultado.next()) {
                int rondaDB = resultado.getInt("Ronda");
                String participanteDB = resultado.getString("Participante");
                String equipo1DB = resultado.getString("Equipo 1");
                String gana1DB = resultado.getString("Gana 1");
                String empataDB = resultado.getString("Empata");
                String gana2DB = resultado.getString("Gana 2");
                String equipo2DB = resultado.getString("Equipo 2");

                Equipo equipo1 = null;
                Equipo equipo2 = null;

                try{
                    equipo1 = lectorCSV.buscarEquipo(equipo1DB);
                    equipo2 = lectorCSV.buscarEquipo(equipo2DB);
                } catch (EquipoNoEncontradoException e){
                    continue;
                }
                Ronda ronda = null;
                try{
                    ronda = lectorCSV.buscarRonda(rondaDB);
                } catch(RondaNoEncontradaException e){
                    continue;
                }

                Persona persona = buscarPersona(participanteDB);

                if (persona == null) { //si no existe la persona, crearla, añadirla a la lista y reiniciar la variable puntos
                    persona = new Persona(participanteDB, ronda.getNro());
                    this.personas.add(persona);
                    puntos = 0;
                    cantPronosticosAcertados = 0;
                }

                if (persona.getNroRonda() != ronda.getNro()) { //si la persona ya existe pero cambió la ronda, que se reinicien los puntos
                    persona.setNroRonda(ronda.getNro());
                    puntos = 0;
                    cantPronosticosAcertados = 0;
                }

                Partido partido = ronda.buscarPartido(equipo1, equipo2);
                Equipo equipoPred = null;
                ResultadoEnum resultadoPred = null;

                if ("X".equals(gana1DB)) {  //si la X está en gana1
                    equipoPred = partido.getEquipo1();
                    resultadoPred = ResultadoEnum.GANADOR;

                }
                if ("X".equals(empataDB)) {  //si la X está en empate
                    equipoPred = partido.getEquipo1();
                    resultadoPred = ResultadoEnum.EMPATE;
                }

                if ("X".equals(gana2DB)) {  //si la X está en gana2
                    equipoPred = partido.getEquipo1();
                    resultadoPred = ResultadoEnum.PERDEDOR;
                }

                Pronostico pronostico = new Pronostico(partido, equipoPred, persona);

                //sumamos los puntos que correspondan y los metemos en el puntaje de la persona
                puntos += pronostico.puntos(Integer.parseInt(datosUsuario.get(3)), resultadoPred);


                pronostico.getPersona().setPuntaje(puntos);

                cantPronosticosAcertados += pronostico.sumarPronosticoAcertado(resultadoPred);
                pronostico.getPersona().setCantPronosticos(cantPronosticosAcertados);

                this.pronosticos.add(pronostico);
            }
        } catch (SQLException se) {
            // Execpción ante problemas de conexión
            se.printStackTrace();
        }  finally {
            // Esta sentencia es para que ante un problema con la base igual se cierren las conexiones
            try {
                if (consulta != null)
                    consulta.close();
            } catch (SQLException se2) {
            }
            try {
                if (conexion != null)
                    conexion.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        //imprimo los resultados de la ronda final
        System.out.println("Resultados de la ronda " + rondapatron.getNro());
        for (Persona persona : this.personas) {
            System.out.println(persona.getNombre() + " obtuvo " + persona.getPuntaje() + " puntos y acertó " + persona.getCantPronosticos() + " pronosticos");
        }
    }
    public int puntosExtraRonda(int puntosExtra,Persona p, int nroRonda, ResultadoEnum resultado){

        List<Pronostico> pronostico1 = this.pronosticos.stream().filter(pronostico
                -> pronostico.getPersona().equals(p)).toList();

        pronostico1 = pronostico1.stream().filter(pronostico
                -> pronostico.getPersona().getNroRonda() == nroRonda).toList();

        if(pronostico1.stream().allMatch(pronostico -> pronostico.acertó(resultado))){
            return puntosExtra;
        }

        return 0;
    }
}
