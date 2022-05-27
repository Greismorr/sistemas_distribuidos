package pacman_produtor_consumidor;

import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ProdutorConsumidor {
    public static void main(String[] args) throws UnknownHostException {  
    	
        final String BUFFER = "BUFFER_DE_FANTASMAS";
        
    	try {        
            Registry registry = LocateRegistry.getRegistry(1099);
            BufferInterface buffer = (BufferInterface)registry.lookup(BUFFER);
            
            ProdutorDeFantasmas fantasmaProdutor = new ProdutorDeFantasmas(buffer);
            PacmanConsumidor pacman = new PacmanConsumidor(buffer);

            fantasmaProdutor.start();
            pacman.start(); 
        } 
    	catch ( RemoteException | NotBoundException  e) {
            System.err.println("ProdutorConsumidor exception: " + e.getMessage());
        }
    }
}