package business;

import domain.Candidato;
import domain.Persona;
import domain.Puesto;
import domain.Voto;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.IConstants;

/**
 *
 * @author Nelson
 */
public class Server extends Thread implements IConstants {

    private PersonBusiness padronBusiness;
    private CandidatoBusiness candidatoBusiness;
    private Puesto puesto;
    private List<Candidato> postulantes;  
    private List<Voto> listaVotos;
    private boolean periodoVotacion;
    
    public Server(){
        this.postulantes = new ArrayList<>();
        this.listaVotos = new ArrayList<>();
        this.periodoVotacion = false;
    }
    
    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Iniciado");
                        
            do {
                Socket socket = serverSocket.accept();
                
                PrintStream enviar = new PrintStream(socket.getOutputStream()); //output
                BufferedReader recibir = new BufferedReader(new InputStreamReader(socket.getInputStream()));//input
                
                String funcionString = recibir.readLine();// primer input
                enviar.println("Servidor STV");
                enviar.println("listo");
                if (funcionString.equalsIgnoreCase(VALIDATE_IDENTIFITATION)) {
                    String identification = recibir.readLine();
                    validatePerson(socket, identification);
                } else if (funcionString.equalsIgnoreCase(GET_CANDIDATES)) {
                    getCandidates(socket);
                } else if (funcionString.equalsIgnoreCase(GET_POSITION)){
                    getPuesto(socket);
                }
            } while (true);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void validatePerson(Socket socket, String identificaction) throws IOException, ClassNotFoundException {
        ObjectOutputStream objectOut = new ObjectOutputStream(socket.getOutputStream());
        if (this.periodoVotacion){            
            Persona respond = padronBusiness.searchPerson(identificaction);
            if (respond != null){                    
                objectOut.writeObject(1);
                objectOut.writeObject(respond);
                padronBusiness.save(padronBusiness.getVotantesList());
            } else {
                objectOut.writeObject(0);
                objectOut.writeObject(null);
            }
        }else {
            objectOut.writeObject(-1);
            objectOut.writeObject(null);
        }
        //ObjectInputStream objectIn = new ObjectInputStream(socket.getInputStream());
        //Player originCoach = (Player) objectIn.readObject();
    }

    private void getCandidates(Socket socket) throws IOException {
        ObjectOutputStream objectOut = new ObjectOutputStream(socket.getOutputStream());                  
        objectOut.writeObject(postulantes);
    }
    
    private void getPuesto(Socket socket) throws IOException {
        ObjectOutputStream objectOut = new ObjectOutputStream(socket.getOutputStream());                  
        objectOut.writeObject(puesto);
    }

    public void loadFile(String absolutePath) {
        padronBusiness = new PersonBusiness(absolutePath);
        padronBusiness.getAllPersons();
    }

    public Puesto getPuesto() {
        return puesto;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }

    public void addPostulante(Candidato postulante) {
        postulantes.add(postulante);
        candidatoBusiness =  new CandidatoBusiness(CANDIDATE_FILE);
        candidatoBusiness.save(postulantes);
    }
    
    public void removePostulante(Candidato postulante){
        postulantes.remove(postulante);
        candidatoBusiness =  new CandidatoBusiness(CANDIDATE_FILE);
        candidatoBusiness.save(postulantes);
    }

    public List<Candidato> getPostulantes() {
        return postulantes;
    }

    public void setPostulantes(List<Candidato> postulantes) {
        this.postulantes = postulantes;
    }        

    public boolean isPeriodoVotacion() {
        return periodoVotacion;
    }

    public void setPeriodoVotacion(boolean periodoVotacion) {
        this.periodoVotacion = periodoVotacion;
    }     
}