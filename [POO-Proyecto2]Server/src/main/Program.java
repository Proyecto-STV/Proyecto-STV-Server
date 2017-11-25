package main;

import business.Server;
import view.PrincipalWindow;

/**
 *
 * @author Nelson
 */
public class Program {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Server server = new Server();
        server.start();
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PrincipalWindow(server).setVisible(true);
            }
        });
    }
}
