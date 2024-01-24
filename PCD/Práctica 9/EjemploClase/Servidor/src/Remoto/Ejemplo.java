/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Remoto;

import IRemoto.IEjemplo;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author pedro
 */

public class Ejemplo extends UnicastRemoteObject implements IEjemplo {
    
    private int contador;
    
    public Ejemplo() throws RemoteException{
        super();
        contador = 0;
    }
    
    @Override
    public synchronized int incrementa(String quien, int cuanto){
        contador+=cuanto;
        System.out.println(quien+ " incrementa en "+cuanto+ " y queda en "+contador+ " ejecutado desde: "+
                Thread.currentThread().getName());
        return contador;
    }
}

//public class Ejemplo  extends UnicastRemoteObject implements IEjemplo{
//
//    int contador=0;
//    
//    public Ejemplo() throws RemoteException{
//        //super();
//    }
//    
//    @Override
//    public synchronized int incrementa(String quien, int valor) throws RemoteException {
//        contador=contador+valor;
//        System.out.println(quien+" incrementa en contador en "+valor+" y lo deja en "+contador);
//        return contador;
//    }
//    
//}
