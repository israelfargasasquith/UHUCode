/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author israel
 */
public class SocioDAO {
    
    private Conexion conexion;
    private Statement query;
    private ResultSet result;
    
    public SocioDAO(Conexion conexion) {
        this.conexion = conexion;
    }

    public boolean estaSocio(String numSocio) {
        
        return false;
    }
    
    public ArrayList<String> listaSocios() throws SQLException {
        ArrayList<String> lSocios = new ArrayList<>();
        query = conexion.getConexion().createStatement();
        result = query.executeQuery("SELECT s.nombre, s.numeroSocio \n"
                + "FROM SOCIO s ");
        while(result.next()){
            lSocios.add(result.getString(1));
            lSocios.add(result.getString(2));
        }
        return lSocios;
    }
    
}
