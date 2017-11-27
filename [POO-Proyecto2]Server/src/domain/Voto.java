package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Voto implements Serializable {

    private List<Candidato> info;

    public Voto() {
        this.info = new ArrayList<>();
    }

    public Voto(List<Candidato> listaCandidatos) {
        this.info = listaCandidatos;
    }

    public void imprimirVotos() {
        for (Candidato e : info) {
            String msg = e.getNombre() + " " + e.getAgrupacion();
            System.out.println(msg);
        }
    }

    public void agregarCandidato(Candidato candidato) {
        info.add(candidato);
    }

    public List<Candidato> getInfo() {
        return info;
    }

    public void setInfo(ArrayList<Candidato> info) {
        this.info = info;
    }

    public Candidato sacar_eleccion() {
        for (Candidato e : info) {
            if (e.is_eliminado()) {
            } else {
                return e;
            }
        }
        return null;
    }
}
