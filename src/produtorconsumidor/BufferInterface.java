package produtorconsumidor;

import java.rmi.Remote;
import java.rmi.RemoteException; 

public interface BufferInterface extends Remote
{
    static final int N = 5;
    int[] B = new int[N];
    int InPtr=0, OutPtr=0;
    int Count=0;
    
    public int take() throws RemoteException;
    public void append(int value) throws RemoteException;
}
