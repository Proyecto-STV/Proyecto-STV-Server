package domain;

import java.io.Serializable;

public class Persona implements Serializable{
    private String nombre;
    private String cedula;
    private boolean haVotado;
    private boolean haIngresado;

    public Persona(String nombre, String cedula) {
        super();
        this.nombre = nombre;
        this.cedula = cedula;
        this.haVotado = false;
        this.haIngresado = false;
    }

    public Persona(String nombre, String cedula, boolean haVotado, boolean haIngresado) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.haVotado = haVotado;
        this.haIngresado = haIngresado;        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCarnet(String cedula) {
        this.cedula = cedula;
    }

    public boolean isHaVotado() {
        return haVotado;
    }

    public void setHaVotado(boolean haVotado) {
        this.haVotado = haVotado;
    }

    public boolean isHaIngresado() {
        return haIngresado;
    }

    public void setHaIngresado(boolean haIngresado) {
        this.haIngresado = haIngresado;
    }

    @Override
    public String toString() {
        return "Persona{" + "nombre=" + nombre + ", cedula=" + cedula + ", haVotado=" + haVotado + ", haIngresado=" + haIngresado + '}';
    }    
}
