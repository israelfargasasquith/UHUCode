/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.ActividadDAO;
import Modelo.Monitor;
import Modelo.SocioDAO;
import Vista.VistaPrincipal;
import static java.lang.Thread.sleep;
import java.time.Duration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author 34667
 */
public class ControladorPrincipal {

    private SessionFactory sesionFactory;
    private Transaction transacion;
    private VistaPrincipal vistaPrincipal;
    private SocioDAO socioDAO;
    private ActividadDAO actividadDAO;
    private Session sesion;

    public ControladorPrincipal(SessionFactory sesionFactory) throws InterruptedException {
        this.sesionFactory = sesionFactory;
        this.vistaPrincipal = new VistaPrincipal();
        socioDAO = new SocioDAO();
        actividadDAO = new ActividadDAO();
        String opc = "0";
        while (!opc.equals("6")) {
            opc = vistaPrincipal.muestraMenuPrincipal();
            /*
                 1.- Mostrar todos los socios\n"
                + "2.- Mostrar nombre y teléfono de los socios\n"
                + "3.- Mostrar nombre y categoría de los socios\n"
                + "4.- Mostrar el responsable de la actividad que usted elija\n"
                + "5.- Mostrar el nombre de todos los socios de la actividad que usted elija\n"
                + "6.- Salir"
             */

            switch (opc) {
                case "1":
                    String opc2 = "0";
                    opc2 = vistaPrincipal.subMenuMostrarSocios();
                    /*
                       1.-  HQL\n"
                    + "2.-  SQLNativo\n"
                    + "3.-  NamedQuery\n"
                     */
                    switch (opc2) {
                        case "HQL":
                        case "1":
                        case "SQLNativo":
                        case "2":
                        case "NamedQuery":
                        case "3":
                            muestraSocio(opc2);
                            break;
                        default:
                            System.out.println("Error elije una opcion de la pantalla, por favor");
                            sleep(Duration.ofSeconds(2));
                    }
                    break;
                case "2":
                    muestraNombreTelefonoSocio();
                    break;
                case "3":
                    muestraNombreCategoriaSocio();
                    break;
                case "4":
                    responsableActividad();
                    break;
                case "5":
                    muestraSociosPorActividad();
                    break;
                case "6":
                    break;
                case "7":
                    altaSocio();
                    break;
                case "8":
                    bajaSocio();
                    break;
                case "9":
                    actualizarCategoriaSocio();
                    break;
                case "10":
                    inscribirSocioEnActividad();
                    break;
                case "11":
                    bajaSocioDeActividad();
                    break;
                case "12":
                    listaSociosPorActividad();
                    break;
                case "13":
                    listaActividadesSocio();
                    break;
                default:
                    System.out.println("Error elije una opcion de la pantalla, por favor");
                    sleep(Duration.ofSeconds(2));

            }

        }

    }

