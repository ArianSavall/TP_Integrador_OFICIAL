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

    public String getNombre() {return nombre;}
    public int getPuntaje() {return puntaje;}
    public int getCantPronosticos() {return cantPronosticosAcertados;}


    public void sumarPuntos(int puntosASumar) {
        this.puntaje += puntosASumar;
    }
    public void sumarPronosticoAcertado(){
        this.cantPronosticosAcertados += 1;
    }
}
