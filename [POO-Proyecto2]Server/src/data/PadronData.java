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
public class PadronData {
    private String fileName;

    public PadronData(String fileName) {
        this.fileName = fileName;
    }
    
    public List<Persona> getAllPersons(){
        List<Persona> personList = new ArrayList<>();
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
}
