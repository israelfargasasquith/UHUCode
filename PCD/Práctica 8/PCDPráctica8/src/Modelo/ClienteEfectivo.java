/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.concurrent.Callable;

/**
 *
 * @author 34667
 */
public class ClienteEfectivo implements Callable<Integer>{

    private  Tienda t;
    
    public ClienteEfectivo(Tienda t){
        this.t = t;
    }
    
    @Override
    public Integer call()  {
        Integer tiempo=null;
        t.entraTarjeta();
        sleep(5000);
        t.saleTajeta();
        
        
        return tiempo;
    }
    
}
