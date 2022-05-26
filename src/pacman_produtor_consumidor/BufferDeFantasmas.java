package pacman_produtor_consumidor;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class BufferDeFantasmas extends UnicastRemoteObject implements BufferInterface{
    private static final long serialVersionUID = 1L;
    
    private static final int TAMANHO_BUFFER = 6;
    private static final int PORTA = 1099;
    
    private int[] vetorBuffer = new int[TAMANHO_BUFFER];
    private int filaEntrada = 0;
    private int filaSaida = 0;
    private int contador = 0;
    
    public BufferDeFantasmas() throws RemoteException {
        super();
    } 
    
    @Override
    public synchronized int comer() throws RemoteException {
    	//Pausa a digestão de fantasmas caso não haja nenhum fantasma disponível
        while (contador == 0) {
            try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
        
        int I = vetorBuffer[filaSaida];
        
        filaSaida = (filaSaida + 1) % TAMANHO_BUFFER;
        contador = contador - 1;
        notifyAll();
        
        return I;
    }
    
    @Override
    public synchronized void criarFantasma() throws RemoteException {
    	//Pausa a criação de fantasmas caso o número de fantasmas seja do tamanho do buffer
        while (contador == TAMANHO_BUFFER) {
            try {
                wait();
            } catch (InterruptedException e) {
				e.printStackTrace();
            }
        }
        
        contador = contador +1;
        vetorBuffer[filaEntrada] = contador;
        filaEntrada = (filaEntrada + 1) % TAMANHO_BUFFER;

        notifyAll(); 
    }

    public static void main(String args[]) throws MalformedURLException {
        try {           
        	BufferDeFantasmas buffer = new BufferDeFantasmas();
            
            java.rmi.registry.LocateRegistry.createRegistry(PORTA);
            
            Naming.rebind("rmi://localhost:" + PORTA + "/BUFFER_DE_FANTASMAS", buffer);  
            System.out.println("Disponível para instâncias Pacman!");
        } catch (RemoteException e) {
            System.out.println("Falha no Buffer: " + e.toString());
        }
    }
}
