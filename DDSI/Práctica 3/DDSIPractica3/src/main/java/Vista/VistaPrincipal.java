/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import Modelo.Monitor;
import Modelo.Socio;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author 34667
 */
public class VistaPrincipal {

    private Scanner lector = new Scanner(System.in);

    public String muestraMenuPrincipal() {
        System.out.println("\n\n***********************************************************\n"
                + "Las operaciones que usted puede realizar son:\n"
                + "1.- Mostrar todos los socios\n"
                + "2.- Mostrar nombre y teléfono de los socios\n"
                + "3.- Mostrar nombre y categoría de los socios\n"
                + "4.- Mostrar el responsable de la actividad que usted elija\n"
                + "5.- Mostrar el nombre de todos los socios de la actividad que usted elija\n"
                + "6.- Salir\n"
                + "7.- Dar de alta socio\n"
                + "8.- Dar de baja un socio\n"
                + "9.- Actualiza categoria socio\n"
                + "10.- Inscribir socio en actividad\n"
                + "11.- Baja socio de actividad\n"
                + "12.- Lista socios por actividad\n"
                + "13.- Lista actividades de un socio\n"
                + "-------------------------------------------------------------------------\n"
                + "Por favor, elija una opcion:");

        return lector.next();
    }

    public String subMenuMostrarSocios() {
        System.out.println("\n\n***********************************************************\n"
                + "Hay 3 formas de solicitar los socios:\n"
                + "1.-  HQL\n"
                + "2.-  SQLNativo\n"
                + "3.-  NamedQuery\n"
                + "-------------------------------------------------------------------------\n"
                + "Por favor, elija una opcion:");
        return lector.next();
    }

    public void muestraSocios(ArrayList<Socio> lSocios) {
        System.out.println("*********************************"
                + "\n A continuación se muestran los socios del gym hay: " + lSocios.size() + " socios apuntados en total");
        for (Socio lSocio : lSocios) {
            System.out.println("Numero Socio: " + lSocio.getNumeroSocio() + "  Nombre: " + lSocio.getNombre() + "  DNI: " + lSocio.getDni()
                    + "  FechaNacimiento: " + lSocio.getFechaNacimiento() + "  Telefono: " + lSocio.getFechaNacimiento() + "  Correo: " + lSocio.getCorreo()
                    + "   Fecha Entrada: " + lSocio.getFechaEntrada() + "   Categoria: " + lSocio.getCategoria());
        }
    }

    public void muestraNombreTelefonoSocios(ArrayList<Object[]> lSocios) {
        System.out.println("*********************************"
                + "\n A continuación se muestran los telefonos de los socios del gym hay: " + lSocios.size() + " socios apuntados en total");

        for (Object[] lSocio : lSocios) {
            System.out.println("Nombre: " + lSocio[0] + "  Teléfono: " + lSocio[1]);
        }

    }

    public String solicitaCategoria() {
        System.out.println("*********************************"
                + "\n Introduzca la categoria de la que quiere extraer los socios, las categorias disponibles son A | B | C | D | E");
        String categoria = lector.next();
        if (categoria.equalsIgnoreCase("A") || categoria.equalsIgnoreCase("B") || categoria.equalsIgnoreCase("C")
                || categoria.equalsIgnoreCase("D") || categoria.equalsIgnoreCase("E")) {
            return categoria;
        } else {
            return "";
        }
    }

    public void muestraNombreCategoriaSocios(ArrayList<Object[]> lSocios) {
        System.out.println("*********************************"
                + "\n A continuación se muestran las categorias de los socios del gym hay: " + lSocios.size() + " socios apuntados en total");

        for (Object[] lSocio : lSocios) {
            System.out.println("Nombre: " + lSocio[0] + "  Categoria: " + lSocio[1]);
        }

    }

    public String pideCodigoActividad() {
        System.out.println("*********************************"
                + "\n Introduzca la actividad de la cual quiere saber el monitor responsable: | AC01 |");
        return lector.next();
    }

    public void muestraMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void muestraResponsable(Modelo.Monitor monitor) {
        System.out.println("*********************************"
                + "\n A continuación se muestran los campos del monitor responsable de la actividad solicitada previamente por mensaje.");
        System.out.println("CodMonitor: " + monitor.getCodMonitor() + "  Nombre: " + monitor.getNombre() + "  DNI: " + monitor.getDni());
    }

    public void muestraSociosPorActividad(ArrayList<String> lSocios) {
        System.out.println("*********************************"
                + "\n A continuación se muestran los socios apuntados a la actividad solicitada anteriormente por teclado");

        for (String lSocio : lSocios) {
            System.out.println("Nombre: " + lSocio);
        }
    }

    public void altaSocio() {
        System.out.println("*********************************"
                + "\n A continuación vamos a introducir el socio Temporal con dni 1111111S en la BD");

    }

    public void bajaSocio() {
        System.out.println("*********************************"
                + "\n A continuación vamos a borrar el socio Temporal con dni 1111111S de la BD");

    }

    public void actualizaCategoriaSocio() {
        System.out.println("*********************************"
                + "\n A continuación vamos a cambiar la categoria del socio S010 de E a A en la BD");
    }

    public void inscribeSocio() {
        System.out.println("*********************************"
                + "\n A continuación vamos a inscribir al socio S010 en la actividad AC01");
    }

    public void desinscribeSocio() {
        System.out.println("*********************************"
                + "\n A continuación vamos a desinscribir al socio S010 de la actividad AC01");
    }

    public void muestraSocioApuntadosEnActividad() {
        System.out.println("*********************************"
                + "\n A continuación vamos a mostrar la AC01 junto con sus socios apuntados al lado");
    }

    public void muestraActividadesDeSocio() {
         System.out.println("*********************************"
                + "\n A continuación vamos a mostrar las actividades en las que esta apuntado el socio S001");
    }

}
