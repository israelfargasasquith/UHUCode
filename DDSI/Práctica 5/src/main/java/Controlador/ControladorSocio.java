/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Socio;
import Modelo.SocioDAO;
import Modelo.UtilTablasSocio;
import Vista.VistaActualizaSocio;
import Vista.VistaGestionSocios;
import Vista.VistaMensajes;
import Vista.VistaNuevoSocio;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author 34667
 */
public class ControladorSocio implements ActionListener {

    private VistaNuevoSocio vNuevoSocio;
    private VistaActualizaSocio vActualizaSocio;

    private SocioDAO socioDAO;
    private SessionFactory sesionFactory;
    private VistaMensajes vMensajes;
    private UtilTablasSocio tablasSocio;
    private SimpleDateFormat formatoFecha;
    private String regexTelefono;
    private String regexDNI;
    private String regexCorreo;
    private String criterioTabla;

    public ControladorSocio(SessionFactory sesionFactory, VistaGestionSocios vSocios, VistaMensajes vMensajes) {
        this.criterioTabla = "Sin Criterio";
        this.vNuevoSocio = new VistaNuevoSocio();
        this.vActualizaSocio = new VistaActualizaSocio();
        this.socioDAO = new SocioDAO();
        this.sesionFactory = sesionFactory;
        this.vMensajes = vMensajes;
        this.tablasSocio = new UtilTablasSocio(vSocios);
        this.formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        this.regexTelefono = "\\d{9}";
        this.regexDNI = "\\d{8}[A-Z && [^IOÑ]]";
        this.regexCorreo = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

        vNuevoSocio.setVisible(false);
        vNuevoSocio.setResizable(false);
        vNuevoSocio.setLocationRelativeTo(null);

        vActualizaSocio.setVisible(false);
        vActualizaSocio.setResizable(false);
        vActualizaSocio.setLocationRelativeTo(null);

        tablasSocio.dibujaTablaSocioes(vSocios);
        rellenarTablaSocios(sesionFactory.openSession());

        addActionListeners();
    }

    //Estos metodos son los que llamamos desde el controlador principal
    public void nuevoSocio() {
        codigoMasAlto(sesionFactory.openSession());
    }

    public void actualizaSocio(String numeroSocio) {
        rellenaVentanaConCodigo(numeroSocio, sesionFactory.openSession());
    }

    public void bajaSocio(String numeroSocio) {
        if (JOptionPane.YES_OPTION == vMensajes.muestraMensaje(null, "¿Quiere eliminar al socio " + numeroSocio + " de la BD?", VistaMensajes.enumMensajes.SI_O_NO)) {
            bajaSocio(numeroSocio, sesionFactory.openSession());
            rellenarTablaSocios(sesionFactory.openSession());

        }
    }

    public void cambioCriterio(String criterio) {
        this.criterioTabla = criterio;
        rellenarTablaSocios(sesionFactory.openSession());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {

            case "InsertarNuevoSocio":
                Socio nuevo = null;

                nuevo = comprobarTextNuevoSocio();

                nuevoSocio(sesionFactory.openSession(), nuevo);

                rellenarTablaSocios(sesionFactory.openSession());
                vaciaVentanaNuevoSocio();
                vNuevoSocio.dispose();

                break;
            case "CancelarNuevoSocio":

                vaciaVentanaNuevoSocio();
                vNuevoSocio.dispose();

                break;

            case "ActualizaSocio":
                Socio actualizado = null;

                actualizado = comprobarTextActualiza();

                actualizaSocio(sesionFactory.openSession(), actualizado);

                rellenarTablaSocios(sesionFactory.openSession());
                vaciarVentanaActualiza();
                vActualizaSocio.dispose();
                break;
            case "CancelarActualizaSocio":
                vaciarVentanaActualiza();
                vActualizaSocio.dispose();
                break;

        }

    }

