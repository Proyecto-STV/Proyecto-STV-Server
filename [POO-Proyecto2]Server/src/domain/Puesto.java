package domain;

public class Puesto {
    private String nombre;
    private String organizacion;
    private int numeroSillas;    
    private int numeroPostulantes;

    public Puesto(String nombre, String organizacion, int numero) {
        this.nombre = nombre;
        this.organizacion = organizacion;
        this.numeroSillas = numero;
    }

    public Puesto(String nombre, String organizacion, int numeroSillas, int numeroPostulantes) {
        this.nombre = nombre;
        this.organizacion = organizacion;
        this.numeroSillas = numeroSillas;
        this.numeroPostulantes = numeroPostulantes;
    }   

    public int getNumero() {
        return numeroSillas;
    }

    public void setNumero(int numero) {
        this.numeroSillas = numero;
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

    public int getNumeroSillas() {
        return numeroSillas;
    }

    public void setNumeroSillas(int numeroSillas) {
        this.numeroSillas = numeroSillas;
    }

    public int getNumeroPostulantes() {
        return numeroPostulantes;
    }

    public void setNumeroPostulantes(int numeroPostulantes) {
        this.numeroPostulantes = numeroPostulantes;
    }

    @Override
    public String toString() {
        return "Puesto{" + "nombre=" + nombre + ", organizacion=" + organizacion + ", numeroSillas=" + numeroSillas + ", numeroPostulantes=" + numeroPostulantes + '}';
    }    
}