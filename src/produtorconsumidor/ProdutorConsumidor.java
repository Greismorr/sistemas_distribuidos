package produtorconsumidor;

import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ProdutorConsumidor {
    
    private ProdutorConsumidor() {}

    public static void main(String[] args) throws UnknownHostException 
    {        
    	try 
    	{        
            Scanner scan = new Scanner(System.in);
            int mensagens = 0;
            
            Registry registry = LocateRegistry.getRegistry(1099);
            BufferInterface buffer = (BufferInterface)registry.lookup("BUFFER");
            
            System.out.println("--- PRODUTOR - CONSUMIDOR ---");
            System.out.println("Digite o numero de mensagens a serem enviadas pelo produtor");
            System.out.print("e que serão retiradas pelo consumidor: ");
            mensagens = scan.nextInt();
            
            Produtor produtor = new Produtor(buffer, mensagens);
            Consumidor consumidor = new Consumidor(buffer, mensagens);

            produtor.start();
            consumidor.start();
            
        } 
    	catch ( RemoteException | NotBoundException  e) 
    	{
            System.err.println("ProdutorConsumidor exception: " + e.getMessage());
        }
    }
}