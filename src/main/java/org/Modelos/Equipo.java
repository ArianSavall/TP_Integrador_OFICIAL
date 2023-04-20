package org.Modelos;

import javax.xml.transform.Result;

public class Equipo {
    private String nombre;
    private ResultadoEnum resultado;



    public Equipo(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
    public ResultadoEnum getResultado() {
        return resultado;
    }
    public void setResultado(ResultadoEnum resultado) {
        this.resultado = resultado;
    }
}
