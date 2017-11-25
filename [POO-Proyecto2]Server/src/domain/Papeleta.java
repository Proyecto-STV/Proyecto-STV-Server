package domain;

import java.util.ArrayList;

public class Papeleta {

    private Puesto puesto;
    public ArrayList<Candidato> candidatos;

    public Papeleta(Puesto puesto) {
        this.puesto = puesto;
        this.candidatos = new ArrayList<>();
    }
    
    public Puesto getPuesto() {
        return puesto;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }   

    public void agregar_canditatos(Candidato e) {
        candidatos.add(e);
    }
}
