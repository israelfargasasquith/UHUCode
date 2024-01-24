/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.ParDePuntos;
import Vista.VistaCompararAlgoritmos;
import Vista.VistaCompararDos;
import Vista.VistaCompararTodos;
import Vista.VistaCompararVoraces;
import Vista.VistaCompararVoracesFichero;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author marin
 */
public class ControladorTablas {

    public DefaultTableModel ModeloComparar2 = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public void dibujarTabla2(VistaCompararDos vDos) {
        vDos.tabla2.setModel(ModeloComparar2);
        String[] columnasTabla = {"TALLA", vDos.Comp2Combo1.getSelectedItem().toString(), vDos.Comp2Combo2.getSelectedItem().toString()};
        ModeloComparar2.setColumnIdentifiers(columnasTabla);

        //Para no permitir el redimensionamiento de las columnas con el ratón
        vDos.tabla2.getTableHeader().setResizingAllowed(false);
        vDos.tabla2.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

        vDos.tabla2.getColumnModel().getColumn(0).setPreferredWidth(40);
        vDos.tabla2.getColumnModel().getColumn(1).setPreferredWidth(100);
        //vDos.tabla2.getColumnModel().getColumn(2).setPreferredWidth(100);

    }

    public void rellenarTablaComparar2(ArrayList<ArrayList<Double>> tiempos) {

        int numRegistros = tiempos.size();
        for (int i = 0; i < numRegistros; i++) {
            Object[] fila = new Object[4];
            fila[0] = 100 * (i + 1);
            fila[1] = tiempos.get(i).get(0);
            fila[2] = tiempos.get(i).get(1);

            ModeloComparar2.addRow(fila);
        }
    }

    public void vaciarTablaComparar2() {
        while (ModeloComparar2.getRowCount() > 0) {
            ModeloComparar2.removeRow(0);
        }
    }

    public DefaultTableModel ModeloCompararTodos = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public void dibujarTablaTodosTallas(VistaCompararTodos vTodos) {
        vTodos.TablaTodos.setModel(ModeloCompararTodos);
        String[] columnasTabla = {"TALLA", "Exhaustivo", "DyV", "DyVMejorado", "Exhaustivo con Poda"};
        ModeloCompararTodos.setColumnIdentifiers(columnasTabla);

        //Para no permitir el redimensionamiento de las columnas con el ratón
        vTodos.TablaTodos.getTableHeader().setResizingAllowed(false);
        vTodos.TablaTodos.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

        vTodos.TablaTodos.getColumnModel().getColumn(0).setPreferredWidth(40);
        vTodos.TablaTodos.getColumnModel().getColumn(1).setPreferredWidth(100);
        vTodos.TablaTodos.getColumnModel().getColumn(2).setPreferredWidth(100);
        vTodos.TablaTodos.getColumnModel().getColumn(3).setPreferredWidth(100);
        vTodos.TablaTodos.getColumnModel().getColumn(4).setPreferredWidth(100);
        //vDos.tabla2.getColumnModel().getColumn(2).setPreferredWidth(100);

    }

    public void rellenarTablaCompararTodosTallas(ArrayList<ArrayList<Double>> tiempos) {

        int numRegistros = tiempos.size();
        for (int i = 0; i < numRegistros; i++) {
            Object[] fila = new Object[5];
            fila[0] = 100 * (i + 1);
            fila[1] = tiempos.get(i).get(0);
            fila[2] = tiempos.get(i).get(1);
            fila[3] = tiempos.get(i).get(2);
            fila[4] = tiempos.get(i).get(3);

            ModeloCompararTodos.addRow(fila);
        }
    }

    public void vaciarTablaCompararTodos() {
        while (ModeloCompararTodos.getRowCount() > 0) {
            ModeloCompararTodos.removeRow(0);
        }
    }

    public DefaultTableModel ModeloCompararAlgoritmos = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public void dibujarTablaCompararAlgoritmos(VistaCompararAlgoritmos vAleatorios) {
        vAleatorios.TablaComprobarAleatorios.setModel(ModeloCompararAlgoritmos);
        String[] columnasTabla = {"Algoritmo", "Punto 1", "Punto 2", "Distancia", "Tiempo"};
        ModeloCompararAlgoritmos.setColumnIdentifiers(columnasTabla);

        //Para no permitir el redimensionamiento de las columnas con el ratón
        vAleatorios.TablaComprobarAleatorios.getTableHeader().setResizingAllowed(false);
        vAleatorios.TablaComprobarAleatorios.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

        vAleatorios.TablaComprobarAleatorios.getColumnModel().getColumn(0).setPreferredWidth(100);
        vAleatorios.TablaComprobarAleatorios.getColumnModel().getColumn(1).setPreferredWidth(100);
        vAleatorios.TablaComprobarAleatorios.getColumnModel().getColumn(2).setPreferredWidth(100);
        vAleatorios.TablaComprobarAleatorios.getColumnModel().getColumn(3).setPreferredWidth(100);
        vAleatorios.TablaComprobarAleatorios.getColumnModel().getColumn(4).setPreferredWidth(100);

        //vDos.tabla2.getColumnModel().getColumn(2).setPreferredWidth(100);
    }

