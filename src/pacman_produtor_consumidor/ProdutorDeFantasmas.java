package pacman_produtor_consumidor;

import java.rmi.RemoteException;

public class ProdutorDeFantasmas extends Thread{
    private final BufferInterface buffer;
    
    public ProdutorDeFantasmas(BufferInterface buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while(true) {
            try {               
                buffer.criarFantasma();
            } catch (RemoteException e) {
            	System.out.println("ProdutorDeFantasmas: " + e.toString());
            }
        }
    }
}
