package produtorconsumidor;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumidor extends Thread {
    private final BufferInterface buffer;
    private final int mensagens;

    public Consumidor(BufferInterface b, int mensagens) {
        this.buffer = b;
        this.mensagens = mensagens;
    }

    @Override
    public void run() {

        for (int i = 1; i <= this.mensagens; i++) 
        {
            int v = 0;
            try 
            {
                v = buffer.take();
            } catch (RemoteException ex) {
                Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //Exibe na tela o valor retirado
            System.out.println("-----------------------\nCONSUMIDOR retirou: " + v);
            try {
                sleep((int)(Math.random() * 100));
            } catch (InterruptedException e) { }
        }
    }
}
