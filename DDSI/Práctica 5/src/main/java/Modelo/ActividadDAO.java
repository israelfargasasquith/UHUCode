/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author 34667
 */
public class ActividadDAO {
    
    public ArrayList<Actividad> listaActividades(Session sesion){
        ArrayList<Actividad> lActividades = new ArrayList<>();
        
        Query consulta = sesion.createNativeQuery("SELECT * FROM ACTIVIDAD ",Actividad.class);
        lActividades = (ArrayList<Actividad>) consulta.list();
        return lActividades;
    }

    public Monitor monitorResponsable(Session sesion, String actividad) {
        Monitor resultado;
        ArrayList<Object[]> camposMonitor =new ArrayList<>();
        
        Query consulta = sesion.createQuery("SELECT m.codMonitor, m.nombre, m.dni, m.telefono, m.correo, m.fechaEntrada, m.nick "
                                           + "FROM Actividad a  "
                                           + "JOIN a.monitorResponsable m "
                                           + "WHERE a.idActividad =:actividad");
        consulta.setParameter("actividad", actividad);
        camposMonitor = (ArrayList<Object[]>) consulta.list();
        resultado = new Monitor(camposMonitor.get(0)[0].toString(), camposMonitor.get(0)[1].toString(), camposMonitor.get(0)[2].toString(),
                camposMonitor.get(0)[3].toString(), camposMonitor.get(0)[4].toString(), camposMonitor.get(0)[5].toString(), 
                camposMonitor.get(0)[6].toString());
        return resultado;
    }
    
    public void inscribeSocio(Session sesion, String nombreSocio, String idActividad) {
        Socio socioAInscribir;
        
        Query query = sesion.createNamedQuery("Socio.findByNombre",Socio.class).setParameter("nombre", nombreSocio);
        socioAInscribir = (Socio) query.getSingleResult();
        
        Actividad actividadEnLaQueInscribir = sesion.get(Actividad.class, idActividad);
        
        actividadEnLaQueInscribir.altaSocio(socioAInscribir);
        sesion.saveOrUpdate(actividadEnLaQueInscribir);
    }

    public void desinscribeSocio(Session sesion, String nombreSocio, String idActividad) {
        Socio socioADesinscribir;
        
        Query query = sesion.createNamedQuery("Socio.findByNombre",Socio.class).setParameter("nombre", nombreSocio);
        socioADesinscribir = (Socio) query.getSingleResult();
        
        Actividad actividadDeLaQueBorrar = sesion.get(Actividad.class, idActividad);
        
        actividadDeLaQueBorrar.bajaSocio(socioADesinscribir);
        sesion.saveOrUpdate(actividadDeLaQueBorrar);
    }

    public void sociosPorActividad(Session sesion) {
        Actividad actividadElegida = sesion.get(Actividad.class, "AC01");
        Set<Socio> sociosApuntados =  actividadElegida.getSocios();
        
        System.out.println("En la actividad AC01 estan apuntados: ");
        for (Socio sociosApuntado : sociosApuntados) {
            System.out.println("Id: "+sociosApuntado.getNumeroSocio() +" Nombre: "+sociosApuntado.getNombre() + " Telefono: "+sociosApuntado.getTelefono());
        }
    }
    
   
}
