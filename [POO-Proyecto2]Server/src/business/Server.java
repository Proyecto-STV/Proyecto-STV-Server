package business;

import domain.Candidato;
import domain.Persona;
import domain.Puesto;
import domain.Voto;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
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
    private PersonBusiness votantesBusiness;
    
    private CandidatoBusiness candidatoBusiness;
    private Puesto puesto;
    private List<Candidato> postulantes;
    private List<Candidato> ganadores;
    private List<Voto> listaVotos;
    private boolean periodoVotacion;

    public Server() {
        this.postulantes = new ArrayList<>();
        this.listaVotos = new ArrayList<>();
        this.periodoVotacion = false;
        this.padronBusiness = new PersonBusiness(PERSONS_FILE_NAME);
        this.votantesBusiness = new PersonBusiness(VOTANTES_FILE_NAME);
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
                } else if (funcionString.equalsIgnoreCase(GET_POSITION)) {
                    getPuesto(socket);
                } else if (funcionString.equalsIgnoreCase(CLIENT_CLOSED)){
                    clientClossing(socket);
                }
            } while (true);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void validatePerson(Socket socket, String identificaction) throws IOException, ClassNotFoundException {
        ObjectOutputStream objectOut = new ObjectOutputStream(socket.getOutputStream());
        if (this.periodoVotacion) {
            Persona respond = votantesBusiness.searchPerson(identificaction);
            if (respond != null) {
                objectOut.writeObject(1);
                ObjectInputStream objectIn = new ObjectInputStream(socket.getInputStream());
                objectIn.readObject();
                objectOut.writeObject(respond);
                votantesBusiness.save(votantesBusiness.getPersonList());
            } else {
                objectOut.writeObject(0);
                objectOut.writeObject(null);
            }
        } else {
            objectOut.writeObject(-1);
            objectOut.writeObject(null);
        }       
    }

    private void getCandidates(Socket socket) throws IOException {
        ObjectOutputStream objectOut = new ObjectOutputStream(socket.getOutputStream());
        objectOut.writeObject(postulantes);
    }

    private void getPuesto(Socket socket) throws IOException {
        ObjectOutputStream objectOut = new ObjectOutputStream(socket.getOutputStream());
        objectOut.writeObject(puesto);
    }
    
    private void clientClossing(Socket socket) throws IOException, ClassNotFoundException {
        ObjectInputStream objectIn = new ObjectInputStream(socket.getInputStream());
        Persona person = (Persona) objectIn.readObject();
        System.out.println("");
        System.out.println(person.toString());
        votantesBusiness.updatePersona(person);
    }

    public void loadFile(String absolutePath) {
        padronBusiness = new PersonBusiness(absolutePath);
        padronBusiness.getPadron();
        votantesBusiness.save(padronBusiness.getPersonList());
        votantesBusiness.getVotantes();
    }

    public Puesto getPuesto() {
        return puesto;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }

    public void addPostulante(Candidato postulante) {
        postulantes.add(postulante);
        candidatoBusiness = new CandidatoBusiness(CANDIDATE_FILE);
        candidatoBusiness.save(postulantes);
    }

    public void removePostulante(Candidato postulante) {
        postulantes.remove(postulante);
        candidatoBusiness = new CandidatoBusiness(CANDIDATE_FILE);
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

    public int contar_votos() {        
        int necesario = listaVotos.size() / postulantes.size();
        System.out.println("La cantidad de votos es: ");
        System.out.println(listaVotos.size());
        System.out.println("Los votos necesarios para ganar es");
        System.out.println(necesario);
        while (true) {

            if (puesto.getNumero() - ganadores.size() == postulantes.size()) {
                for (Candidato r : postulantes) {
                    r.eliminar();
                    ganadores.add(r);
                    System.out.printf("Este Candidato gano una silla %s \n", r.getNombre());
                }
            }
            if (ganadores.size() == puesto.getNumero()) {
                System.out.println("Ya terminaron las eleciones");
                return 0;
            }
            for (Voto e : listaVotos) {

                Candidato aux = e.sacar_eleccion();
                if (aux != null) {
                    aux.agregar_a_mis_votos(e);
                    for (Candidato r : postulantes) {
                        if (r.mis_votos.size() >= necesario) {
                            ganadores.add(r);
                            System.out.printf("Este Candidato gano una silla %s \n", r.getNombre());
                            r.eliminar();
                            postulantes.remove(aux);
                        }
                    }
                }
            }

            System.out.printf("Se encuentra al mayor perdedor \n");

            Candidato perdedor = postulantes.get(0);
            for (Candidato r : postulantes) {
                System.out.printf("La cantidad de votos del candidato %s es %d \n", r.getNombre(), r.mis_votos.size());
                if (perdedor.mis_votos.size() > r.mis_votos.size()) {
                    perdedor = r;
                }
            }

            perdedor.eliminar();
            postulantes.remove(perdedor);
            System.out.printf("El perdedor es: %s \n", perdedor.getNombre());
            for (Candidato r : postulantes) {
                r.mis_votos.removeAll(listaVotos);
            }
        }
    }

    public void dar_ganadores() {
        System.out.printf("Los %d ganadares del puesto %s son \n", puesto.getNumero(), puesto.getNombre());

        for (Candidato r : ganadores) {
            System.out.println(r.getNombre() + r.getAgrupacion() + r.getColor());
        }
    }   
}