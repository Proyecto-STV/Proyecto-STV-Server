package data;

import domain.Candidato;
import domain.Voto;
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
public class VotoData {
    private String fileName;
    private List<Voto> listaVotos;
    
    public VotoData(String fileName) {
        this.fileName = fileName;        
    }

    public List<Voto> getListaVotos() {
        listaVotos = new ArrayList<>();
        try {
            try (FileReader textFileReader = new FileReader(fileName)) {
                BufferedReader bufReader = new BufferedReader(textFileReader);

                String line = bufReader.readLine();
                while (line != null) {
                    String [] votoFile = line.split(";");
                    String [] candidatos = votoFile[1].split(":");
                    List<Candidato> candidatosVoto = new ArrayList<>();
                    for (int i = 0; i < candidatos.length; i++) {
                        String [] candidato = candidatos[i].split("-");
                        Candidato nuevoCandidato = new Candidato();
                        nuevoCandidato.setNombre(candidato[0]);
                        nuevoCandidato.setAgrupacion(candidato[1]);    
                        candidatosVoto.add(nuevoCandidato);
                    }
                    Voto voto = new Voto(candidatosVoto);
                    
                    listaVotos.add(voto);
                    line = bufReader.readLine();
                }                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaVotos;
    }

    public boolean save(List<Voto> votosArchivo) {
        FileOutputStream fos = null;
        try {
            File fout = new File(fileName);
            if(fout.exists() && !fout.isDirectory()) { 
                fout.delete();
            }
            fout.createNewFile();
            fos = new FileOutputStream(fout);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));            
            for (int i = 0; i < votosArchivo.size(); i++) {            
                String salida = "";
                for (Candidato info : votosArchivo.get(i).getInfo()) {
                    salida += info.getNombre() + "-" + info.getAgrupacion() + ":";
                }
                bw.write(i + ";" + salida);
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
    
}
