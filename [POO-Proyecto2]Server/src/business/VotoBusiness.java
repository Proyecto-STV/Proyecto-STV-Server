package business;

import data.VotoData;
import domain.Voto;
import java.util.List;

/**
 *
 * @author Nelson
 */
public class VotoBusiness {
    private VotoData votoData;

    public VotoBusiness(String fileName) {
        this.votoData = new VotoData(fileName);
    }
    
    public List<Voto> getListaVotos(){
        return votoData.getListaVotos();
    }
    
    public boolean save(List<Voto> listaVotos){
        return votoData.save(listaVotos);
    }
}
