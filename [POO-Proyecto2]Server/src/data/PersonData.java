package data;

import domain.Persona;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nelson
 */
public class PersonData {
    private String fileName;
    private List<Persona> padronList;
    
    public PersonData(String fileName) {
        this.fileName = fileName;
        padronList = new ArrayList<>();
    }
    
    public List<Persona> getPadron(){
        padronList = new ArrayList<>();
        try {
            try (FileReader textFileReader = new FileReader(fileName)) {
                BufferedReader bufReader = new BufferedReader(textFileReader);

                String line = bufReader.readLine();
                while (line != null) {
                    String [] personaFile = line.split(";");
                    Persona person = new Persona(personaFile[1], personaFile[0]);
                    padronList.add(person);
                    System.out.println(person.toString());
                    line = bufReader.readLine();
                }
                save(padronList);
            }         
        } catch (IOException e) {
            e.printStackTrace();
        }
        return padronList;
    }
    
    public List<Persona> getVotantes(){
        padronList = new ArrayList<>();
        try {
            try (FileReader textFileReader = new FileReader(fileName)) {
                BufferedReader bufReader = new BufferedReader(textFileReader);

                String line = bufReader.readLine();
                while (line != null) {
                    String [] personaFile = line.split(";");
                    Persona person = new Persona(personaFile[1], personaFile[0], Boolean.parseBoolean(personaFile[2]), Boolean.parseBoolean(personaFile[3]));
                    padronList.add(person);
                    line = bufReader.readLine();
                }                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return padronList;
    }

    public Persona searchPerson(String identificaction) {
        int i = 0;
        for (Persona person : padronList) {
            if (person.getCedula().equals(identificaction) && !person.isHaVotado() && !person.isHaIngresado()){
                person.setHaIngresado(true);
                padronList.set(i, person);
                return person;
            }
            i++;
        }
        return null;
    }      

    public boolean save(List<Persona> listaPersonas) {        
        FileOutputStream fos = null;
        try {
            File fout = new File(fileName);
            if(fout.exists() && !fout.isDirectory()) { 
                fout.delete();
            }
            fout.createNewFile();
            fos = new FileOutputStream(fout);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));            
            for (Persona persona : listaPersonas) {                                      
                bw.write(persona.getCedula() + ";" + persona.getNombre() + ";" + persona.isHaVotado()+ ";" + persona.isHaIngresado());
                bw.newLine();
            }
            bw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CandidatoData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CandidatoData.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(CandidatoData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }   
    
    public void updatePersona(Persona person) {
        int i = 0;        
        for (Persona personList : getVotantes()) {
            if (personList.getCedula().equals(person.getCedula())){
                padronList.set(i, person);
                System.out.println(person.toString());
            }
            i++;
        }
        save(padronList);
    }
    
    public List<Persona> getPersonList() {
        return padronList;
    }

    public void setPersonList(List<Persona> personList) {
        this.padronList = personList;
    }      
}
