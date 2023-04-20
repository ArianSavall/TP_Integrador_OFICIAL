package org.utilities;

import org.Exceptions.EquipoNoEncontradoException;
import org.Exceptions.FaseNoEncontradaException;
import org.Exceptions.RondaNoEncontradaException;
import org.Modelos.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.utilities.LectorDB.*;

public class LectorCSV {

    private Path rutaResultados;
    private Path rutaConfig;

    private List<Fase> fases = new ArrayList<>();
    private List<Equipo> equipos = new ArrayList<>();


    public LectorCSV(Path rutaResultados, Path rutaConfig) {
        this.rutaResultados = rutaResultados;
        this.rutaConfig = rutaConfig;
    }

    public LectorCSV() {
    }

    public List<Fase> getFases() {
        return fases;
    }


    //metodos para buscar

    public Fase buscarFase(Integer i){
        for(Fase f : fases){
            if(f.getNro() == i){
                return f;
            }
        }
        throw new FaseNoEncontradaException("No se encontró la fase");
    }
    public Ronda buscarRonda(Integer i) {
        for(Fase f : fases){
            for (Ronda r : f.getRondas()) {
                if (r.getNro() == i) {
                    return r;
                }
            }
        }
        throw new RondaNoEncontradaException("No se encontró la ronda"); //RondaException
    }

    public Equipo buscarEquipo(String i) {
        for (Equipo e : this.equipos) {
            if (e.getNombre().equals(i)) {
                return e;
            }
        }
        throw new EquipoNoEncontradoException("No se encontró el equipo");//EquipoException
        //return null;
    }


    public void leerResultados() {
        List<String> lineasResultado = new ArrayList<>();
        try {
            lineasResultado = Files.readAllLines(rutaResultados);
        } catch (
                IOException e) {
            System.out.println("No se pudo leer la linea de resultados...");
            System.out.println(e.getMessage());
            System.exit(1);
        }
        boolean primera = true;
        for (String lineaResultado : lineasResultado) {
            if (primera) {
                primera = false;
            } else {
                String[] campos = lineaResultado.split(";");
                Equipo equipo1 = null;
                Equipo equipo2 = null;

                try{
                    equipo1 = this.buscarEquipo(campos[1]);
                    equipo2 = this.buscarEquipo(campos[4]);
                }catch(EquipoNoEncontradoException e){
                    //Si el equipo no existe, instanciar uno nuevo
                       if(equipo1 ==null){
                           equipo1 = new Equipo(campos[1]);
                       }
                       equipo2 = new Equipo(campos[4]);

                }
                equipos.add(equipo1);
                equipos.add(equipo2);

                //instancio un nuevo partido
                Partido partido = new Partido(equipo1, equipo2);
                partido.setCantGoles1(Integer.parseInt(campos[2]));
                partido.setCantGoles2(Integer.parseInt(campos[3]));

                Ronda ronda = null;
                    //Si no existe una ronda, crearla
                try{
                    ronda = this.buscarRonda(Integer.parseInt(campos[0]));
                } catch (RondaNoEncontradaException e){
                        ronda = new Ronda(Integer.parseInt(campos[0]));
                        ronda.agregarPartidos(partido);
                }
                ronda.agregarPartidos(partido);

                Fase fase = null;

                //Si no existe una fase, crearla
                try{
                    fase = this.buscarFase(Integer.parseInt(campos[5]));
                } catch(FaseNoEncontradaException e){
                    fase = new Fase(Integer.parseInt(campos[5]));
                    fase.agregarRondas(ronda);
                    fases.add(fase);
                }

                //se fija que la ronda no esté dentro de la fase. Si no está, agregarla
                try{
                   this.buscarRonda(ronda.getNro());
                }catch(RondaNoEncontradaException e){
                    fase.agregarRondas(ronda);
                }
            }
        }
    }

    public List<String> leerConfig() {
        List<String> lineasConfig = new ArrayList<>();
        List<String> datosUsuario = new ArrayList<>();
        try {
            lineasConfig = Files.readAllLines(rutaConfig);
        } catch (
                IOException e) {
            System.out.println("No se pudo leer la linea de configuraciones...");
            System.out.println(e.getMessage());
            System.exit(1);
        }
        boolean primera = true;
        for (String lineaConfig : lineasConfig) {
            if(primera){
                primera=false;
            }else{
                String[] campos = lineaConfig.split(";");
                datosUsuario.addAll(Arrays.asList(campos).subList(0, 6));
            }
        }
        return datosUsuario;
    }
}