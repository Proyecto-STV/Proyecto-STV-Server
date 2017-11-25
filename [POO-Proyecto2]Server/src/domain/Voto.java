package domain;

import java.util.ArrayList;

public class Voto {
    private ArrayList<Candidato> info;

    public Voto() {
        this.info = new ArrayList<>();
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
}