    private void muestraSocio(String opc) {
        sesion = sesionFactory.openSession();
        transacion = sesion.beginTransaction();
        try {
            vistaPrincipal.muestraSocios(socioDAO.solicitaSocios(opc, sesion));
            transacion.commit();
        } catch (Exception e) {
            transacion.rollback();
            System.out.println("Error con la transaccion HQL mostrar socios " + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }

    private void muestraNombreTelefonoSocio() {
        sesion = sesionFactory.openSession();
        transacion = sesion.beginTransaction();
        try {
            vistaPrincipal.muestraNombreTelefonoSocios(socioDAO.nombreTelefono(sesion));
            transacion.commit();
        } catch (Exception e) {
            transacion.rollback();
            System.out.println("Error con la transaccion nombre telefono socios " + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }

    private void muestraNombreCategoriaSocio() {
        sesion = sesionFactory.openSession();
        transacion = sesion.beginTransaction();
        try {
            String categoria = vistaPrincipal.solicitaCategoria();
            if (!categoria.equalsIgnoreCase("")) {
                vistaPrincipal.muestraNombreCategoriaSocios(socioDAO.nombreCategoria(sesion, categoria));
            } else {
                System.out.println("Debe pedir una categoria disponible...");
            }
            transacion.commit();
        } catch (Exception e) {
            transacion.rollback();
            System.out.println("Error con la transaccion nombre categoria socios " + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }

    private void responsableActividad() {
        sesion = sesionFactory.openSession();
        transacion = sesion.beginTransaction();
        try {
            Monitor monitor = actividadDAO.monitorResponsable(sesion, vistaPrincipal.pideCodigoActividad());
            vistaPrincipal.muestraResponsable(monitor);
            transacion.commit();
        } catch (Exception e) {
            transacion.rollback();
            System.out.println("Error con la transaccion responsable actividad " + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }

    private void muestraSociosPorActividad() {
        sesion = sesionFactory.openSession();
        transacion = sesion.beginTransaction();
        try {
            vistaPrincipal.muestraSociosPorActividad(socioDAO.sociosPorActividad(sesion, vistaPrincipal.pideCodigoActividad()));
            transacion.commit();
        } catch (Exception e) {
            transacion.rollback();
            System.out.println("Error con la transaccion responsable actividad " + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }

    private void altaSocio() {
        sesion = sesionFactory.openSession();
        transacion = sesion.beginTransaction();
        try {
            vistaPrincipal.altaSocio();
            socioDAO.altaSocio(sesion);
            transacion.commit();
        } catch (Exception e) {
            transacion.rollback();
            System.out.println("Error con la transaccion alta socio " + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }

    private void bajaSocio() {
        sesion = sesionFactory.openSession();
        transacion = sesion.beginTransaction();
        try {
            vistaPrincipal.bajaSocio();
            socioDAO.bajaSocio(sesion);
            transacion.commit();
        } catch (Exception e) {
            transacion.rollback();
            System.out.println("Error con la transaccion baja socio " + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }

    private void actualizarCategoriaSocio() {
        sesion = sesionFactory.openSession();
        transacion = sesion.beginTransaction();
        try {
            vistaPrincipal.actualizaCategoriaSocio();
            socioDAO.actualizaCategoriaSocio(sesion);
            transacion.commit();
        } catch (Exception e) {
            transacion.rollback();
            System.out.println("Error con la transaccion actualiza categoria socio " + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }

    private void inscribirSocioEnActividad() {
        sesion = sesionFactory.openSession();
        transacion = sesion.beginTransaction();
        try {
            vistaPrincipal.inscribeSocio();
            actividadDAO.inscribeSocio(sesion);
            transacion.commit();
        } catch (Exception e) {
            transacion.rollback();
            System.out.println("Error con la transaccion inscribir socio " + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }

    private void bajaSocioDeActividad() {
        sesion = sesionFactory.openSession();
        transacion = sesion.beginTransaction();
        try {
            vistaPrincipal.desinscribeSocio();
            actividadDAO.desinscribeSocio(sesion);
            transacion.commit();
        } catch (Exception e) {
            transacion.rollback();
            System.out.println("Error con la transaccion desinscribir socio " + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }

    private void listaSociosPorActividad() {
        sesion = sesionFactory.openSession();
        transacion = sesion.beginTransaction();
        try {
            vistaPrincipal.muestraSocioApuntadosEnActividad();
            actividadDAO.sociosPorActividad(sesion);
            transacion.commit();
        } catch (Exception e) {
            transacion.rollback();
            System.out.println("Error con la transaccion mostrar socios por actividad " + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }

    private void listaActividadesSocio() {
        sesion = sesionFactory.openSession();
        transacion = sesion.beginTransaction();
        try {
            vistaPrincipal.muestraActividadesDeSocio();
            socioDAO.actividadesSocio(sesion);
            transacion.commit();
        } catch (Exception e) {
            transacion.rollback();
            System.out.println("Error con la transaccion mostrar socios por actividad " + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }

}
