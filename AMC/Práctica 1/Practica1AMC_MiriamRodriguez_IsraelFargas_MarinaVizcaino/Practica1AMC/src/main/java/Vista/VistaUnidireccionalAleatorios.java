/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Modelo.ParDePuntos;
import Modelo.Punto;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

/**
 *
 * @author marin
 */
public class VistaUnidireccionalAleatorios extends javax.swing.JFrame {

    private ArrayList<Punto> puntos;
    private ArrayList<ParDePuntos> puntosCamino;
    private int TamPuntos = 5, TamMult = 1;

    public VistaUnidireccionalAleatorios() {
        initComponents();
        puntos = new ArrayList<>();
        puntosCamino = new ArrayList<>();

    }

    public ArrayList<Punto> getPuntos() {
        return puntos;
    }

    public void setPuntos(ArrayList<Punto> puntos) {
        this.puntos = puntos;
    }

    public ArrayList<ParDePuntos> getPuntosCamino() {
        return puntosCamino;
    }

    public void setPuntosCamino(ArrayList<ParDePuntos> puntosCamino) {
        this.puntosCamino = puntosCamino;
    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);
        //g=PanelPuntos.getGraphics();
        g = this.JPanelUnidireccionalAleatorios.getGraphics();
        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;
        int panelWidth = JPanelUnidireccionalAleatorios.getWidth();
        int panelHeight = JPanelUnidireccionalAleatorios.getHeight();
        ArrayList<Punto> puntosRescalados = new ArrayList<>();

        if (!puntos.isEmpty()) {

            for (Punto p : puntos) {

                minX = (int) Math.min(minX, p.getX());
                maxX = (int) Math.max(maxX, p.getX());
                minY = (int) Math.min(minY, p.getY());
                maxY = (int) Math.max(maxY, p.getY());

                //g.fillOval((int)p.getX()*TamMult,(int) p.getY()*TamMult, TamPuntos,TamPuntos);
            }
            int rangeX = maxX - minX;
            int rangeY = maxY - minY;
            int buffer = 20;
            double scaleX = (panelWidth - buffer) / (maxX - minX);
            double scaleY = (panelHeight - buffer) / (maxY - minY);
            double scale = Math.min(scaleX, scaleY);

            //Dibujamos los puntos escalados
            double x;
            double y;
            for (Punto p : puntos) {

                x = (p.getX() - minX) / rangeX * (panelWidth - buffer);
                y = (p.getY() - minY) / rangeY * (panelHeight - buffer);

                g.fillOval((int) x, (int) y, TamPuntos, TamPuntos);

            }

            //Dibujamos camino
            g.setColor(Color.red);
            double punto1X, punto1Y, punto2X, punto2Y;
            for (ParDePuntos camino : puntosCamino) {
                punto1X = (camino.getA().getX() - minX) / rangeX * (panelWidth - buffer);
                punto1Y = (camino.getA().getY() - minY) / rangeY * (panelHeight - buffer);

                punto2X = (camino.getB().getX() - minX) / rangeX * (panelWidth - buffer);
                punto2Y = (camino.getB().getY() - minY) / rangeY * (panelHeight - buffer);

                g.drawLine((int) punto1X, (int) punto1Y, (int) punto2X, (int) punto2Y);

            }

            //System.out.println("Tamaño pantalla: "+JPanelAleatorios.getWidth() +", "+JPanelAleatorios.getHeight()+"\nY los puntos son"+puntosCercanos.getA().getX()+" "+puntosCercanos.getA().getY()+" "+puntosCercanos.getB().getX()+" "+puntosCercanos.getB().getY());
            //System.out.println(punto1X+" "+punto1Y+" "+punto2X+" "+punto2Y);
            g.setColor(Color.BLACK);

        }

    }

    public void pintaGrafo() {

        repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        TextNumUnidireccional = new javax.swing.JTextField();
        JPanelUnidireccionalAleatorios = new javax.swing.JPanel();
        BotonGenerarAnalizarAleatoriosUnidireccional = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        BotonVolverUnidireccionalAleatorios = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TextCamino = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        TextCoste = new javax.swing.JTextArea();
        BotonGuardarEnFicheroUnidireccionalAleatorios = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nº puntos");

        TextNumUnidireccional.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextNumUnidireccionalActionPerformed(evt);
            }
        });

        JPanelUnidireccionalAleatorios.setFocusTraversalPolicyProvider(true);

        javax.swing.GroupLayout JPanelUnidireccionalAleatoriosLayout = new javax.swing.GroupLayout(JPanelUnidireccionalAleatorios);
        JPanelUnidireccionalAleatorios.setLayout(JPanelUnidireccionalAleatoriosLayout);
        JPanelUnidireccionalAleatoriosLayout.setHorizontalGroup(
            JPanelUnidireccionalAleatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 824, Short.MAX_VALUE)
        );
        JPanelUnidireccionalAleatoriosLayout.setVerticalGroup(
            JPanelUnidireccionalAleatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        BotonGenerarAnalizarAleatoriosUnidireccional.setText(" Generar y Analizar");
        BotonGenerarAnalizarAleatoriosUnidireccional.setActionCommand("Generar y Analizar Unidireccional Aleatorios");
        BotonGenerarAnalizarAleatoriosUnidireccional.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonGenerarAnalizarAleatoriosUnidireccionalActionPerformed(evt);
            }
        });

        jLabel3.setText("Camino");

        jLabel4.setText("Coste");

        BotonVolverUnidireccionalAleatorios.setText("Volver");
        BotonVolverUnidireccionalAleatorios.setActionCommand("Volver Unidireccional Aleatorios");

        TextCamino.setColumns(20);
        TextCamino.setRows(5);
        jScrollPane1.setViewportView(TextCamino);

        TextCoste.setColumns(20);
        TextCoste.setRows(5);
        jScrollPane2.setViewportView(TextCoste);

        BotonGuardarEnFicheroUnidireccionalAleatorios.setText("Guardar En Fichero");
        BotonGuardarEnFicheroUnidireccionalAleatorios.setActionCommand("Guardar En Fichero Unidireccional aleatorios");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(TextNumUnidireccional, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(BotonGenerarAnalizarAleatoriosUnidireccional, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(JPanelUnidireccionalAleatorios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(84, 84, 84))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(BotonGuardarEnFicheroUnidireccionalAleatorios)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BotonVolverUnidireccionalAleatorios)))))
                .addGap(31, 31, 31))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TextNumUnidireccional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotonGenerarAnalizarAleatoriosUnidireccional, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BotonVolverUnidireccionalAleatorios)
                            .addComponent(BotonGuardarEnFicheroUnidireccionalAleatorios)))
                    .addComponent(JPanelUnidireccionalAleatorios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TextNumUnidireccionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextNumUnidireccionalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextNumUnidireccionalActionPerformed

    private void BotonGenerarAnalizarAleatoriosUnidireccionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonGenerarAnalizarAleatoriosUnidireccionalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotonGenerarAnalizarAleatoriosUnidireccionalActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton BotonGenerarAnalizarAleatoriosUnidireccional;
    public javax.swing.JButton BotonGuardarEnFicheroUnidireccionalAleatorios;
    public javax.swing.JButton BotonVolverUnidireccionalAleatorios;
    public javax.swing.JPanel JPanelUnidireccionalAleatorios;
    public javax.swing.JTextArea TextCamino;
    public javax.swing.JTextArea TextCoste;
    public javax.swing.JTextField TextNumUnidireccional;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
