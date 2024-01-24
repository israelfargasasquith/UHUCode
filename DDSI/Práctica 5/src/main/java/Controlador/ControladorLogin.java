/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Config.HibernateUtilMariaDB;
import Config.HibernateUtilOracle;
import Vista.VistaLogin;
import Vista.VistaMensajes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.hibernate.SessionFactory;

/**
 *
 * @author 34667
 */
public class ControladorLogin implements ActionListener {
    
    private SessionFactory sesion;
    private VistaMensajes vMensajes;
    private VistaLogin vLogin;
    private String servidor;
    
    public ControladorLogin() throws InterruptedException {
        vLogin = new VistaLogin();
        vMensajes = new VistaMensajes();
        vLogin.setVisible(true);
        vLogin.setLocationRelativeTo(null);
        vLogin.setResizable(false);
        addListeners();
    }
   
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            
            case "Conectar":
               try {
                servidor = (String) vLogin.jComboServidores.getSelectedItem();
                
                if (servidor.equalsIgnoreCase("MariaDB")) {
                    sesion = HibernateUtilMariaDB.getSessionFactory();
                } else if (servidor.equalsIgnoreCase("Oracle")) {
                    sesion = HibernateUtilOracle.getSessionFactory();
                }
                vMensajes.muestraMensaje(null, "Se ha conectado a la BD " + servidor, VistaMensajes.enumMensajes.INFORMACION);
                ControladorPrincipal controladorP = new ControladorPrincipal(sesion,vMensajes);
                
            } catch (ExceptionInInitializerError ex) {
                vMensajes.muestraMensaje(null, "Error al conectar con la BD", VistaMensajes.enumMensajes.ERROR);
                System.exit(1);
            }
            vLogin.dispose();
            
            break;
            
            case "Salir":
                System.exit(0);
                break;
            
            default:
        }
        
    }
     
    public void addListeners() {
        vLogin.jButtonConectar.addActionListener(this);
        vLogin.jButtonSalirLogin.addActionListener(this);
    }
    
}
