package pacman_produtor_consumidor;

import java.net.MalformedURLException;
import java.rmi.server.UnicastRemoteObject;
import properties.Manipulador;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class BufferDeFantasmas extends UnicastRemoteObject implements BufferInterface{
    private static final long serialVersionUID = 1L;
    
    private int[] vetorBuffer = new int[Manipulador.getTamanho_buffer()];
    private int filaEntrada = 0;
    private int filaSaida = 0;
    private int contador = 0;
    
    public BufferDeFantasmas() throws RemoteException {
        super();
    } 
    
    @Override
    public synchronized int comer() throws RemoteException {
    	//Pausa a digest�o de fantasmas caso n�o haja nenhum fantasma dispon�vel ou caso o n�mero de fantasmas
    	//seja menor que o tamanho do buffer. Assim, ser� garantido que todo Pacman tenha 6 fantasmas.
        while (contador == 0 || contador < Manipulador.getTamanho_buffer()) {
            try {
				wait();
			} catch (InterruptedException e) {
				System.out.println("Falha ao consumir: " + e.toString());
			}
        }
        
        int I = vetorBuffer[filaSaida];
        
        filaSaida = (filaSaida + 1) % Manipulador.getTamanho_buffer();
        contador = contador - 1;
        
        notifyAll();
        
        return I;
    }
    
    @Override
    public synchronized void criarFantasma() throws RemoteException {
    	//Pausa a cria��o de fantasmas caso o n�mero de fantasmas seja do tamanho do buffer
        while (contador == Manipulador.getTamanho_buffer()) {
            try {
                wait();
            } catch (InterruptedException e) {
            	System.out.println("Falha ao CriarFantasma: " + e.toString());
            }
        }
        
        contador = contador +1;
        vetorBuffer[filaEntrada] = contador;
        filaEntrada = (filaEntrada + 1) % Manipulador.getTamanho_buffer();

        notifyAll(); 
    }

    public static void main(String args[]) throws MalformedURLException {
        try {    
        	
        	Manipulador.CarregarDadosSensiveis();
        	BufferDeFantasmas buffer = new BufferDeFantasmas();

            java.rmi.registry.LocateRegistry.createRegistry(Manipulador.getPort());
            
            Naming.rebind(Manipulador.getHost() + Manipulador.getPort() + Manipulador.getBuffer(), buffer);  
            
            ProdutorDeFantasmas fantasmaProdutor = new ProdutorDeFantasmas(buffer);
            fantasmaProdutor.start();
            
            System.out.println("Dispon�vel para inst�ncias Pacman!");
            
        } catch (RemoteException e) {
            System.out.println("Falha no Buffer: " + e.toString());
        }
    }
}
