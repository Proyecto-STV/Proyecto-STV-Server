package data;

import domain.Persona;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nelson
 */
public class PersonData {
    private String fileName;
    private List<Persona> personList;
    public PersonData(String fileName) {
        this.fileName = fileName;
        personList = new ArrayList<>();
    }
    
    public List<Persona> getAllPersons(){
        personList = new ArrayList<>();
        try {
            try (FileReader textFileReader = new FileReader(fileName)) {
                BufferedReader bufReader = new BufferedReader(textFileReader);

                String line = bufReader.readLine();
                while (line != null) {
                    String [] personaFile = line.split(";");
                    Persona person = new Persona(personaFile[1], personaFile[0]);
                    personList.add(person);
                    System.out.println(person.toString());
                    line = bufReader.readLine();
                }                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return personList;
    }

    public boolean searchPerson(String identificaction) {
        for (Persona personList1 : personList) {
            if (personList1.getCedula().equals(identificaction) && !personList1.isHaVotado()){
                return true;
            }
        }
        return false;
    }

    public List<Persona> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Persona> personList) {
        this.personList = personList;
    }    
}
