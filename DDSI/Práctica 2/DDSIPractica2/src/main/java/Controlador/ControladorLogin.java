/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Conexion;
import Vista.VistaMensajes;

/**
 *
 * @author 34667
 */
public class ControladorLogin {

    private Conexion conexion;
    private VistaMensajes vMensajes;

    public ControladorLogin() {
        vMensajes = new VistaMensajes();
        conectarBD();
        desconectarBD();
    }

    private Conexion conectarBD() {
        try {
            conexion = new Conexion("mariadb", "172.18.1.241", "mariadb", "DDSI_057", "DDSI_057");
            vMensajes.mensajeConsola("Conexion correcta con mariaDB");
        } catch (Exception ex) {
            vMensajes.mensajeConsola("Error en conectarBD: "+ex.getMessage());
        }
        return conexion;
    }

    private void desconectarBD() {
        try {
            conexion.desconectar();
            vMensajes.mensajeConsola("Desconexion correcta");
        } catch (Exception ex) {
            vMensajes.mensajeConsola("Error al desconectar: "+ex.getMessage());
        }

    }
}
