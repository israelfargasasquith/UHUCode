/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Modelo.ParDePuntos;
import Modelo.Punto;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author marin
 */
public class VistaPuntosFichero extends javax.swing.JFrame {

    private ArrayList<Punto> lPuntos;
    private ParDePuntos puntosCercanos;

    public void setLPuntos(ArrayList<Punto> lPuntos) {
        this.lPuntos = lPuntos;
        repaint();
    }
     public ParDePuntos getPuntosCercanos() {
        return puntosCercanos;
    }

    public void setPuntosCercanos(ParDePuntos puntosCercanos) {
        this.puntosCercanos = puntosCercanos;
    }


    /**
     * Creates new form VistaPuntosFichero
     */
    public VistaPuntosFichero() {
        initComponents();
        jPanelPuntosFichero.setBackground(Color.WHITE);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (lPuntos != null) {
            g = jPanelPuntosFichero.getGraphics();
            ArrayList<Punto> lPuntosReescalados = new ArrayList<>();
            
            //Primero cogemos el valor minimo del fichero
            double xMax = -1, yMax = -1;
            for (Punto lPunto : lPuntos) {
                System.out.println("El valor de xMax es: " + xMax + " y el de yMax es: " + yMax);
                if (xMax < lPunto.getX()) {
                    xMax = lPunto.getX();
                }
                if (yMax < lPunto.getY()) {
                    yMax = lPunto.getY();
                }
            }
            double factorConversionX = (double) (this.jPanelPuntosFichero.getWidth() - 10.0) / xMax;
            double factorConversionY = (double) (this.jPanelPuntosFichero.getHeight() - 10.0) / yMax;
            System.out.println("Factor x: " + factorConversionX + " Factor y: " + factorConversionY);
            for (Punto lPunto : lPuntos) {
                lPuntosReescalados.add(new Punto(factorConversionX * lPunto.getX(), factorConversionY * lPunto.getY(), lPunto.getId()));
                System.out.println("Valor del punto original-> id: " + lPunto.getId() + " x: " + lPunto.getX() + " y: " + lPunto.getY()
                        + "\n Valor del punto reescalado-> id: " + lPunto.getId() + " x: " + lPunto.getX() * factorConversionX + " y: " + lPunto.getY() * factorConversionY);
            }

            for (Punto punto : lPuntosReescalados) {
                g.drawOval((int) punto.getX(), (int) punto.getY(), 7, 7);
                g.fillOval((int) punto.getX(), (int) punto.getY(), 7, 7);
            }
            
            double punto1X = Math.round((puntosCercanos.getA().getX() - minX) * scale);
            double punto1Y = Math.round((puntosCercanos.getA().getY() - minY) * scale);

            double punto2X = Math.round((puntosCercanos.getB().getX() - minX) * scale);
            double punto2Y = Math.round((puntosCercanos.getB().getY() - minY) * scale);

            System.out.println("Tamaño pantalla: "+jPanelPuntosFichero.getWidth() +", "+jPanelPuntosFichero.getHeight()+"\nY los puntos son"+puntosCercanos.getA().getX()+""
                    + " "+puntosCercanos.getA().getY()+" "+puntosCercanos.getB().getX()+" "+puntosCercanos.getB().getY());
            System.out.println(punto1X+" "+punto1Y+" "+punto2X+" "+punto2Y);
            g.setColor(Color.red);

            g.drawLine((int) punto1X, (int) punto1Y, (int) punto2X, (int) punto2Y);
            g.setColor(Color.BLACK);

        }
    }

    @Override
    public void update(Graphics g) {
        paint(g);
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
        jButtonCalcularFichero = new javax.swing.JButton();

        jLabel2.setText("Elige algoritmo");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(750, 500));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setText("Elige algoritmo");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 91, -1));

        jComboAlgoritmos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Exhaustivo", "Búsqueda con Poda", "DyV", "DyV mejorado" }));
        getContentPane().add(jComboAlgoritmos, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 109, -1));

        jButtonEligeFichero.setText("Elegir Fichero");
        getContentPane().add(jButtonEligeFichero, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, -1, -1));

        jButtonGeneraFichero.setText("Generar Fichero Aleatorio");
        getContentPane().add(jButtonGeneraFichero, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 20, 210, -1));

        jButtonVolverFicheros.setText("Volver");
        jButtonVolverFicheros.setActionCommand("Volver desde fichero");
        jButtonVolverFicheros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVolverFicherosActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonVolverFicheros, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 470, -1, -1));

        jPanelPuntosFichero.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelPuntosFichero.setPreferredSize(new java.awt.Dimension(690, 370));

        javax.swing.GroupLayout jPanelPuntosFicheroLayout = new javax.swing.GroupLayout(jPanelPuntosFichero);
        jPanelPuntosFichero.setLayout(jPanelPuntosFicheroLayout);
        jPanelPuntosFicheroLayout.setHorizontalGroup(
            jPanelPuntosFicheroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanelPuntosFicheroLayout.setVerticalGroup(
            jPanelPuntosFicheroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 368, Short.MAX_VALUE)
        );

        getContentPane().add(jPanelPuntosFichero, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        jButtonCalcularFichero.setText("Calcular");
        jButtonCalcularFichero.setActionCommand("CalcularMasCercanoFichero");
        jButtonCalcularFichero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCalcularFicheroActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonCalcularFichero, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, 120, -1));

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonVolverFicherosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVolverFicherosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonVolverFicherosActionPerformed

    private void jButtonCalcularFicheroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCalcularFicheroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonCalcularFicheroActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton jButtonCalcularFichero;
    public javax.swing.JButton jButtonEligeFichero;
    public javax.swing.JButton jButtonGeneraFichero;
    public javax.swing.JButton jButtonVolverFicheros;
    public javax.swing.JComboBox<String> jComboAlgoritmos;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    public javax.swing.JPanel jPanelPuntosFichero;
    // End of variables declaration//GEN-END:variables
}
