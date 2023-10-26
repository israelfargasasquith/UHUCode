/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Modelo.Punto;
import java.util.ArrayList;

/**
 *
 * @author marin
 */
public class VistaPuntosFichero extends javax.swing.JFrame {
    private ArrayList<Punto> lPuntos;

    /**
     * Creates new form VistaPuntosFichero
     */
    public VistaPuntosFichero() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboAlgoritmos = new javax.swing.JComboBox<>();
        jButtonEligeFichero = new javax.swing.JButton();
        jButtonGeneraFichero = new javax.swing.JButton();
        jButtonVolverFicheros = new javax.swing.JButton();
        jPanelPuntosFichero = new javax.swing.JPanel();

        jLabel2.setText("Elige algoritmo");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setText("Elige algoritmo");

        jComboAlgoritmos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButtonEligeFichero.setText("Elegir Fichero");

        jButtonGeneraFichero.setText("Generar");
        jButtonGeneraFichero.setActionCommand("Generar Fichero Aleatorio");

        jButtonVolverFicheros.setText("Volver");
        jButtonVolverFicheros.setActionCommand("Volver desde fichero");
        jButtonVolverFicheros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVolverFicherosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelPuntosFicheroLayout = new javax.swing.GroupLayout(jPanelPuntosFichero);
        jPanelPuntosFichero.setLayout(jPanelPuntosFicheroLayout);
        jPanelPuntosFicheroLayout.setHorizontalGroup(
            jPanelPuntosFicheroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanelPuntosFicheroLayout.setVerticalGroup(
            jPanelPuntosFicheroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 360, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanelPuntosFichero, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonVolverFicheros)))
                        .addGap(16, 16, 16))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboAlgoritmos, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(jButtonEligeFichero)
                        .addGap(65, 65, 65)
                        .addComponent(jButtonGeneraFichero)
                        .addContainerGap(206, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboAlgoritmos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonEligeFichero)
                    .addComponent(jButtonGeneraFichero))
                .addGap(34, 34, 34)
                .addComponent(jPanelPuntosFichero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButtonVolverFicheros)
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonVolverFicherosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVolverFicherosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonVolverFicherosActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton jButtonEligeFichero;
    public javax.swing.JButton jButtonGeneraFichero;
    public javax.swing.JButton jButtonVolverFicheros;
    public javax.swing.JComboBox<String> jComboAlgoritmos;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    public javax.swing.JPanel jPanelPuntosFichero;
    // End of variables declaration//GEN-END:variables
}
