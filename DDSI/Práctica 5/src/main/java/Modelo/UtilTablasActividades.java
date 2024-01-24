/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Vista.VistaAltas;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 34667
 */
public class UtilTablasActividades {

    private VistaAltas vAltas;
    DefaultTableModel tabla = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public UtilTablasActividades(VistaAltas vAltas) {
        this.vAltas = vAltas;
        vAltas.jTableActividades.setModel(tabla);
    }

    public void dibujaTablaActividades(VistaAltas vAltas) {
        String[] columnasTablas = {"Código", "Nombre", "¿Inscrito?"};
        tabla.setColumnIdentifiers(columnasTablas);

        vAltas.jTableActividades.getTableHeader().setResizingAllowed(false);
        vAltas.jTableActividades.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

        vAltas.jTableActividades.getColumnModel().getColumn(0).setPreferredWidth(40);
        vAltas.jTableActividades.getColumnModel().getColumn(1).setPreferredWidth(140);
        vAltas.jTableActividades.getColumnModel().getColumn(2).setPreferredWidth(70);
    }

    public void rellenarTablaAltas(ArrayList<Actividad> actividades, ArrayList<Actividad> lActividadesSocio) {
        Object[] fila = new Object[3];
//        System.out.println("**********************************\nTodas las actividades ofrecidas");
//        for (Actividad ac : actividades) {
//            System.out.println(ac);
//        }
//        System.out.println("*********************\nTodas las actividades del socio 1");
        if (!lActividadesSocio.isEmpty()) {
//            for (Actividad ac : lActividadesSocio) {
//                System.out.println(ac);
//            }
            for (Actividad a : actividades) {
                fila[0] = a.getIdActividad();
                fila[1] = a.getNombre();
                if (lActividadesSocio.contains(a)) {
                    fila[2] = "✅";//"SI";
                } else {
                    fila[2] = "❌";//"NO";
                }
//                System.out.println("Fila : idAc-> " + fila[0] + " nombre-> " + fila[1] + " suscrito-> " + fila[2]);
                tabla.addRow(fila);
            }
        } else {
            for (Actividad a : actividades) {
                fila[0] = a.getIdActividad();
                fila[1] = a.getNombre();
                fila[2] = "❌";//"NO";
//                System.out.println("Fila : idAc-> " + fila[0] + " nombre-> " + fila[1] + " suscrito-> " + fila[2]);
                tabla.addRow(fila);
            }
        }
    }

    public void vaciarTablaAltas() {
        while (tabla.getRowCount() > 0) {
            tabla.removeRow(0);
        }
    }
}
