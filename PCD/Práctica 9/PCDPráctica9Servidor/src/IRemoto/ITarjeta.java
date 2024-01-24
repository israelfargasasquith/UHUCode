/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package IRemoto;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author israel
 */
public interface ITarjeta extends Remote{
    
   public void quiereEntrarTarjeta(int id);
   
   public int esperoAcabar();
    
}
