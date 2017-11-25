package domain;

public class Persona {
    private String nombre;
    private String cedula;

    public Persona(String nombre, String cedula) {
        super();
        this.nombre = nombre;
        this.cedula = cedula;
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
}
