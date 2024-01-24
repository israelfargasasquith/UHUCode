/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import IRemoto.IEjemplo;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pedro
 */
public class Cliente {

    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) {
//        try {
//            // TODO code application logic here
//
//             Registry Registro = LocateRegistry.getRegistry("172.16.172.1", 2015);
//             //Vemos lo que ofrece el registro
//             String[] oferta = Registro.list();
//             for (int i = 0; i < oferta.length; i++) {
//             System.out.println("Elemento " + i + " del registro: " + oferta[i]);
//             }
//             
//            IEjemplo objrem = (IEjemplo) Naming.lookup("rmi://172.16.172.1:2015/ejemremoto");
//            
//            int cont=objrem.incrementa("Pedro", 5);
//            System.out.println("He dejado el contador en "+cont);
//            
//        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
//            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException {
        //El cliente tendra que buscar con una URI al servidor RMI con rmi://[localhost]:[puerto]/[recurso]
        //en nuestro caso rmi://nuestraIP:2023/ejemploremoto
        //busca el objeto y devuelve una variable que "apunta"
        //a ese objeto
        //String uri = "rmi://172.17.22.39:2023/ejemploremoto";
        String uri = "rmi://localhost:2023/ejemploremoto"; 
        IEjemplo ejemp;
        ejemp = (IEjemplo) Naming.lookup(uri);
        int res = ejemp.incrementa("Isra",420);
        System.out.println("El contador vale "+res);
    }
}

