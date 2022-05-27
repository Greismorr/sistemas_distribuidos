package pacman_produtor_consumidor;

import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CriarPacmanConsumidor {
    public static void main(String[] args) throws UnknownHostException { 
        final String HOST = "localhost";
        final String BUFFER = "BUFFER_DE_FANTASMAS";
        
    	try {        
            Registry registry = LocateRegistry.getRegistry(HOST, 1099);
            BufferInterface buffer = (BufferInterface)registry.lookup(BUFFER);
            
            PacmanConsumidor pacman = new PacmanConsumidor(buffer);

            pacman.start(); 
        } 
    	catch (RemoteException | NotBoundException  e) {
            System.err.println("ProdutorConsumidor exception: " + e.getMessage());
        }
    }
}