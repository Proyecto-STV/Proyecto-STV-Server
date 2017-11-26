package domain;

import java.io.Serializable;

public class Candidato implements Serializable{
    private String nombre;
    private String agrupacion;
    private int color;
    private byte[] foto;
    private String fotoPath;

    public Candidato() {

    }

    public String getNombre() {
        return nombre;
    }

    public Candidato(String nombre, String agrupacion, int color, String fotoPath) {        
        this.setNombre(nombre);
        this.setAgrupacion(agrupacion);
        this.setColor(color);
        this.setFotoPath(fotoPath);
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAgrupacion() {
        return agrupacion;
    }

    public void setAgrupacion(String agrupacion) {
        this.agrupacion = agrupacion;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getFotoPath() {
        return fotoPath;
    }

    public void setFotoPath(String fotoPath) {
        this.fotoPath = fotoPath;
    }        

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", agrupacion: " + agrupacion;
    }        
}
