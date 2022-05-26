package pacman_produtor_consumidor;

import java.rmi.RemoteException;

public class PacmanConsumidor extends Thread {
    private final BufferInterface buffer;

    public PacmanConsumidor(BufferInterface buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
    	while(true) {
            try {
                int valorBuffer = buffer.comer();
                
                System.out.print("\u15e7-");
                
                for(int currentNumber = 1; currentNumber <= valorBuffer; currentNumber++) {
                    System.out.print("-\u15e3-");
                }
                System.out.printf("%n");
            } catch (RemoteException e) {
            	e.printStackTrace();
            }
    	}
    }
}
