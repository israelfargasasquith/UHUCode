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

    public ArrayList<Socio> listaSocios(Session sesion, String criterio) {
        ArrayList<Socio> lSocios = new ArrayList<Socio>();
        Query consulta;
        if (criterio.equalsIgnoreCase("sin criterio")) {
            
            consulta = sesion.createNamedQuery("Socio.findAll", Socio.class);
            lSocios = (ArrayList<Socio>) consulta.list();
        
        } else {
             consulta = sesion.createNamedQuery("Socio.findByCategoria", Socio.class).setParameter("categoria", criterio.charAt(0));
            lSocios = (ArrayList<Socio>) consulta.list();
        }
        return lSocios;
    }

    public ArrayList<String> listaSociosNombre(Session sesion) {
        ArrayList<String> lSocios = new ArrayList<String>();
        Query consulta;

        consulta = sesion.createQuery("SELECT s.nombre FROM Socio s", String.class);
        lSocios = (ArrayList<String>) consulta.list();

        return lSocios;
    }

    public Socio socioPorNumero(Session sesion, String numSocio) {

        Socio buscado = sesion.getReference(Socio.class, numSocio);
        return buscado;
    }

    public String siguienteNum(Session sesion) {
        ArrayList<String> lCodigos = new ArrayList<>();
        Query consulta;

        consulta = sesion.createQuery("SELECT s.numeroSocio FROM Socio s ORDER BY numeroSocio");
        lCodigos = (ArrayList<String>) consulta.list();
        int siguienteCod = -1;
        String devuelve;
        for (int i = 0; i < lCodigos.size(); i++) {
            siguienteCod = Integer.parseInt(lCodigos.get(i).substring(1, lCodigos.get(i).length()));
            if (siguienteCod > i + 1) {
                siguienteCod = i + 1;
                break;
            }
        }

        devuelve = String.format("S%03d", siguienteCod);
        int ultimoCodigo = Integer.parseInt(lCodigos.get(lCodigos.size() - 1).substring(1, lCodigos.get(lCodigos.size() - 1).length()));
        if (siguienteCod == -1 || siguienteCod == ultimoCodigo) {
            devuelve = String.format("S%03d", lCodigos.size() + 1);
        }
        return devuelve;
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

    public ArrayList<String> sociosPorActividad(Session sesion, String idActividad) {
        ArrayList<String> nombreSociosActividad = new ArrayList<>();

        Query consulta = sesion.createQuery("SELECT s.nombre FROM Socio s JOIN s.actividades a WHERE a.id=:idActividad");
        consulta.setParameter("idActividad", idActividad);
        nombreSociosActividad = (ArrayList<String>) consulta.list();

        return nombreSociosActividad;
    }

    public void bajaSocio(Session sesion, String numSocio) {

        Socio borraSocio = sesion.getReference(Socio.class, numSocio);
        sesion.delete(borraSocio);

    }

    public void altaSocio(Session sesion, Socio nuevo) {
        sesion.saveOrUpdate(nuevo);
    }

    public void actualizaCategoriaSocio(Session sesion) {
        Socio actualizaSocio = sesion.get(Socio.class, "S010");
        actualizaSocio.setCategoria('A');
        sesion.saveOrUpdate(actualizaSocio);
    }

    public ArrayList<Actividad> actividadesSocio(Session sesion, String nombreSocio) {
        Socio socio;

        Query consulta = sesion.createNamedQuery("Socio.findByNombre", Socio.class).setParameter("nombre", nombreSocio);

        socio = (Socio) consulta.getSingleResult(); //NO PUEDE HABER EN LA BD SOCIOS CON EL MISMO NOMBRE

        Socio verActividadesSocio = sesion.get(Socio.class, socio.getNumeroSocio());
        Set<Actividad> lSetActividades = verActividadesSocio.getActividades();
        ArrayList<Actividad> lActividades = new ArrayList<>(lSetActividades);
//        System.out.println("La lista de actividades del socio " + socio.getNumeroSocio() + " son:");
//        for (Actividad lActividade : lSetActividades) {
//            System.out.println("Id: " + lActividade.getIdActividad() + " Nombre: " + lActividade.getNombre() + " Precio base: " + lActividade.getPrecioBaseMes());
//        }
        return lActividades;
    }

}
