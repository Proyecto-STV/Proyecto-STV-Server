package domain;

import java.io.File;

public class Candidato {

    private String nombre;
    private String agrupacion;
    private String color;
    private File foto;

    public String getNombre() {
        return nombre;
    }

    public Candidato(String nombre, String agrupacion, String color) {
        super();
        this.setNombre(nombre);
        this.setAgrupacion(agrupacion);
        this.setColor(color);
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public File getFoto() {
        return foto;
    }

    public void setFoto(File foto) {
        this.foto = foto;
    }
}
