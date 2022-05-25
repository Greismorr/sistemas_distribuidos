package produtorconsumidor;

import java.rmi.Remote;
import java.rmi.RemoteException; 

public interface BufferInterface extends Remote
{
    static final int TAMANHO = 5;
    int[] Vetor = new int[TAMANHO];
    int FilaDeEntrada=0, FilaDeSaida=0, Contador=0;
    
    public int take() throws RemoteException;
    public void append(int value) throws RemoteException;
}