    private Socio comprobarTextNuevoSocio() {
        Socio nuevo = null;
        if (!compruebaVacio('N')) {

            vMensajes.muestraMensaje(vNuevoSocio, "Error: algun campo esta vacio", VistaMensajes.enumMensajes.ERROR);

        } else if (!compuebaDNI('N')) {
            vMensajes.muestraMensaje(vNuevoSocio, "Error: el DNI no es valido", VistaMensajes.enumMensajes.ERROR);

        } else if (!compurebaCorreo('N')) {
            vMensajes.muestraMensaje(vNuevoSocio, "Error: el correo no es valido", VistaMensajes.enumMensajes.ERROR);

        } else if (!compruebaTelefono('N')) {
            vMensajes.muestraMensaje(vNuevoSocio, "Error: el telefono no es valido", VistaMensajes.enumMensajes.ERROR);

        } else if (!compruebaFechaEntrada('N')) {
            vMensajes.muestraMensaje(vNuevoSocio, "Error: la fecha de entrada no es valida", VistaMensajes.enumMensajes.ERROR);

        } else if (!compruebaFechaNacimiento('N')) {
            vMensajes.muestraMensaje(vNuevoSocio, "Error: la fecha de nacimiento no es valida", VistaMensajes.enumMensajes.ERROR);

        } else {
            nuevo = new Socio(vNuevoSocio.jTextCodigo.getText(), vNuevoSocio.jTextNombre.getText(),
                    vNuevoSocio.jTextDNI.getText(), formatoFecha.format(vNuevoSocio.jDateNacimiento.getDate()),
                    vNuevoSocio.jTextTelefono.getText(), vNuevoSocio.jTextCorreo.getText(),
                    formatoFecha.format(vNuevoSocio.jDateEntrada.getDate()), vNuevoSocio.jComboCategoria.getSelectedItem().toString().charAt(0));
        }
        return nuevo;
    }

    private Socio comprobarTextActualiza() {
        Socio nuevo = null;
        if (!compruebaVacio('A')) {

            vMensajes.muestraMensaje(vNuevoSocio, "Error: algun campo esta vacio", VistaMensajes.enumMensajes.ERROR);

        } else if (!compuebaDNI('A')) {
            vMensajes.muestraMensaje(vNuevoSocio, "Error: el DNI no es valido", VistaMensajes.enumMensajes.ERROR);

        } else if (!compurebaCorreo('A')) {
            vMensajes.muestraMensaje(vNuevoSocio, "Error: el correo no es valido", VistaMensajes.enumMensajes.ERROR);

        } else if (!compruebaTelefono('A')) {
            vMensajes.muestraMensaje(vNuevoSocio, "Error: el telefono no es valido", VistaMensajes.enumMensajes.ERROR);

        } else if (!compruebaFechaEntrada('A')) {
            vMensajes.muestraMensaje(vNuevoSocio, "Error: la fecha de entrada no es valida", VistaMensajes.enumMensajes.ERROR);

        } else if (!compruebaFechaNacimiento('A')) {
            vMensajes.muestraMensaje(vNuevoSocio, "Error: la fecha de nacimiento no es valida", VistaMensajes.enumMensajes.ERROR);

        } else {
            nuevo = new Socio(vActualizaSocio.jTextCodigo.getText(), vActualizaSocio.jTextNombre.getText(),
                    vActualizaSocio.jTextDNI.getText(), formatoFecha.format(vActualizaSocio.jDateNacimiento.getDate()),
                    vActualizaSocio.jTextTelefono.getText(), vActualizaSocio.jTextCorreo.getText(),
                    formatoFecha.format(vActualizaSocio.jDateEntrada.getDate()), (Character) vActualizaSocio.jComboCategoria.getSelectedItem().toString().charAt(0));
        }

        return nuevo;
    }

