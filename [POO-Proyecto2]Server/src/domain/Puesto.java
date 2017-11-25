package domain;

public class Puesto {
    private String nombre;
    private String organizacion;
    private int numero;    

    public Puesto(String nombre, String organizacion, int numero) {
        this.nombre = nombre;
        this.organizacion = organizacion;
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(String organizacion) {
        this.organizacion = organizacion;
    }

    @Override
    public String toString() {
        return "Puesto{" + "nombre=" + nombre + ", organizacion=" + organizacion + ", numero=" + numero + '}';
    }        
}