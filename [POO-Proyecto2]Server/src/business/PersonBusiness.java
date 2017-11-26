package business;

import data.PersonData;
import domain.Persona;
import java.util.List;
import util.IConstants;

/**
 *
 * @author Nelson
 */
public class PersonBusiness implements IConstants{
    private PersonData personData;

    public PersonBusiness(String fileName) {
        this.personData = new PersonData(fileName);
    }
    
    public List<Persona> getAllPersons(){
        return personData.getPadron();
    }

    public Persona searchPerson(String identificaction) {
        return personData.searchPerson(identificaction);
    }
    
    public boolean save(List<Persona> listaPersonas){
        return personData.save(listaPersonas);
    }
    
    public List<Persona> getPersonList() {
        return personData.getPersonList();
    }

    public void setPersonList(List<Persona> personList) {
        this.personData.setPersonList(personList);
    } 

    public List<Persona> getVotantesList() {
        return personData.getVotantesList();
    }

    public void setVotantesList(List<Persona> votantesList) {
        this.personData.setVotantesList(votantesList);
    }  
}
