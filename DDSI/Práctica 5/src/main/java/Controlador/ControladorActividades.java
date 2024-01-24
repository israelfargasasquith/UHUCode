/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.ActividadDAO;
import Modelo.SocioDAO;
import Modelo.UtilTablasActividades;
import Vista.VistaAltas;
import Vista.VistaMensajes;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author 34667
 */
public class ControladorActividades implements ActionListener {

    private VistaAltas vActividad;
    private SessionFactory sesionFactory;
    private UtilTablasActividades tablasActividades;
    private SocioDAO socioDAO;
    private ActividadDAO actividadDAO;
    private VistaMensajes vMensajes;
    String actividadSeleccionada[];

    public ControladorActividades(SessionFactory sesionFactory, VistaMensajes vMensajes) {
        socioDAO = new SocioDAO();
        actividadDAO = new ActividadDAO();
        this.vActividad = new VistaAltas();
        this.sesionFactory = sesionFactory;
        this.actividadSeleccionada = new String[2];
        actividadSeleccionada[1] = "ninguna";
        this.tablasActividades = new UtilTablasActividades(vActividad);
        this.vMensajes = vMensajes;

        vActividad.setVisible(false);
        vActividad.setLocationRelativeTo(null);
        vActividad.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        vActividad.setResizable(false);

        rellenarComboSocio(sesionFactory.openSession());
        vActividad.jComboBoxSocios.setSelectedIndex(0);

        tablasActividades.dibujaTablaActividades(vActividad);
        rellenarTablaAltas(sesionFactory.openSession());

        addListeners();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "cambioSocio":

                tablasActividades.vaciarTablaAltas();
                rellenarTablaAltas(sesionFactory.openSession());
                break;

            case "Dar de  Alta":
                if (actividadSeleccionada[1].equalsIgnoreCase("ninguna")) {
                    vMensajes.muestraMensaje(vActividad, "Error: no se puede dar de alta porque no hay ninguna actividad seleccionada", VistaMensajes.enumMensajes.ERROR);
                } else if (actividadSeleccionada[1].equalsIgnoreCase("✅")) {
                    vMensajes.muestraMensaje(vActividad, "Error: no se puede dar de alta porque ya esta dada de alta la actividad: " + actividadSeleccionada[0], VistaMensajes.enumMensajes.ERROR);
                } else {
                    darAlta(sesionFactory.openSession());
                    vMensajes.muestraMensaje(vActividad, "Todo correcto: se ha dado de alta la actividad " + actividadSeleccionada[0], VistaMensajes.enumMensajes.INFORMACION);
                }
                actividadSeleccionada[1] = "ninguna";
                break;
            case "Dar De Baja":
                if (actividadSeleccionada[1].equalsIgnoreCase("ninguna")) {
                    vMensajes.muestraMensaje(vActividad, "Error: no se puede dar de baja porque no hay ninguna actividad seleccionada", VistaMensajes.enumMensajes.ERROR);
                } else if (actividadSeleccionada[1].equalsIgnoreCase("❌")) {
                    vMensajes.muestraMensaje(vActividad, "Error: no se puede dar de baja porque ya esta dada de baja la actividad: " + actividadSeleccionada[0], VistaMensajes.enumMensajes.ERROR);
                } else {
                    darBaja(sesionFactory.openSession());
                    vMensajes.muestraMensaje(vActividad, "Todo correcto: se ha dado de baja la actividad " + actividadSeleccionada[0], VistaMensajes.enumMensajes.INFORMACION);
                }
                actividadSeleccionada[1] = "ninguna";
                break;
            case "CancelarAlta":
                vActividad.dispose();
                break;
            default:
            //System.out.println("Te has equivocao con los action comand");
        }
    }

    public void gestionarAltas() {
        vaciarComboSocios();
        rellenarComboSocio(sesionFactory.openSession());
        tablasActividades.vaciarTablaAltas(); //Una posible mejora es solo borrar la ultima fila, pero habria que cambiar el addRow de rellenar tabla
        rellenarTablaAltas(sesionFactory.openSession());
        vActividad.setVisible(true);
    }

    private void vaciarComboSocios() {
        vActividad.jComboBoxSocios.removeAllItems();
    }

    private void rellenarComboSocio(Session sesion) {

        Transaction transacion = sesion.beginTransaction();
        try {
            ArrayList<String> lSocios = new ArrayList<>();
            lSocios = socioDAO.listaSociosNombre(sesion);
            for (String lSocio : lSocios) {
                vActividad.jComboBoxSocios.addItem(lSocio);
            }
        } catch (Exception e) {
            transacion.rollback();
            vMensajes.muestraMensaje(vActividad, "Error con la transaccion rellenaComboSocio " + e.getMessage(), VistaMensajes.enumMensajes.ERROR);
            //System.out.println("Error con la transaccion rellenarTablaMonitores " + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }

    }

    private void darAlta(Session sesion) {
        Transaction transacion = sesion.beginTransaction();
        try {
            actividadDAO.inscribeSocio(sesion, vActividad.jComboBoxSocios.getSelectedItem().toString(), actividadSeleccionada[0]);
            tablasActividades.vaciarTablaAltas();
            tablasActividades.rellenarTablaAltas(actividadDAO.listaActividades(sesion),
                    socioDAO.actividadesSocio(sesion, vActividad.jComboBoxSocios.getSelectedItem().toString()));
            transacion.commit();
        } catch (Exception e) {
            transacion.rollback();
            vMensajes.muestraMensaje(vActividad, "Error con la transaccion darAlta " + e.getMessage(), VistaMensajes.enumMensajes.ERROR);
            //System.out.println("Error con la transaccion rellenaTablaSocios " + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }

    private void darBaja(Session sesion) {
        Transaction transacion = sesion.beginTransaction();
        try {
            actividadDAO.desinscribeSocio(sesion, vActividad.jComboBoxSocios.getSelectedItem().toString(), actividadSeleccionada[0]);
            tablasActividades.vaciarTablaAltas();
            tablasActividades.rellenarTablaAltas(actividadDAO.listaActividades(sesion),
                    socioDAO.actividadesSocio(sesion, vActividad.jComboBoxSocios.getSelectedItem().toString()));
            transacion.commit();
        } catch (Exception e) {
            transacion.rollback();
            vMensajes.muestraMensaje(vActividad, "Error con la transaccion darBaja " + e.getMessage(), VistaMensajes.enumMensajes.ERROR);
            //System.out.println("Error con la transaccion rellenaTablaSocios " + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }

    private void rellenarTablaAltas(Session sesion) {
        Transaction transacion = sesion.beginTransaction();
        try {
            tablasActividades.vaciarTablaAltas();
            tablasActividades.rellenarTablaAltas(actividadDAO.listaActividades(sesion),
                    socioDAO.actividadesSocio(sesion, vActividad.jComboBoxSocios.getSelectedItem().toString()));
            transacion.commit();
        } catch (Exception e) {
            transacion.rollback();
            //System.out.println("Error con la transaccion rellenaTablaSocios " + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }

    private void seleccionActividad(MouseEvent e) {
        if (vActividad.jTableActividades.getSelectedRow() != -1) {
            actividadSeleccionada[0] = (String) vActividad.jTableActividades.getValueAt(vActividad.jTableActividades.getSelectedRow(), 0);
            actividadSeleccionada[1] = (String) vActividad.jTableActividades.getValueAt(vActividad.jTableActividades.getSelectedRow(), 2);
            //System.out.println("Codigo actividad seleccionada: " + actividadSeleccionada[0] + " inscrito?: " + actividadSeleccionada[1]);
        }
    }

    private void addListeners() {
        vActividad.jButtonCerrar.addActionListener(this);
        vActividad.jButtonDarAlta.addActionListener(this);
        vActividad.jButtonDarDeBaja.addActionListener(this);
        vActividad.jComboBoxSocios.addActionListener(this);
        vActividad.jTableActividades.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seleccionActividad(e);
            }
        });
    }

}
