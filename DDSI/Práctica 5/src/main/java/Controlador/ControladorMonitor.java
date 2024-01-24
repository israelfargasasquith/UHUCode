/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Monitor;
import Modelo.MonitorDAO;
import Modelo.UtilTablasMonitor;
import Vista.VistaActualizaMonitor;
import Vista.VistaGestionMonitores;
import Vista.VistaMensajes;
import Vista.VistaNuevoMonitor;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.Date;

/**
 *
 * @author 34667
 */
public class ControladorMonitor implements ActionListener {

    private VistaNuevoMonitor vNuevoMonitor;
    private MonitorDAO monitorDAO;
    private SessionFactory sesionFactory;
    private VistaMensajes vMensajes;
    private UtilTablasMonitor tablasMonitor;
    private VistaActualizaMonitor vActualizaMonitor;
    private SimpleDateFormat formatoFecha;
    private final String regexTelefono;
    private final String regexDNI;
    private final String regexCorreo;

    public ControladorMonitor(SessionFactory sesionFactory, VistaGestionMonitores vMonitor, VistaMensajes vMensajes) {
        this.vNuevoMonitor = new VistaNuevoMonitor();
        this.vActualizaMonitor = new VistaActualizaMonitor();
        this.monitorDAO = new MonitorDAO();
        this.sesionFactory = sesionFactory;
        this.vMensajes = vMensajes;
        this.tablasMonitor = new UtilTablasMonitor(vMonitor);
        this.formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        this.regexTelefono = "\\d{9}";
        this.regexDNI = "\\d{8}[A-Z && [^IOÑ]]";
        this.regexCorreo = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

        vNuevoMonitor.setVisible(false);
        vNuevoMonitor.setResizable(false);
        vNuevoMonitor.setLocationRelativeTo(null);

        vActualizaMonitor.setVisible(false);
        vActualizaMonitor.setResizable(false);
        vActualizaMonitor.setLocationRelativeTo(null);

        tablasMonitor.dibujaTablaMonitores(vMonitor);
        rellenarTablaMonitores(sesionFactory.openSession());

        addActionListeners();
    }

    //Estos metodos son los que llamamos desde el controlador principal
    public void nuevoMonitor() {
        codigoMasAlto(sesionFactory.openSession());
    }

    public void actualizaMonitor(String codMonitor) {
        rellenaVentanaConCodigo(codMonitor, sesionFactory.openSession());
    }

