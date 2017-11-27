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
    
    public List<Persona> getPadron(){
        return personData.getPadron();
    }

    public List<Persona> getVotantes(){
        return personData.getVotantes();
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

    public void updatePersona(Persona person) {
        this.personData.updatePersona(person);
    }
}
