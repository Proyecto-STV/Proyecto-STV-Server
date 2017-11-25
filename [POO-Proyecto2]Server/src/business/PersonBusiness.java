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
        return personData.getAllPersons();
    }

    public boolean searchPerson(String identificaction) {
        return personData.searchPerson(identificaction);
    }
}
