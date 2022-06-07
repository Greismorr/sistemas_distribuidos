package client_server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import pacman_produtor_consumidor.BufferDeFantasmas;
import pacman_produtor_consumidor.ProdutorDeFantasmas;
import properties.Manipulador;

public class Server {

	Server() 
	{
	   try 
	      {
		Manipulador.CarregarDadosSensiveis();
		System.setProperty("java.rmi.server.hostname", Manipulador.getUrl());	
		
		String Path = Manipulador.getHost() + Manipulador.getPort() + Manipulador.getBuffer();
		BufferDeFantasmas buffer = new BufferDeFantasmas();
	
		java.rmi.registry.LocateRegistry.createRegistry(Manipulador.getPort());
	
		Naming.rebind(Path, buffer);	
		System.out.printf("Servidor iniciado em %s", Path);
			
            	ProdutorDeFantasmas fantasmaProdutor = new ProdutorDeFantasmas(buffer);
            	fantasmaProdutor.start();           

		}
		catch (RemoteException | MalformedURLException e) {
			System.out.println("Server: " + e.toString());
		}
	}

	public static void main(String args[]) throws MalformedURLException {
		new Server();
	}
}
