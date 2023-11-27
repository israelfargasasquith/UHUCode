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
public class SocioDAO {
    
    public ArrayList<Socio> listaSocios(Session sesion) {
        ArrayList<Socio> lSocios = new ArrayList<Socio>();
        Query consulta;
       
            consulta = sesion.createNamedQuery("Socio.findAll",Socio.class);
            lSocios = (ArrayList<Socio>) consulta.list();

        
        return lSocios;
    }

    public ArrayList<Socio> solicitaSocios(String tipoLlamada, Session sesion) {
        ArrayList<Socio> lSocios = new ArrayList<>();
        Query consulta;
        if (tipoLlamada.equalsIgnoreCase("HQL") || tipoLlamada.equalsIgnoreCase("1")) {
            System.out.println("Se mostrara una llamada HQL");
            consulta = sesion.createQuery("FROM Socio s", Socio.class); //Hay que ponerlo asi que mariadb se raya con los nombres
            lSocios = (ArrayList<Socio>) consulta.list();

        } else if (tipoLlamada.equalsIgnoreCase("SQLNativo") || tipoLlamada.equalsIgnoreCase("2")) {
            System.out.println("Se mostrara una llamada SQL");

            consulta = sesion.createNativeQuery("SELECT * FROM SOCIO", Socio.class);
            lSocios = (ArrayList<Socio>) consulta.list();

        } else if (tipoLlamada.equalsIgnoreCase("NamedQuery") || tipoLlamada.equalsIgnoreCase("3")) {
            System.out.println("Se mostrara una llamada NamedQuery");

            consulta = sesion.createNamedQuery("Socio.findAll", Socio.class);
            lSocios = (ArrayList<Socio>) consulta.list();

        }
        return lSocios;
    }

    public ArrayList<Object[]> nombreTelefono(Session sesion) {
        ArrayList<Object[]> nombreTlfSocios = new ArrayList<>();

        Query consulta = sesion.createQuery("SELECT s.nombre, s.telefono FROM Socio s");
        nombreTlfSocios = (ArrayList<Object[]>) consulta.list();

        return nombreTlfSocios;
    }

    public ArrayList<Object[]> nombreCategoria(Session sesion, String categoria) {
        ArrayList<Object[]> nombreCategoriaSocios = new ArrayList<>();

        Query consulta = sesion.createQuery("SELECT s.nombre, s.categoria FROM Socio s WHERE s.categoria=:categoria");
        consulta.setParameter("categoria", categoria.charAt(0));
        nombreCategoriaSocios = (ArrayList<Object[]>) consulta.list();

        return nombreCategoriaSocios;
    }
    
    public ArrayList<String> sociosPorActividad(Session sesion, String idActividad){
        ArrayList<String> nombreSociosActividad = new ArrayList<>();

        Query consulta = sesion.createQuery("SELECT s.nombre FROM Socio s JOIN s.actividades a WHERE a.id=:idActividad");
        consulta.setParameter("idActividad", idActividad);
        nombreSociosActividad = (ArrayList<String>) consulta.list();
        
        return nombreSociosActividad;
    }
    
   
    
    public void bajaSocio(Session sesion){
        
        Socio borraSocio = sesion.getReference(Socio.class, "S011");
        sesion.delete(borraSocio);
        
    } 
    

    public void altaSocio(Session sesion) {
        Socio nuevoSocio = new Socio("S011", "Temporal", "11111111S", "11/11/1111","111111111","temporal@gmail.com","11/11/1111", 'A');
        sesion.saveOrUpdate(nuevoSocio);
    }

    public void actualizaCategoriaSocio(Session sesion) {
        Socio actualizaSocio = sesion.get(Socio.class, "S010");
        actualizaSocio.setCategoria('A');
        sesion.saveOrUpdate(actualizaSocio);
    }

    public void actividadesSocio(Session sesion) {
        Socio verActividadesSocio = sesion.get(Socio.class, "S001");
        Set<Actividad> lActividades = verActividadesSocio.getActividades();
        
        System.out.println("La lista de actividades del socio S001 son:");
        for (Actividad lActividade : lActividades) {
            System.out.println("Id: "+lActividade.getIdActividad()+ " Nombre: "+lActividade.getNombre()+ " Precio base: "+lActividade.getPrecioBaseMes());
        }
    }

}
