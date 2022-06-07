package pacman_produtor_consumidor;

import java.rmi.Remote;
import java.rmi.RemoteException; 

public interface BufferInterface extends Remote {
    public int comer() throws RemoteException;
    public void criarFantasma() throws RemoteException;
}
