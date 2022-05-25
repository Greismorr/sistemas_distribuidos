package produtorconsumidor;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumidor extends Thread {
	
    private final BufferInterface buffer;
    private final int mensagens;

    public Consumidor(BufferInterface buffer, int mensagens) {
        this.buffer = buffer;
        this.mensagens = mensagens;
    }

    @Override
    public void run() {

        for (int contador = 1; contador <= this.mensagens; contador++) 
        {
            int valorBuffer = 0;
            try 
            {
                valorBuffer = buffer.take();
            } catch (RemoteException ex) {
                Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println("-----------------------\nCONSUMIDOR retirou: " + valorBuffer);
            try {
                sleep((int)(Math.random() * 100));
            } catch (InterruptedException e) { }
        }
    }
}
