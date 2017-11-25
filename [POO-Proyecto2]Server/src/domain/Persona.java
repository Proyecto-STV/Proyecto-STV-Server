package domain;

public class Persona {
    private String nombre;
    private String cedula;
    private boolean haVotado;

    public Persona(String nombre, String cedula) {
        super();
        this.nombre = nombre;
        this.cedula = cedula;
        this.haVotado = false;
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

    @Override
    public String toString() {
        return "Persona{" + "nombre=" + nombre + ", cedula=" + cedula + ", haVotado=" + haVotado + '}';
    }    
}
