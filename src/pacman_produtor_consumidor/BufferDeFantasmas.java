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
    	//Pausa a digest�o de fantasmas caso n�o haja nenhum fantasma dispon�vel
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
    	//Pausa a cria��o de fantasmas caso o n�mero de fantasmas seja do tamanho do buffer
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
            System.out.println("Dispon�vel para inst�ncias Pacman!");
        } catch (RemoteException e) {
            System.out.println("Falha no Buffer: " + e.toString());
        }
    }
}
