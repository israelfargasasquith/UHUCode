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
public class VistaBidireccionalAleatorios extends javax.swing.JFrame {

    private ArrayList<Punto> puntos;
    private ArrayList<ParDePuntos> puntosCamino;
    private int TamPuntos = 5, TamMult = 1;

    public VistaBidireccionalAleatorios() {
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
        g = this.JPanelBidireccionalAleatorios.getGraphics();
        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;
        int panelWidth = JPanelBidireccionalAleatorios.getWidth();
        int panelHeight = JPanelBidireccionalAleatorios.getHeight();
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
        TextNumBidireccional = new javax.swing.JTextField();
        JPanelBidireccionalAleatorios = new javax.swing.JPanel();
        BotonGenerarAnalizarAleatoriosBidireccional = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        BotonVolverBidireccionalAleatorios = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TextCamino = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        TextCoste = new javax.swing.JTextArea();
        BotonGuardarEnFicheroBidireccionalAleatorio = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nº puntos");

        TextNumBidireccional.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextNumBidireccionalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JPanelBidireccionalAleatoriosLayout = new javax.swing.GroupLayout(JPanelBidireccionalAleatorios);
        JPanelBidireccionalAleatorios.setLayout(JPanelBidireccionalAleatoriosLayout);
        JPanelBidireccionalAleatoriosLayout.setHorizontalGroup(
            JPanelBidireccionalAleatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 864, Short.MAX_VALUE)
        );
        JPanelBidireccionalAleatoriosLayout.setVerticalGroup(
            JPanelBidireccionalAleatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 623, Short.MAX_VALUE)
        );

        BotonGenerarAnalizarAleatoriosBidireccional.setText(" Generar y Analizar");
        BotonGenerarAnalizarAleatoriosBidireccional.setActionCommand("Generar y Analizar Bidireccional Aleatorios");
        BotonGenerarAnalizarAleatoriosBidireccional.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonGenerarAnalizarAleatoriosBidireccionalActionPerformed(evt);
            }
        });

        jLabel3.setText("Camino");

        jLabel4.setText("Coste");

        BotonVolverBidireccionalAleatorios.setText("Volver");
        BotonVolverBidireccionalAleatorios.setActionCommand("Volver Bidireccional Aleatorios");

        TextCamino.setColumns(20);
        TextCamino.setRows(5);
        jScrollPane1.setViewportView(TextCamino);

        TextCoste.setColumns(20);
        TextCoste.setRows(5);
        jScrollPane2.setViewportView(TextCoste);

        BotonGuardarEnFicheroBidireccionalAleatorio.setText("Guardar en Fichero");
        BotonGuardarEnFicheroBidireccionalAleatorio.setActionCommand("Guardar en Fichero Bidireccional Aleatorio");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(JPanelBidireccionalAleatorios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(BotonGuardarEnFicheroBidireccionalAleatorio)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(BotonVolverBidireccionalAleatorios))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(TextNumBidireccional, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(BotonGenerarAnalizarAleatoriosBidireccional, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
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
                            .addComponent(TextNumBidireccional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotonGenerarAnalizarAleatoriosBidireccional, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BotonGuardarEnFicheroBidireccionalAleatorio)
                            .addComponent(BotonVolverBidireccionalAleatorios)))
                    .addComponent(JPanelBidireccionalAleatorios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TextNumBidireccionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextNumBidireccionalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextNumBidireccionalActionPerformed

    private void BotonGenerarAnalizarAleatoriosBidireccionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonGenerarAnalizarAleatoriosBidireccionalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotonGenerarAnalizarAleatoriosBidireccionalActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton BotonGenerarAnalizarAleatoriosBidireccional;
    public javax.swing.JButton BotonGuardarEnFicheroBidireccionalAleatorio;
    public javax.swing.JButton BotonVolverBidireccionalAleatorios;
    public javax.swing.JPanel JPanelBidireccionalAleatorios;
    public javax.swing.JTextArea TextCamino;
    public javax.swing.JTextArea TextCoste;
    public javax.swing.JTextField TextNumBidireccional;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
