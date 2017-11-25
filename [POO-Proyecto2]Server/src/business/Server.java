package business;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.IConstants;

/**
 *
 * @author Nelson
 */
public class Server extends Thread implements IConstants {

    private PadronBusiness padronBusiness;
    
    public Server(){
        padronBusiness = new PadronBusiness();
    }
    
    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Iniciado");
            
            padronBusiness.getAllPersons();
            do {
                Socket socket = serverSocket.accept();
                
                PrintStream enviar = new PrintStream(socket.getOutputStream()); //output
                BufferedReader recibir = new BufferedReader(new InputStreamReader(socket.getInputStream()));//input
                
                String funcionString = recibir.readLine();// primer input
                enviar.println("Servidor STV");
                if (funcionString.equalsIgnoreCase("")) {
                    methodInput(socket);
                } else if (funcionString.equalsIgnoreCase("")) {
                    methodOutput(socket);
                }
            } while (true);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void methodInput(Socket socket) throws IOException, ClassNotFoundException {
        ObjectInputStream objectIn = new ObjectInputStream(socket.getInputStream());
        //Player originCoach = (Player) objectIn.readObject();
                
        ObjectOutputStream objectOut = new ObjectOutputStream(socket.getOutputStream());
        objectOut.writeObject(null);
    }

    private void methodOutput(Socket socket) throws IOException {
        ObjectOutputStream objectOut = new ObjectOutputStream(socket.getOutputStream());  
        
        
        objectOut.writeObject(null);
    }
}
