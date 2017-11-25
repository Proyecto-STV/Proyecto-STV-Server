package domain;

import java.util.ArrayList;

public class Puesto {
    private String nombre;
    private String organizacion;
    private int numero;
    public ArrayList<Candidato> resultados;

    public Puesto(String nombre, String organizacion, int numero) {
        super();
        this.nombre = nombre;
        this.organizacion = organizacion;
        this.numero = numero;
        this.resultados = new ArrayList<>();
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void otorgar_puesto(Candidato ganador) {
        if (resultados.size() > numero) {
        } else {
            resultados.add(ganador);
        }
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
}