/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Vista.VistaGestionSocios;
import java.util.ArrayList;
import java.util.Set;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 34667
 */
public class UtilTablasSocio {
    
    VistaGestionSocios vSocio;
    DefaultTableModel tabla = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column){
            return false;
        }
    };
    
    public UtilTablasSocio(VistaGestionSocios vSocio){
        this.vSocio = vSocio;
        vSocio.jTableSocios.setModel(tabla);
    }
    
    public void dibujaTablaSocioes(VistaGestionSocios vSocio){
        String[] columnasTablas = {"Código", "Nombre", "DNI", "Fecha Nacimiento", "Teléfono", "Correo", "Fecha Alta", "Categoría"};
        tabla.setColumnIdentifiers(columnasTablas);
        
        vSocio.jTableSocios.getTableHeader().setResizingAllowed(false);
        vSocio.jTableSocios.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        
        vSocio.jTableSocios.getColumnModel().getColumn(0).setPreferredWidth(20);
        vSocio.jTableSocios.getColumnModel().getColumn(1).setPreferredWidth(150);
        vSocio.jTableSocios.getColumnModel().getColumn(2).setPreferredWidth(50);
        vSocio.jTableSocios.getColumnModel().getColumn(3).setPreferredWidth(90);
        vSocio.jTableSocios.getColumnModel().getColumn(4).setPreferredWidth(50);
        vSocio.jTableSocios.getColumnModel().getColumn(5).setPreferredWidth(200);
        vSocio.jTableSocios.getColumnModel().getColumn(6).setPreferredWidth(90);
        vSocio.jTableSocios.getColumnModel().getColumn(7).setPreferredWidth(30);
        
    }
    
    public void rellenarTablaSocioes(ArrayList<Socio> socios){
        Object[] fila = new Object[8];
        for (Socio socio : socios) {
            fila[0] = socio.getNumeroSocio();
            fila[1] = socio.getNombre();
            fila[2] = socio.getDni();
            fila[3] = socio.getFechaNacimiento();
            fila[4] = socio.getTelefono();
            fila[5] = socio.getCorreo();
            fila[6] = socio.getFechaEntrada();
            fila[7] = socio.getCategoria();
            tabla.addRow(fila);
        }
    }
    
    public void vaciarTablaSocioes(){
        while(tabla.getRowCount() > 0){
            tabla.removeRow(0);
        }
    }
}
