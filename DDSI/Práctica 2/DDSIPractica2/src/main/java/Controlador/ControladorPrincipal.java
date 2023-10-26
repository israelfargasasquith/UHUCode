/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Conexion;
import Modelo.SocioDAO;
import Vista.VistaMenu;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author israel
 */
public class ControladorPrincipal {

    private Conexion conexion;
    private VistaMenu vMenu;
    private SocioDAO socioDAO;
    private String opc;

    public ControladorPrincipal(Conexion conexion) throws SQLException {
        this.conexion = conexion;
        vMenu = new VistaMenu();
        socioDAO = new SocioDAO(conexion);
        opc = vMenu.muestraOpciones();
        switch (opc) {
            case "1":
                ArrayList<String> lSocios = socioDAO.listaSocios();
                System.out.println("Los socios inscritos son: ");
                for (int i = 0; i < lSocios.size(); i+=2) {
                    System.out.println("Socio -> Nombre: "+lSocios.get(i)+" NumSocio: "+lSocios.get(i+1));
                } que pereza hacerlo por consola xd voy con la 3 que es hibernate...
                break;
            case "2":
                break;
            case "3":
                break;
            case "4":
                break;
            case "5":
                break;
            default:
                break;
        }
    }

}