    private void rellenarTablaSocios(Session sesion) {
        Transaction transacion = sesion.beginTransaction();
        try {
            tablasSocio.vaciarTablaSocioes();
            tablasSocio.rellenarTablaSocioes(socioDAO.listaSocios(sesion,criterioTabla));
            transacion.commit();
        } catch (Exception e) {
            transacion.rollback();
            vMensajes.muestraMensaje(null, "Error en la transaccion rellenarTablaSocios", VistaMensajes.enumMensajes.ERROR);
            //System.out.println("Error con la transaccion rellenaTablaSocios " + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }

    }

    private void bajaSocio(String numSocio, Session sesion) {
        Transaction transacion;
        transacion = sesion.beginTransaction();
        try {
            socioDAO.bajaSocio(sesion, numSocio);
            transacion.commit();
        } catch (Exception e) {
            transacion.rollback();
            vMensajes.muestraMensaje(null, "Error en la transaccion bajaSocio", VistaMensajes.enumMensajes.ERROR);
            //System.out.println("Error con la transaccion bajaSocio " + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }

    private void nuevoSocio(Session sesion, Socio nuevo) {
        Transaction transacion;
        transacion = sesion.beginTransaction();
        try {
            socioDAO.altaSocio(sesion, nuevo);
            transacion.commit();
        } catch (Exception e) {
            transacion.rollback();
            vMensajes.muestraMensaje(vNuevoSocio, "Error en la transaccion nuevoSocio", VistaMensajes.enumMensajes.ERROR);
            //System.out.println("Error con la transaccion nuevoSocio " + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }

    private void actualizaSocio(Session sesion, Socio actualiza) {
        Transaction transacion;
        transacion = sesion.beginTransaction();
        try {
            socioDAO.altaSocio(sesion, actualiza);
            transacion.commit();
        } catch (Exception e) {
            transacion.rollback();
            vMensajes.muestraMensaje(vActualizaSocio, "Error en la transaccion actualizaSocio", VistaMensajes.enumMensajes.ERROR);
            //System.out.println("Error con la transaccion actualizaSocio " + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }

    private void rellenaVentanaConCodigo(String numSocio, Session sesion) {
        Transaction transacion;
        transacion = sesion.beginTransaction();
        try {

            Socio tmp = socioDAO.socioPorNumero(sesion, numSocio);
            vActualizaSocio.jTextCodigo.setText(tmp.getNumeroSocio());
            vActualizaSocio.jTextCorreo.setText(tmp.getCorreo());
            vActualizaSocio.jTextDNI.setText(tmp.getDni());
            vActualizaSocio.jDateEntrada.setDate(formatoFecha.parse(tmp.getFechaEntrada()));
            vActualizaSocio.jDateNacimiento.setDate(formatoFecha.parse(tmp.getFechaNacimiento()));
            vActualizaSocio.jTextNombre.setText(tmp.getNombre());
            vActualizaSocio.jTextTelefono.setText(tmp.getTelefono());
            vActualizaSocio.jComboCategoria.setSelectedItem(String.valueOf(tmp.getCategoria()));
            vActualizaSocio.jTextCodigo.setEditable(false);
            vActualizaSocio.setLocationRelativeTo(null);
            vActualizaSocio.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
            vActualizaSocio.setVisible(true);
            transacion.commit();
        } catch (Exception e) {
            transacion.rollback();
            vMensajes.muestraMensaje(vActualizaSocio, "Error en la transaccion rellenaVentanaConCodigo", VistaMensajes.enumMensajes.ERROR);
            //System.out.println("Error con la transaccion rellena ventana numsocio " + e.getMessage());
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
            vNuevoSocio.jTextCodigo.setText(socioDAO.siguienteNum(sesion));
            vNuevoSocio.jTextCodigo.setEditable(false);
            vNuevoSocio.setLocationRelativeTo(null);
            vNuevoSocio.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
            vNuevoSocio.setVisible(true);
            transacion.commit();
        } catch (Exception e) {
            transacion.rollback();
            vMensajes.muestraMensaje(vNuevoSocio, "Error en la transaccion codigoMasAlto", VistaMensajes.enumMensajes.ERROR);
            //System.out.println("Error con la transaccion codigoMasAlto " + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }

    private void vaciaVentanaNuevoSocio() {
        vNuevoSocio.jTextCodigo.setText("");
        vNuevoSocio.jTextCodigo.setText("");
        vNuevoSocio.jTextCorreo.setText("");
        vNuevoSocio.jTextDNI.setText("");
        vNuevoSocio.jDateEntrada.setDate(null);
        vNuevoSocio.jDateNacimiento.setDate(null);
        vNuevoSocio.jTextNombre.setText("");
        vNuevoSocio.jTextTelefono.setText("");
    }

    private void vaciarVentanaActualiza() {
        vActualizaSocio.jTextCodigo.setText("");
        vActualizaSocio.jTextCodigo.setText("");
        vActualizaSocio.jTextCorreo.setText("");
        vActualizaSocio.jTextDNI.setText("");
        vActualizaSocio.jDateEntrada.setDate(null);
        vActualizaSocio.jDateNacimiento.setDate(null);
        vActualizaSocio.jTextNombre.setText("");
        vActualizaSocio.jTextTelefono.setText("");
    }

    private void addActionListeners() {
        vNuevoSocio.jButtonCancelar.addActionListener(this);
        vNuevoSocio.jButtonInsertar.addActionListener(this);

        vActualizaSocio.jButtonActualizar.addActionListener(this);
        vActualizaSocio.jButtonCancelar.addActionListener(this);
    }

    private boolean compuebaDNI(char tipo) {
        if (tipo == 'N') {
            String dni = vNuevoSocio.jTextDNI.getText();
            return dni.matches(regexDNI);
        } else {
            return vActualizaSocio.jTextDNI.getText().matches(regexDNI);
        }
    }

    private boolean compurebaCorreo(char tipo) {
        if (tipo == 'N') {
            String correo = vNuevoSocio.jTextCorreo.getText();
            return correo.matches(regexCorreo);
        } else {
            return vActualizaSocio.jTextCorreo.getText().matches(regexCorreo);
        }
    }

    private boolean compruebaTelefono(char tipo) {
        if (tipo == 'N') {
            String telefono = vNuevoSocio.jTextTelefono.getText();
            return telefono.matches(regexTelefono);
        } else {
            return vActualizaSocio.jTextTelefono.getText().matches(regexTelefono);
        }
    }

    private boolean compruebaFechaEntrada(char tipo) {
        Date hoy = new Date();

        if (tipo == 'N') {

            Date fechaEntrada = vNuevoSocio.jDateEntrada.getDate();
            return fechaEntrada.before(hoy);
        } else {
            return vActualizaSocio.jDateEntrada.getDate().before(hoy);
        }

    }

    private boolean compruebaFechaNacimiento(char tipo) {
        Date hoy = new Date();
        Date hace18Años;
        Calendar menor = Calendar.getInstance();
        menor.setTime(hoy);
        menor.add(Calendar.YEAR, -18);
        hace18Años = menor.getTime();

        if (tipo == 'N') {

            Date fechaNacimiento = vNuevoSocio.jDateNacimiento.getDate();
            return fechaNacimiento.before(hace18Años);

        } else {
            Date fechaNacimiento = vActualizaSocio.jDateNacimiento.getDate();
            return fechaNacimiento.before(hace18Años);
        }

    }

    private boolean compruebaVacio(char tipo) {
        if (tipo == 'N') {
            return !(vNuevoSocio.jTextCodigo.getText().isBlank() || vNuevoSocio.jTextCorreo.getText().isBlank()
                    || vNuevoSocio.jTextDNI.getText().isBlank() || (vNuevoSocio.jDateNacimiento.getDate() == null)
                    || vNuevoSocio.jTextNombre.getText().isBlank() || vNuevoSocio.jTextTelefono.getText().isBlank()
                    || vNuevoSocio.jDateEntrada.getDate() == null);
        } else {
            return !(vActualizaSocio.jTextCodigo.getText().isBlank() || vActualizaSocio.jTextCorreo.getText().isBlank()
                    || vActualizaSocio.jTextDNI.getText().isBlank() || (vActualizaSocio.jDateNacimiento.getDate() == null)
                    || vActualizaSocio.jTextNombre.getText().isBlank() || vActualizaSocio.jTextTelefono.getText().isBlank()
                    || vActualizaSocio.jDateEntrada.getDate() == null);
        }

    }

}
