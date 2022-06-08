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
                System.out.println("Pacman Consumidor");
                int valorBuffer = buffer.comer();
                
                //System.out.print("C<-");
                System.out.print("u15E7-"); - Pacman
                
                for(int currentNumber = 1; currentNumber <= valorBuffer; currentNumber++) {
                    System.out.print("-\u15e3-"); - Fantasma
                    //System.out.print("\\^^/-");
                }
                
                System.out.printf("%n");
                System.out.printf("PacmanConsumidor tem %d fantasmas para comer!", valorBuffer);
                System.out.printf("%n");
            } catch (RemoteException e) {
              	System.out.println("PacmanConsumidor: " + e.toString());
            }
    	}
    }
}
