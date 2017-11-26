package business;

import data.CandidatoData;
import domain.Candidato;
import java.util.List;

/**
 *
 * @author Nelson
 */
public class CandidatoBusiness {
    private CandidatoData candidatoData;

    public CandidatoBusiness(String fileName) {
        this.candidatoData = new CandidatoData(fileName);
    }
    
    public List<Candidato> getAllPersons(){
        return candidatoData.getAllCandidates();
    }

    public boolean searchPerson(String identificacion, String organizacion) {
        return candidatoData.searchCandidate(identificacion, organizacion);
    }
    
    public boolean save(List<Candidato> candidatos){
        return candidatoData.save(candidatos);
    }
}
