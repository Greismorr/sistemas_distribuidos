package produtorconsumidor;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class Buffer extends UnicastRemoteObject implements BufferInterface{

	private static final long serialVersionUID = 1L;
	
	private static final int TAMANHO_BUFFER = 5;
    private static final int PORTA = 1099;
    private int[] vetorBuffer = new int[TAMANHO_BUFFER];
    
    private int FilaEntrada = 0;
    private int FilaSaida = 0;
    private int Contador = 0;
    
    public Buffer() throws RemoteException {
        super();
    } 
    
    @Override
    public synchronized int take() throws RemoteException {
        
        while (Contador == 0) 
        {
            try 
            {
                wait();
            } 
            catch (InterruptedException e) {}
        }
        
        int I = vetorBuffer[FilaSaida];
        
        FilaSaida = (FilaSaida+1) % TAMANHO_BUFFER;
        Contador = Contador-1;
        notifyAll();
        
        return I;
    }
    
    @Override
    public synchronized void append(int value) throws RemoteException {
        
        while (Contador == TAMANHO_BUFFER) 
        {
            try 
            {
                wait();
            } catch (InterruptedException e) {}
        }
             
        vetorBuffer[FilaEntrada] = value;
        FilaEntrada = (FilaEntrada+1) % TAMANHO_BUFFER;

        Contador = Contador+1;
        notifyAll();
    }

    public static void main(String args[]) throws MalformedURLException{
        try{
                      
            Buffer buffer = new Buffer();
            
            java.rmi.registry.LocateRegistry.createRegistry(PORTA);
            
            Naming.rebind("rmi://localhost:" + PORTA + "/BUFFER", buffer);  
            System.out.println("Buffer aguardando conexão.....");
        }
        catch (RemoteException re) {
            System.out.println("Remote exception: " + re.toString());
        }
    }
}