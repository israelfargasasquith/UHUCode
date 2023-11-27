/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.ActividadDAO;
import Modelo.Monitor;
import Modelo.MonitorDAO;
import Modelo.SocioDAO;
import Modelo.UtilTablasMonitor;
import Modelo.UtilTablasSocio;
import Vista.VistaGestionMonitores;
import Vista.VistaGestionSocios;
import Vista.VistaNuevoMonitor;
import Vista.VistaPrincipal;
import Vista.VistaVacia;
import java.awt.CardLayout;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Thread.sleep;
import java.time.Duration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author 34667
 */
public class ControladorPrincipal implements ActionListener {

    private SessionFactory sesionFactory;
    private Session sesion;
    private Transaction transacion;

    private VistaPrincipal vistaPrincipal;
    private VistaGestionMonitores vMonitores;
    private VistaGestionSocios vSocios;
    private VistaVacia vVacia;
    private VistaNuevoMonitor vNuevoMonitor;

    private SocioDAO socioDAO;
    private ActividadDAO actividadDAO;
    private MonitorDAO monitorDAO;

    private UtilTablasMonitor tablasMonitores;
    private UtilTablasSocio tablasSocios;

    public ControladorPrincipal(SessionFactory sesionFactory) {
        this.sesionFactory = sesionFactory;

        this.vistaPrincipal = new VistaPrincipal();
        this.vMonitores = new VistaGestionMonitores();
        this.vSocios = new VistaGestionSocios();
        this.vVacia = new VistaVacia();
        this.vNuevoMonitor = new VistaNuevoMonitor();

        socioDAO = new SocioDAO();
        actividadDAO = new ActividadDAO();
        monitorDAO = new MonitorDAO();

        addActionListeners();

        vistaPrincipal.getContentPane().setLayout(new CardLayout());
        vistaPrincipal.add(vVacia);
        vistaPrincipal.add(vMonitores);
        vistaPrincipal.add(vSocios);

        tablasMonitores = new UtilTablasMonitor(vMonitores);
        tablasSocios = new UtilTablasSocio(vSocios);

        vVacia.setVisible(true);
        vMonitores.setVisible(false);
        vSocios.setVisible(false);

        vistaPrincipal.setResizable(false);
        vNuevoMonitor.setResizable(false);

        vistaPrincipal.setVisible(true);
        vistaPrincipal.setLocationRelativeTo(null);
        vistaPrincipal.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case "Gestión de Monitores":
                vVacia.setVisible(false);
                vSocios.setVisible(false);
                vMonitores.setVisible(true);

                tablasMonitores.dibujaTablaMonitores(vMonitores);
                rellenarTablaMonitores();

                break;
            case "Nuevo Monitor": //Hay que crear varios controladores para bajar la carga de trabajo del principal
                vNuevoMonitor.setLocationRelativeTo(null);
                vNuevoMonitor.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                vNuevoMonitor.setVisible(true);
                codigoMasAlto();          AQUI TE QUEDASTE, TIENES QUE BUSCAR EL CODIGO Y PONER EL TEXTFIELD EN GRIS       
                break;
            case "InsertarNuevoMonitor":
                break;
            case "CancelarNuevoMonitor":
                vNuevoMonitor.jTextCodigo.setText("");
                vNuevoMonitor.jTextCodigo.setText("");
                vNuevoMonitor.jTextCorreo.setText("");
                vNuevoMonitor.jTextDNI.setText("");
                vNuevoMonitor.jTextFecha.setText("");
                vNuevoMonitor.jTextNick.setText("");
                vNuevoMonitor.jTextNombre.setText("");
                vNuevoMonitor.jTextTelefono.setText("");
                vNuevoMonitor.dispose();
                break;
            case "Gestión de Socios":
                vVacia.setVisible(false);
                vMonitores.setVisible(false);
                vSocios.setVisible(true);

                tablasSocios.dibujaTablaSocioes(vSocios);
                rellenarTablaSocios();
                break;
                
                
            case "Salir de la aplicación":
                vistaPrincipal.dispose();
                System.exit(0);
                break;
            default:
                System.out.println("Te has equivocado al poner el nomrbe de la action command");
        }
    }

    
    private void codigoMasAlto(){ 
        sesion = sesionFactory.openSession();
        transacion = sesion.beginTransaction();
        try {
            tablasMonitores.vaciarTablaMonitores();
            tablasMonitores.rellenarTablaMonitores(monitorDAO.listaMonitores(sesion));
            transacion.commit();
        } catch (Exception e) {
            transacion.rollback();
            System.out.println("Error con la transaccion rellenarTablaMonitores " + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }
    private void rellenarTablaMonitores() {

        sesion = sesionFactory.openSession();
        transacion = sesion.beginTransaction();
        try {
            tablasMonitores.vaciarTablaMonitores();
            tablasMonitores.rellenarTablaMonitores(monitorDAO.listaMonitores(sesion));
            transacion.commit();
        } catch (Exception e) {
            transacion.rollback();
            System.out.println("Error con la transaccion rellenarTablaMonitores " + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }

    }

    private void rellenarTablaSocios() {

        sesion = sesionFactory.openSession();
        transacion = sesion.beginTransaction();
        try {
            tablasSocios.vaciarTablaSocioes();
            tablasSocios.rellenarTablaSocioes(socioDAO.listaSocios(sesion));
            transacion.commit();
        } catch (Exception e) {
            transacion.rollback();
            System.out.println("Error con la transaccion rellenarTablaMonitores " + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }

    }
    //estos son los de la P3
    public void muestraSocio(String opc) {
        sesion = sesionFactory.openSession();
        transacion = sesion.beginTransaction();
        try {
            //vistaPrincipal.muestraSocios(socioDAO.solicitaSocios(opc, sesion));
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

    public void muestraNombreTelefonoSocio() {
        sesion = sesionFactory.openSession();
        transacion = sesion.beginTransaction();
        try {
            // vistaPrincipal.muestraNombreTelefonoSocios(socioDAO.nombreTelefono(sesion));
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

    public void muestraNombreCategoriaSocio() {
        sesion = sesionFactory.openSession();
        transacion = sesion.beginTransaction();
        try {
            //String categoria = vistaPrincipal.solicitaCategoria();
            if (true) {//!categoria.equalsIgnoreCase("")) {
                // vistaPrincipal.muestraNombreCategoriaSocios(socioDAO.nombreCategoria(sesion, categoria));
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

    public void responsableActividad() {
        sesion = sesionFactory.openSession();
        transacion = sesion.beginTransaction();
        try {
            // Monitor monitor = actividadDAO.monitorResponsable(sesion, vistaPrincipal.pideCodigoActividad());
            //vistaPrincipal.muestraResponsable(monitor);
            transacion.commit();
        } catch (Exception e) {
            transacion.rollback();
            System.out.println("Error con la transaccion responsable actividad" + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }

    public void muestraSociosPorActividad() {
        sesion = sesionFactory.openSession();
        transacion = sesion.beginTransaction();
        try {
            //vistaPrincipal.muestraSociosPorActividad(socioDAO.sociosPorActividad(sesion, vistaPrincipal.pideCodigoActividad()));
            transacion.commit();
        } catch (Exception e) {
            transacion.rollback();
            System.out.println("Error con la transaccion responsable actividad" + e.getMessage());
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

    private void addActionListeners() {
        vistaPrincipal.jMenuItemMonitores.addActionListener(this);
        vistaPrincipal.jMenuItemActividades.addActionListener(this);
        vistaPrincipal.jMenuItemSalir.addActionListener(this);
        vistaPrincipal.jMenuItemSocios.addActionListener(this);

        vMonitores.jButtonNuevoMonitor.addActionListener(this);
        vMonitores.jButtonActualizaMonitor.addActionListener(this);
        vMonitores.jButtonBajaMonitor.addActionListener(this);

        vNuevoMonitor.jButtonCancelar.addActionListener(this);
        vNuevoMonitor.jButtonInsertar.addActionListener(this);

        vSocios.jButtonActualizaSocio.addActionListener(this);
        vSocios.jButtonBajaSocio.addActionListener(this);
        vSocios.jButtonNuevoSocio.addActionListener(this);

    }

}
