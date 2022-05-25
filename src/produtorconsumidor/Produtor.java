package produtorconsumidor;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Produtor extends Thread{
    private final BufferInterface buffer;
    private final int mensagens;
    
    public Produtor(BufferInterface b, int mensagens) {
        this.buffer = b;
        this.mensagens = mensagens;
    }

    @Override
    public void run() {

        for (int i = 1; i <= this.mensagens; i++) 
        {
            try 
            {               
                buffer.append(i);
            } catch (RemoteException ex) {
                Logger.getLogger(Produtor.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println("-----------------------\nPRODUTOR colocou: " + i);
            try {
                sleep((int)(Math.random() * 100));
            } catch (InterruptedException e) { }
        }
    }
}
