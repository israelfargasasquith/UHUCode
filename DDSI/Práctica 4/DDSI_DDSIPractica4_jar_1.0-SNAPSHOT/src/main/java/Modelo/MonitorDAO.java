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
}
