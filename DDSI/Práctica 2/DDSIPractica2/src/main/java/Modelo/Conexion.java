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
            Connection conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@172.17.20.39:1521:etsi", usuario, password);
        } else if (servicioBD.equalsIgnoreCase("MariaDB")) {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mariadb://172.18.1.241:3306/"+usuario, usuario, password);
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
