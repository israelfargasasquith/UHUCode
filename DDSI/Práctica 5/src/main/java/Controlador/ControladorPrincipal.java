/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Vista.VistaGestionMonitores;
import Vista.VistaGestionSocios;
import Vista.VistaMensajes;
import Vista.VistaPrincipal;
import Vista.VistaVacia;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

    private ControladorMonitor cMonitor;
    private ControladorSocio cSocio;
    private ControladorActividades cActividades;

    private VistaPrincipal vistaPrincipal;
    private VistaGestionMonitores vMonitores;
    private VistaGestionSocios vSocios;
    private VistaVacia vVacia;
    private VistaMensajes vMensajes;

    private String monitorSeleccionado;
    private String socioSelecionado;

    public ControladorPrincipal(SessionFactory sesionFactory, VistaMensajes vMensajes) {

        this.monitorSeleccionado = "";
        this.socioSelecionado = "";

        this.sesionFactory = sesionFactory;

        this.vistaPrincipal = new VistaPrincipal();
        this.vMonitores = new VistaGestionMonitores();
        this.vSocios = new VistaGestionSocios();
        this.vVacia = new VistaVacia();
        this.vMensajes = vMensajes;

        addActionListeners();

        vistaPrincipal.getContentPane().setLayout(new CardLayout());
        vistaPrincipal.add(vVacia);
        vistaPrincipal.add(vMonitores);
        vistaPrincipal.add(vSocios);

        //Solo trabajo con una instancia de cada clase controlador y VistaMensajes, para reducir la carga de trabajo del garbajeCollector y otros
        //Daemons de cambio de contexto de cada instncia de objeto. Como los botones de Gestion de Socios/Monitores se encuentran en la vista
        //principal (Insertar, Actualizar, Borrar) los listeners deben estar en el Controlador Principal.
        this.cMonitor = new ControladorMonitor(sesionFactory, vMonitores,vMensajes); 
        this.cSocio = new ControladorSocio(sesionFactory, vSocios,vMensajes);
        this.cActividades = new ControladorActividades(sesionFactory,vMensajes);

        vVacia.setVisible(true);
        vVacia.setSize(900, 600);
        vMonitores.setVisible(false);
        vMonitores.setSize(900, 600);
        vSocios.setVisible(false);
        vSocios.setSize(900, 600);

        vistaPrincipal.setSize(1000, 700);
        vistaPrincipal.setResizable(false);

        vistaPrincipal.setVisible(true);
        vistaPrincipal.setLocationRelativeTo(null);
        vistaPrincipal.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case "Gestión de Monitores":
                socioSelecionado = "";
                monitorSeleccionado = "";
                vVacia.setVisible(false);
                vSocios.setVisible(false);
                vMonitores.setVisible(true);

                break;
                
            //El metodo cMonitor.nuevoMonitor(); es un metodo "dummy" para poder organizar mejor las llamadas entre los controladores, toda
            //la logica se desarrolla y organiza en el controlador propio, pero para que comience a trabajar necesitamos este tipo de llamadas
            //que no suponen un gran coste, solo un pequeño aumento en el acomplamiento
            case "Nuevo Monitor":
                cMonitor.nuevoMonitor();
                monitorSeleccionado = ""; 
                break;

            case "Actualiza Monitor":
                if (!monitorSeleccionado.equalsIgnoreCase("")) {
                    cMonitor.actualizaMonitor(monitorSeleccionado);
                    monitorSeleccionado = "";
                } else {
                    vMensajes.muestraMensaje(vistaPrincipal, "Error no hay monitor seleccionado", VistaMensajes.enumMensajes.ERROR);
                    //System.out.println("Error no hay monitor seleccionado");
                }
                break;

            case "Baja Monitor":
                if (!monitorSeleccionado.equalsIgnoreCase("")) {
                    cMonitor.bajaMonitor(monitorSeleccionado);
                    monitorSeleccionado = "";
                } else {
                    vMensajes.muestraMensaje(vistaPrincipal, "Error no hay monitor seleccionado", VistaMensajes.enumMensajes.ERROR);
                    //System.out.println("Error no hay monitor seleccionado");
                }
                break;

            case "Gestión de Socios":
                monitorSeleccionado = "";
                socioSelecionado = "";
                vVacia.setVisible(false);
                vMonitores.setVisible(false);
                vSocios.setVisible(true);

                break;

            case "Nuevo Socio":
                cSocio.nuevoSocio();
                socioSelecionado = "";
                break;

            case "Actualiza Socio":
                if (!socioSelecionado.equalsIgnoreCase("")) {
                    cSocio.actualizaSocio(socioSelecionado);
                    socioSelecionado = "";
                } else {
                    vMensajes.muestraMensaje(vistaPrincipal, "Error no hay socio seleccionado", VistaMensajes.enumMensajes.ERROR);
                    //System.out.println("Error no hay socio seleccionado");
                }
                break;

            case "Baja Socio":
                if (!socioSelecionado.equalsIgnoreCase("")) {
                    cSocio.bajaSocio(socioSelecionado);
                    socioSelecionado = "";
                } else {
                    vMensajes.muestraMensaje(vistaPrincipal, "Error no hay socio seleccionado", VistaMensajes.enumMensajes.ERROR);
                    //System.out.println("Error no hay socio seleccionado");
                }
                break;

            case "Cambio Criterio":
                cSocio.cambioCriterio((String) vSocios.jComboCriterioCategoria.getSelectedItem());
                socioSelecionado="";
                break;
            case "GestionaAltas":
                cActividades.gestionarAltas();
                break;

            case "Salir de la aplicación":
                vistaPrincipal.dispose();
                System.exit(0);
                break;
            default:
                System.out.println("Te has equivocado al poner el nombre de la action command");
        }
    }

    private void seleccionMonitor(MouseEvent e) {
        if (vMonitores.jTableMonitores.getSelectedRow() != -1) {
            monitorSeleccionado = (String) vMonitores.jTableMonitores.getValueAt(vMonitores.jTableMonitores.getSelectedRow(), 0);
            //System.out.println("Codigo monitor seleccionado: " + monitorSeleccionado);
        }
    }

    private void seleccionSocio(MouseEvent e) {
        if (vSocios.jTableSocios.getSelectedRow() != -1) {
            socioSelecionado = (String) vSocios.jTableSocios.getValueAt(vSocios.jTableSocios.getSelectedRow(), 0);
            //System.out.println("Codigo socio seleccionado: " + socioSelecionado);
        }
    }

    private void addActionListeners() {
        vistaPrincipal.jMenuItemMonitores.addActionListener(this);
        vistaPrincipal.jMenuItemActividades.addActionListener(this);
        vistaPrincipal.jMenuItemSalir.addActionListener(this);
        vistaPrincipal.jMenuItemSocios.addActionListener(this);
        vistaPrincipal.jMenuHome.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                socioSelecionado = "";
                monitorSeleccionado = "";
                vVacia.setVisible(true);
                vSocios.setVisible(false);
                vMonitores.setVisible(false);
            }
        });

        vMonitores.jButtonNuevoMonitor.addActionListener(this);
        vMonitores.jButtonActualizaMonitor.addActionListener(this);
        vMonitores.jButtonBajaMonitor.addActionListener(this);

        vSocios.jButtonActualizaSocio.addActionListener(this);
        vSocios.jButtonBajaSocio.addActionListener(this);
        vSocios.jButtonNuevoSocio.addActionListener(this);
        vSocios.jComboCriterioCategoria.addActionListener(this);

        vMonitores.jTableMonitores.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                seleccionMonitor(e);
            }

        });

        vSocios.jTableSocios.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                seleccionSocio(e);
            }
        });

    }
}
