package org.Modelos;

public class Persona {
    private String nombre;
    private int puntaje = 0;
    private int cantPronosticosAcertados = 0;
    private int nroRonda = 0;

    public Persona(String nombre, int nroRonda) {
        this.nombre = nombre;
        this.nroRonda = nroRonda;
    }

    public Persona(String nombre, int puntaje, int cantPronosticosAcertados) {
        this.nombre = nombre;
        this.puntaje = puntaje;
        this.cantPronosticosAcertados = cantPronosticosAcertados;
    }

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public int getPuntaje() {return puntaje;}

    public void setPuntaje(int puntaje) {this.puntaje = puntaje;}

    public int getCantPronosticos() {return cantPronosticosAcertados;}

    public void setCantPronosticos(int cantPronosticos) {this.cantPronosticosAcertados = cantPronosticos;}

    public int getNroRonda() {return nroRonda;}

    public void setNroRonda(int nroRonda) {this.nroRonda = nroRonda;}
}
