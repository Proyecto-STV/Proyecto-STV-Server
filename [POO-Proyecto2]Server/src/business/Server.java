package business;

import domain.Candidato;
import domain.Puesto;
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
    private Puesto puesto;
    private List<Candidato> postulantes;
    
    public Server(){
        postulantes = new ArrayList<>();
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
                } else if (funcionString.equalsIgnoreCase("")) {
                    methodOutput(socket);
                }
            } while (true);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void validatePerson(Socket socket, String identificaction) throws IOException, ClassNotFoundException {
        boolean respond = padronBusiness.searchPerson(identificaction);
        
        //ObjectInputStream objectIn = new ObjectInputStream(socket.getInputStream());
        //Player originCoach = (Player) objectIn.readObject();
                
        ObjectOutputStream objectOut = new ObjectOutputStream(socket.getOutputStream());
        objectOut.writeObject(respond);
    }

    private void methodOutput(Socket socket) throws IOException {
        ObjectOutputStream objectOut = new ObjectOutputStream(socket.getOutputStream());  
        
        
        objectOut.writeObject(null);
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
    }
    
    public void remorePostulante(Candidato postulante){
        postulantes.remove(postulante);
    }

    public List<Candidato> getPostulantes() {
        return postulantes;
    }

    public void setPostulantes(List<Candidato> postulantes) {
        this.postulantes = postulantes;
    }        
}
