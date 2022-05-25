package produtorconsumidor;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Produtor extends Thread
{
    private final BufferInterface buffer;
    private final int mensagens;
    
    public Produtor(BufferInterface buffer, int mensagens) 
    {
        this.buffer = buffer;
        this.mensagens = mensagens;
    }

    @Override
    public void run() 
    {
        for (int contador = 1; contador <= this.mensagens; contador++) 
        {
            try 
            {               
                buffer.append(contador);
            } catch (RemoteException ex) {
                Logger.getLogger(Produtor.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println("-----------------------\nPRODUTOR colocou: " + contador);
            try {
                sleep((int)(Math.random() * 100));
            } catch (InterruptedException e) { }
        }
    }
}
