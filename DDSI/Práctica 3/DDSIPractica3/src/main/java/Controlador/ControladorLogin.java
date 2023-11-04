/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Config.HibernateUtilMariaDB;
import Config.HibernateUtilOracle;
import Vista.VistaLogin;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;

/**
 *
 * @author 34667
 */
public class ControladorLogin {

    private SessionFactory sesion;
    private VistaLogin vLogin;
    private String servidor;

    public ControladorLogin() {
        solicitaServidor();
        conectaBD();
        ControladorPrincipal controladorP = new ControladorPrincipal(sesion);
    }
    
    public void solicitaServidor(){
        servidor = vLogin.mensajeBienvenida();
    }

    private void conectaBD() {
        try {
            if (servidor.equalsIgnoreCase("Oracle")) {
                sesion = HibernateUtilOracle.getSessionFactory();
            } else if (servidor.equalsIgnoreCase("MariaDB")) {
                sesion = HibernateUtilMariaDB.getSessionFactory();
            }
            vLogin = new VistaLogin();
            vLogin.mensajeConsola("Conexion correcta con Hibernate al servidor " + servidor);
        } catch (ExceptionInInitializerError ex) {
            vLogin.mensajeConsola("Error en la conexion, revise el fichero .cfg.xml: " + ex.getMessage());
        }
    }
}