    public void rellenarTablaCompararAlgoritmos(ArrayList<Double> tiempos, ParDePuntos p, Double distancia) {
        String[] algoritmos = {"Exhaustivo", "DyV", "DyVMejorado", "Exhaustivo con Poda"};
        int numRegistros = tiempos.size();
        for (int i = 0; i < numRegistros; i++) {
            Object[] fila = new Object[5];

            fila[0] = algoritmos[i];
            fila[1] = p.getA().getX() + " , " + p.getA().getY();
            fila[2] = p.getB().getX() + " , " + p.getB().getY();
            fila[3] = distancia;
            fila[4] = tiempos.get(i);
            ModeloCompararAlgoritmos.addRow(fila);
        }

    }

    public void vaciarTablaCompararAlgoritmos() {
        while (ModeloCompararAlgoritmos.getRowCount() > 0) {
            ModeloCompararAlgoritmos.removeRow(0);
        }
    }

    public DefaultTableModel ModeloCompararVoraces = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public void dibujarTablaCompararVoraces(VistaCompararVoraces vVoraces) {
        vVoraces.jTableVoraces.setModel(ModeloCompararVoraces);
        String[] columnasTabla = {"TALLA", "UNIDIRECCIONAL", "BIDIRECCIONAL"};
        ModeloCompararVoraces.setColumnIdentifiers(columnasTabla);

        //Para no permitir el redimensionamiento de las columnas con el ratón
        vVoraces.jTableVoraces.getTableHeader().setResizingAllowed(false);
        vVoraces.jTableVoraces.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

        vVoraces.jTableVoraces.getColumnModel().getColumn(0).setPreferredWidth(100);
        vVoraces.jTableVoraces.getColumnModel().getColumn(1).setPreferredWidth(200);
        vVoraces.jTableVoraces.getColumnModel().getColumn(2).setPreferredWidth(200);

    }

    public void rellenarTablaCompararVoraces(ArrayList<ArrayList<Double>> dist) {
        int numRegistros = dist.size();
        for (int i = 0; i < numRegistros; i++) {
            Object[] fila = new Object[5];

            fila[0] = 100 * (i + 1);
            fila[1] = dist.get(i).get(0);
            fila[2] = dist.get(i).get(1);

            ModeloCompararVoraces.addRow(fila);
        }

    }

    public void vaciarTablaCompararVoraces() {
        while (ModeloCompararVoraces.getRowCount() > 0) {
            ModeloCompararVoraces.removeRow(0);
        }
    }

    public DefaultTableModel ModeloCompararVoracesFichero = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public void dibujarTablaCompararVoracesFichero(VistaCompararVoracesFichero vVoraces) {
        vVoraces.jTableCompararVoracesFichero.setModel(ModeloCompararVoracesFichero);
        String[] columnasTabla = {"UNIDIRECCIONAL", "BIDIRECCIONAL"};
        ModeloCompararVoracesFichero.setColumnIdentifiers(columnasTabla);

        //Para no permitir el redimensionamiento de las columnas con el ratón
        vVoraces.jTableCompararVoracesFichero.getTableHeader().setResizingAllowed(false);
        vVoraces.jTableCompararVoracesFichero.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

        vVoraces.jTableCompararVoracesFichero.getColumnModel().getColumn(0).setPreferredWidth(200);
        vVoraces.jTableCompararVoracesFichero.getColumnModel().getColumn(1).setPreferredWidth(200);
        vVoraces.jTableCompararVoracesFichero.setPreferredScrollableViewportSize(vVoraces.jTableCompararVoracesFichero.getPreferredSize());
        vVoraces.jTableCompararVoracesFichero.setPreferredScrollableViewportSize(vVoraces.jTableCompararVoracesFichero.getPreferredSize());
    }

    public void rellenarTablaCompararVoracesFichero(Double costeBi, Double costeUni, VistaCompararVoracesFichero vVoraces) {

        Object[] fila = new Object[2];
        fila[0] = costeBi;
        fila[1] = costeUni;
        
        ModeloCompararVoracesFichero.addRow(fila);
        vVoraces.jTableCompararVoracesFichero.setPreferredScrollableViewportSize(vVoraces.jTableCompararVoracesFichero.getPreferredSize());

    }

    public void vaciarTablaCompararVoracesFichero() {
        while (ModeloCompararVoracesFichero.getRowCount() > 0) {
            ModeloCompararVoracesFichero.removeRow(0);
        }
    }

}
