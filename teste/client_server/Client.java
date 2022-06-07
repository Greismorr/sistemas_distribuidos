package client_server;

import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import pacman_produtor_consumidor.BufferInterface;
import pacman_produtor_consumidor.PacmanConsumidor;
import properties.Manipulador;

public class Client {

	public static void main(String args[]) throws UnknownHostException {
        
	try {      
            Manipulador.CarregarDadosSensiveis();
    		
            Registry registry = LocateRegistry.getRegistry(Manipulador.getUrl(), Manipulador.getPort());
            String nome = Manipulador.getBuffer().replace("/", "");
            BufferInterface buffer = (BufferInterface)registry.lookup(nome);
            		
            PacmanConsumidor pacman = new PacmanConsumidor(buffer);

            pacman.start(); 
        } 
    	catch (RemoteException | NotBoundException  e) {
            System.err.println("ProdutorConsumidor exception: " + e.getMessage());
        }
	}
}
