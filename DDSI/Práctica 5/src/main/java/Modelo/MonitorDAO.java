/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author 34667
 */
public class MonitorDAO {
     public ArrayList<Monitor> listaMonitores(Session sesion) {
        ArrayList<Monitor> lMonitores = new ArrayList<Monitor>();
        Query consulta;
       
            consulta = sesion.createNamedQuery("Monitor.findAll",Monitor.class);
            lMonitores = (ArrayList<Monitor>) consulta.list();

        
        return lMonitores;
    }
     
     public String siguienteCodigo(Session sesion){
         ArrayList<String> lCodigos = new ArrayList<>();
         Query consulta;
         
         consulta = sesion.createQuery("SELECT m.codMonitor FROM Monitor m ORDER BY codMonitor");
         lCodigos = (ArrayList<String>) consulta.list();
         int siguienteCod=-1;
         String devuelve;
         for (int i = 0; i < lCodigos.size(); i++) {
             siguienteCod = Integer.parseInt(lCodigos.get(i).substring(1,lCodigos.get(i).length()));
             if(siguienteCod > i+1){
                 siguienteCod = i+1;
                 break;
             }
         }
         
         devuelve = String.format("M%03d", siguienteCod);
         int ultimoCodigo = Integer.parseInt(lCodigos.get(lCodigos.size()-1).substring(1,lCodigos.get(lCodigos.size()-1).length()));
         if(siguienteCod == -1 || siguienteCod == ultimoCodigo){
             devuelve = String.format("M%03d", lCodigos.size()+1);
         }
         return devuelve;
         
     }
     
     public Monitor monitorPorCodigo(Session sesion, String codMonitor){
         Monitor monitor = null;
         Query consulta;
         
         consulta = sesion.createNamedQuery("Monitor.findByCodMonitor",Monitor.class)
                 .setParameter("codMonitor", codMonitor);
         monitor = (Monitor)consulta.getSingleResult();
         return monitor;
     }
     public void bajaMonitor(Session sesion, String monitorABorrar){
        
        Monitor borraMonitor = sesion.getReference(Monitor.class, monitorABorrar);
        sesion.delete(borraMonitor);  
    } 
     public void altaMonitor (Session sesion, Monitor nuevoMonitor){
        sesion.saveOrUpdate(nuevoMonitor);
     }
}
