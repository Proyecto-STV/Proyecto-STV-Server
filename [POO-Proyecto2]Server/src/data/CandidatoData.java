package data;

import domain.Candidato;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Nelson
 */
public class CandidatoData {

    private String fileName;
    private List<Candidato> candidateList;

    public CandidatoData(String fileName) {
        this.fileName = fileName;
        candidateList = new ArrayList<>();
    }

    public List<Candidato> getAllCandidates() {
        candidateList = new ArrayList<>();
        try {
            try (FileReader textFileReader = new FileReader(fileName)) {
                BufferedReader bufReader = new BufferedReader(textFileReader);

                String line = bufReader.readLine();
                while (line != null) {
                    String[] candidatoFile = line.split(";");
                    Candidato candidato = new Candidato(candidatoFile[0], candidatoFile[1], Integer.parseInt(candidatoFile[2]), candidatoFile[3]);
                    candidateList.add(candidato);
                    line = bufReader.readLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return candidateList;
    }

    public boolean searchCandidate(String identificacion, String organizacion) {
        for (Candidato candidate : candidateList) {
            if (candidate.getNombre().equals(identificacion) && candidate.getAgrupacion().equals(organizacion)) {
                return true;
            }
        }
        return false;
    }

    public boolean save(List<Candidato> candidatos) {
        FileOutputStream fos = null;
        try {
            File fout = new File(fileName);
            if(fout.exists() && !fout.isDirectory()) { 
                fout.delete();
            }
            fout.createNewFile();
            fos = new FileOutputStream(fout);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));            
            for (Candidato candidate : candidatos) {                                      
                bw.write(candidate.getNombre() + ";" + candidate.getAgrupacion() + ";" + candidate.getColor() + ";" + candidate.getFotoPath() + ";"
                        + Arrays.toString(candidate.getFoto()));
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
