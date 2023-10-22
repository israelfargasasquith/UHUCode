/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vistas;

import Controlador.ControlaPuntos;
import Modelo.Punto;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.xml.catalog.CatalogManager;

/**
 *
 * @author 34667
 */
public class VistaPrincipal extends javax.swing.JFrame {

    private ControlaPuntos cp;
    private Graphics gp;

    public VistaPrincipal(ControlaPuntos cp) {
        this.cp = cp;
        initComponents();
        gp = jPanelPuntos.getGraphics(); //puede dar errores a lo mejor al hacer casting?
        jPanelPuntos.paintComponents(gp);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelPuntos = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        buttonGenerarPuntos = new javax.swing.JButton();
        buttonSalir = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        textNumPuntos = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        buttonAbrirMenuFicheros = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaMensajes = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        buttonGenerarFichero = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        buttonDibuja = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(970, 650));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelPuntos.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPuntos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        javax.swing.GroupLayout jPanelPuntosLayout = new javax.swing.GroupLayout(jPanelPuntos);
        jPanelPuntos.setLayout(jPanelPuntosLayout);
        jPanelPuntosLayout.setHorizontalGroup(
            jPanelPuntosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 546, Short.MAX_VALUE)
        );
        jPanelPuntosLayout.setVerticalGroup(
            jPanelPuntosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 376, Short.MAX_VALUE)
        );

        getContentPane().add(jPanelPuntos, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 550, 380));

        jLabel1.setText("Indica el número de puntos aleatorios ");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        buttonGenerarPuntos.setText("Generar");
        buttonGenerarPuntos.setActionCommand("GenerarPuntos");
        buttonGenerarPuntos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGenerarPuntosActionPerformed(evt);
            }
        });
        getContentPane().add(buttonGenerarPuntos, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        buttonSalir.setText("Salir");
        buttonSalir.setActionCommand("SalirPrincipal");
        buttonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSalirActionPerformed(evt);
            }
        });
        getContentPane().add(buttonSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 520, -1, -1));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 230, 10));
        getContentPane().add(textNumPuntos, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 160, -1));

        jLabel3.setText("Generar desde fichero");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, -1));

        buttonAbrirMenuFicheros.setText("Abrir");
        buttonAbrirMenuFicheros.setActionCommand("GenerarFichero");
        buttonAbrirMenuFicheros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAbrirMenuFicherosActionPerformed(evt);
            }
        });
        getContentPane().add(buttonAbrirMenuFicheros, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, -1, -1));
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 230, 10));

        textAreaMensajes.setColumns(20);
        textAreaMensajes.setRows(5);
        jScrollPane1.setViewportView(textAreaMensajes);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 390, 530, 150));

        jLabel2.setText("Mensajes:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 400, -1, -1));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 230, 10));

        jLabel4.setText("Generar fichero con valores aleatorios");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, -1, -1));

        jLabel5.setText("Dibujar gráficas");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, -1, -1));

        buttonGenerarFichero.setText("Generar");
        buttonGenerarFichero.setActionCommand("GenerarFichero");
        buttonGenerarFichero.setHideActionText(true);
        getContentPane().add(buttonGenerarFichero, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, -1, -1));
        getContentPane().add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 230, 10));

        buttonDibuja.setText("Dibuja");
        buttonDibuja.setActionCommand("DibujaGraphics");
        getContentPane().add(buttonDibuja, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonGenerarPuntosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGenerarPuntosActionPerformed
        if (textNumPuntos.getText().isBlank() || Integer.parseInt(textNumPuntos.getText()) < 0) { // controlar que sean solo numeros enteros
            System.out.println("Error: pon una cantidad de puntos entre 0-1000");
        } else {
            ArrayList<Punto> lp = cp.generaPuntosAleatorios(Integer.parseInt(textNumPuntos.getText()), 10,
                    jPanelPuntos.getWidth() - 10, 10, jPanelPuntos.getHeight() - 10);
            gp.drawLine(0, jPanelPuntos.getHeight() / 2, jPanelPuntos.getWidth(), jPanelPuntos.getHeight() / 2);
            gp.drawLine(jPanelPuntos.getWidth() / 2, 0, jPanelPuntos.getWidth() / 2, jPanelPuntos.getHeight());
            gp.drawLine(jPanelPuntos.getWidth() / 2, 0, jPanelPuntos.getWidth() / 2, jPanelPuntos.getHeight());
            Font f = new Font("Serif", Font.BOLD, 12);
            gp.setFont(f);
            gp.drawString("X", jPanelPuntos.getWidth()-15, (jPanelPuntos.getHeight() / 2) - 5);
            gp.drawString("Y", (jPanelPuntos.getWidth() / 2)+5, 15);
            for (Punto punto : lp) {
                gp.drawOval((int) punto.getX(), (int) punto.getY(), 7, 7);
                gp.fillOval((int) punto.getX(), (int) punto.getY(), 7, 7);

            }
        }

    }//GEN-LAST:event_buttonGenerarPuntosActionPerformed

    private void buttonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSalirActionPerformed
        dispose();
    }//GEN-LAST:event_buttonSalirActionPerformed

    private void buttonAbrirMenuFicherosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAbrirMenuFicherosActionPerformed
        //try {
//            ArrayList<Punto> lPuntos;
//            String path = "./dataset_amc/berlin52.tsp/berlin52.tsp";
//            lPuntos = cp.generaPuntosFichero(path);
//            for (Punto lPunto : lPuntos) {
//                gp.drawOval((int)lPunto.getX(), (int)lPunto.getY(), 1, 1);
//            }
//        } catch (Exception ex) {
//            System.out.println("Error en el boton de generar puntos");
//        }
    }//GEN-LAST:event_buttonAbrirMenuFicherosActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton buttonAbrirMenuFicheros;
    public javax.swing.JButton buttonDibuja;
    public javax.swing.JButton buttonGenerarFichero;
    public javax.swing.JButton buttonGenerarPuntos;
    public javax.swing.JButton buttonSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    public javax.swing.JPanel jPanelPuntos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    public javax.swing.JTextArea textAreaMensajes;
    public javax.swing.JTextField textNumPuntos;
    // End of variables declaration//GEN-END:variables
}
