/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author 34667
 */
public class Conexion {

    private Connection conexion = null;
    private String servicioBD;

    public Conexion(String sgbd, String ip, String servicioBD, String usuario, String password) throws Exception {
        this.servicioBD = servicioBD;
        if (servicioBD.equalsIgnoreCase("Oracle")) {
            conexion = DriverManager.getConnection(
                    "jdbc:"+sgbd+":thin:@"+ip+":1521:"+servicioBD+"", usuario, password);
        } else if (servicioBD.equalsIgnoreCase("MariaDB")) {
            conexion = DriverManager.getConnection(
                    "jdbc:"+sgbd+"://172.18.1.241:3306/" + usuario, usuario, password);
        } else {
            throw new Exception("Error, servicio de BD desconocido");
        }
    }

    public Connection getConexion() {
        return conexion;
    }

    public void desconectar() throws SQLException {
        if (conexion != null) {
            conexion.close();
        }
    }
}