    public void bajaMonitor(String codMonitor) {
        if (JOptionPane.YES_OPTION == vMensajes.muestraMensaje(null, "¿Quiere eliminar al monitor " + codMonitor + " de la BD?", VistaMensajes.enumMensajes.SI_O_NO)) {
            bajaMonitor(codMonitor, sesionFactory.openSession());
            rellenarTablaMonitores(sesionFactory.openSession());

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {

            case "InsertarNuevoMonitor":
                Monitor nuevo = null;

                nuevo = comprobarTextNuevoMonitor();

                nuevoMonitor(sesionFactory.openSession(), nuevo);

                rellenarTablaMonitores(sesionFactory.openSession());
                vaciaVentanaNuevoMonitor();
                vNuevoMonitor.dispose();

                break;
            case "CancelarNuevoMonitor":

                vaciaVentanaNuevoMonitor();
                vNuevoMonitor.dispose();

                break;

            case "ActualizarMonitor":
                Monitor actualizado = null;

                actualizado = comprobarTextActualiza();

                actualizaMonitor(sesionFactory.openSession(), actualizado);

                rellenarTablaMonitores(sesionFactory.openSession());
                vaciarVentanaActualiza();
                vActualizaMonitor.dispose();
                break;
            case "CancelarActualizaMonitor":
                vaciarVentanaActualiza();
                vActualizaMonitor.dispose();
                break;

        }

    }

    private Monitor comprobarTextNuevoMonitor() {
        Monitor nuevo = null;
        if (!compruebaVacio('N')) {

            vMensajes.muestraMensaje(vNuevoMonitor, "Error: algun campo esta vacio", VistaMensajes.enumMensajes.ERROR);

        } else if (!compuebaDNI('N')) {
            vMensajes.muestraMensaje(vNuevoMonitor, "Error: el DNI no es valido", VistaMensajes.enumMensajes.ERROR);

        } else if (!compurebaCorreo('N')) {
            vMensajes.muestraMensaje(vNuevoMonitor, "Error: el correo no es valido", VistaMensajes.enumMensajes.ERROR);

        } else if (!compruebaTelefono('N')) {
            vMensajes.muestraMensaje(vNuevoMonitor, "Error: el telefono no es valido", VistaMensajes.enumMensajes.ERROR);

        } else if (!compruebaFechaEntrada('N')) {
            vMensajes.muestraMensaje(vNuevoMonitor, "Error: la fecha de entrada no es valida", VistaMensajes.enumMensajes.ERROR);

        } else { //faltan meter mas else if de codigo, correo, dni, fecha, nick, nombre, telefono
            nuevo = new Monitor(vNuevoMonitor.jTextCodigo.getText(), vNuevoMonitor.jTextNombre.getText(),
                    vNuevoMonitor.jTextDNI.getText(), vNuevoMonitor.jTextTelefono.getText(),
                    vNuevoMonitor.jTextCorreo.getText(), formatoFecha.format(vNuevoMonitor.jDateEntrada.getDate()),
                    vNuevoMonitor.jTextNick.getText());
        }
        return nuevo;
    }

    private Monitor comprobarTextActualiza() {
        Monitor nuevo = null;
        if (!compruebaVacio('A')) {

            vMensajes.muestraMensaje(vNuevoMonitor, "Error: algun campo esta vacio", VistaMensajes.enumMensajes.ERROR);

        } else if (!compuebaDNI('A')) {
            vMensajes.muestraMensaje(vNuevoMonitor, "Error: el DNI no es valido", VistaMensajes.enumMensajes.ERROR);

        } else if (!compurebaCorreo('A')) {
            vMensajes.muestraMensaje(vNuevoMonitor, "Error: el correo no es valido", VistaMensajes.enumMensajes.ERROR);

        } else if (!compruebaTelefono('A')) {
            vMensajes.muestraMensaje(vNuevoMonitor, "Error: el telefono no es valido", VistaMensajes.enumMensajes.ERROR);

        } else if (!compruebaFechaEntrada('A')) {
            vMensajes.muestraMensaje(vNuevoMonitor, "Error: la fecha de entrada no es valida", VistaMensajes.enumMensajes.ERROR);
        } else {
            nuevo = new Monitor(vActualizaMonitor.jTextCodigo.getText(), vActualizaMonitor.jTextNombre.getText(),
                    vActualizaMonitor.jTextDNI.getText(), vActualizaMonitor.jTextTelefono.getText(),
                    vActualizaMonitor.jTextCorreo.getText(), formatoFecha.format(vActualizaMonitor.jDateEntrada.getDate()),
                    vActualizaMonitor.jTextNick.getText());
        }
        return nuevo;
    }

    private void rellenarTablaMonitores(Session sesion) {
        Transaction transacion = sesion.beginTransaction();
        try {
            tablasMonitor.vaciarTablaMonitores();
            tablasMonitor.rellenarTablaMonitores(monitorDAO.listaMonitores(sesion));
            transacion.commit();
        } catch (Exception e) {
            transacion.rollback();
            vMensajes.muestraMensaje(null, "Error en la transaccion rellenarTablaMonitores", VistaMensajes.enumMensajes.ERROR);
            //System.out.println("Error con la transaccion rellenarTablaMonitores " + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }

    }

    private void bajaMonitor(String codMonitor, Session sesion) {
        Transaction transacion;
        transacion = sesion.beginTransaction();
        try {
            monitorDAO.bajaMonitor(sesion, codMonitor);
            transacion.commit();
        } catch (Exception e) {
            transacion.rollback();
            vMensajes.muestraMensaje(null, "Error en la transaccion bajaMonitor", VistaMensajes.enumMensajes.ERROR);
            //System.out.println("Error con la transaccion bajaMonitor " + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }

    private void nuevoMonitor(Session sesion, Monitor nuevo) {
        Transaction transacion;
        transacion = sesion.beginTransaction();
        try {
            monitorDAO.altaMonitor(sesion, nuevo);
            transacion.commit();
        } catch (Exception e) {
            transacion.rollback();
            vMensajes.muestraMensaje(vNuevoMonitor, "Error en la transaccion nuevoMonitor", VistaMensajes.enumMensajes.ERROR);
            //System.out.println("Error con la transaccion nuevoMonitor " + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }

    private void actualizaMonitor(Session sesion, Monitor actualiza) {
        Transaction transacion;
        transacion = sesion.beginTransaction();
        try {
            monitorDAO.altaMonitor(sesion, actualiza);
            transacion.commit();
        } catch (Exception e) {
            transacion.rollback();
            vMensajes.muestraMensaje(vActualizaMonitor, "Error en la transaccion actualizaMonitor", VistaMensajes.enumMensajes.ERROR);
            //System.out.println("Error con la transaccion actualizaMonitor " + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }

    private void rellenaVentanaConCodigo(String codMonitor, Session sesion) {
        Transaction transacion;
        transacion = sesion.beginTransaction();
        try {

            Monitor tmp = monitorDAO.monitorPorCodigo(sesion, codMonitor);
            vActualizaMonitor.jTextCodigo.setText(tmp.getCodMonitor());
            vActualizaMonitor.jTextCorreo.setText(tmp.getCorreo());
            vActualizaMonitor.jTextDNI.setText(tmp.getDni());
            vActualizaMonitor.jDateEntrada.setDate(formatoFecha.parse(tmp.getFechaEntrada()));
            vActualizaMonitor.jTextNick.setText(tmp.getNick());
            vActualizaMonitor.jTextNombre.setText(tmp.getNombre());
            vActualizaMonitor.jTextTelefono.setText(tmp.getTelefono());
            vActualizaMonitor.jTextCodigo.setEditable(false);
            vActualizaMonitor.setLocationRelativeTo(null);
            vActualizaMonitor.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
            vActualizaMonitor.setVisible(true);
            transacion.commit();
        } catch (Exception e) {
            transacion.rollback();
            vMensajes.muestraMensaje(vActualizaMonitor, "Error en la transaccion rellenaVentanaCodigo", VistaMensajes.enumMensajes.ERROR);
            //System.out.println("Error con la transaccion rellenaVentanaConCodigo " + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }

    private void codigoMasAlto(Session sesion) {
        Transaction transacion;
        transacion = sesion.beginTransaction();
        try {
            vNuevoMonitor.jTextCodigo.setText(monitorDAO.siguienteCodigo(sesion));
            vNuevoMonitor.jTextCodigo.setEditable(false);
            vNuevoMonitor.setLocationRelativeTo(null);
            vNuevoMonitor.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
            vNuevoMonitor.setVisible(true);
            transacion.commit();
        } catch (Exception e) {
            transacion.rollback();
            vMensajes.muestraMensaje(vNuevoMonitor, "Error en la transaccion codigoMasAlto", VistaMensajes.enumMensajes.ERROR);
            //System.out.println("Error con la transaccion codigoMasAlto " + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }

    private void vaciaVentanaNuevoMonitor() {
        vNuevoMonitor.jTextCodigo.setText("");
        vNuevoMonitor.jTextCodigo.setText("");
        vNuevoMonitor.jTextCorreo.setText("");
        vNuevoMonitor.jTextDNI.setText("");
        vNuevoMonitor.jDateEntrada.setDate(null);
        vNuevoMonitor.jTextNick.setText("");
        vNuevoMonitor.jTextNombre.setText("");
        vNuevoMonitor.jTextTelefono.setText("");
    }

    private void vaciarVentanaActualiza() {
        vActualizaMonitor.jTextCodigo.setText("");
        vActualizaMonitor.jTextCodigo.setText("");
        vActualizaMonitor.jTextCorreo.setText("");
        vActualizaMonitor.jTextDNI.setText("");
        vActualizaMonitor.jDateEntrada.setDate(null);
        vActualizaMonitor.jTextNick.setText("");
        vActualizaMonitor.jTextNombre.setText("");
        vActualizaMonitor.jTextTelefono.setText("");
    }

    private void addActionListeners() {
        vNuevoMonitor.jButtonCancelar.addActionListener(this);
        vNuevoMonitor.jButtonInsertar.addActionListener(this);

        vActualizaMonitor.jButtonActualizar.addActionListener(this);
        vActualizaMonitor.jButtonCancelar.addActionListener(this);
    }

    private boolean compuebaDNI(char tipo) {
        if (tipo == 'N') {
            String dni = vNuevoMonitor.jTextDNI.getText();
            return dni.matches(regexDNI);
        } else {
            return vActualizaMonitor.jTextDNI.getText().matches(regexDNI);
        }
    }

    private boolean compurebaCorreo(char tipo) {
        if (tipo == 'N') {
            String correo = vNuevoMonitor.jTextCorreo.getText();
            return correo.matches(regexCorreo);
        } else {
            return vActualizaMonitor.jTextCorreo.getText().matches(regexCorreo);
        }
    }

    private boolean compruebaTelefono(char tipo) {
        if (tipo == 'N') {
            String telefono = vNuevoMonitor.jTextTelefono.getText();
            return telefono.matches(regexTelefono);
        } else {
            return vActualizaMonitor.jTextTelefono.getText().matches(regexTelefono);
        }
    }

    private boolean compruebaFechaEntrada(char tipo) {
        Date hoy = new Date();

        if (tipo == 'N') {

            Date fechaEntrada = vNuevoMonitor.jDateEntrada.getDate();
            return fechaEntrada.before(hoy);
        } else {
            return vActualizaMonitor.jDateEntrada.getDate().before(hoy);
        }

    }

    private boolean compruebaVacio(char tipo) {
        if (tipo == 'N') {
            return !(vNuevoMonitor.jTextCodigo.getText().isBlank() || vNuevoMonitor.jTextCorreo.getText().isBlank()
                    || vNuevoMonitor.jTextDNI.getText().isBlank() || vNuevoMonitor.jTextNombre.getText().isBlank()
                    || vNuevoMonitor.jTextTelefono.getText().isBlank() || vNuevoMonitor.jDateEntrada.getDate() == null);
        } else {
            return !(vActualizaMonitor.jTextCodigo.getText().isBlank() || vActualizaMonitor.jTextCorreo.getText().isBlank()
                    || vActualizaMonitor.jTextDNI.getText().isBlank() || vActualizaMonitor.jTextNombre.getText().isBlank()
                    || vActualizaMonitor.jTextTelefono.getText().isBlank() || vActualizaMonitor.jDateEntrada.getDate() == null);
        }

    }

}
