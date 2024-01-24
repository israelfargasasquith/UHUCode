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
public class VistaBidireccionalFichero extends javax.swing.JFrame {

 private ArrayList<Punto> puntos;
    private ArrayList<ParDePuntos> puntosCamino;
    private int TamPuntos = 5, TamMult = 1;

    public VistaBidireccionalFichero() {
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
        g = this.JPanelBidireccionalFicheros.getGraphics();
        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;
        int panelWidth = JPanelBidireccionalFicheros.getWidth();
        int panelHeight = JPanelBidireccionalFicheros.getHeight();
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

        JPanelBidireccionalFicheros = new javax.swing.JPanel();
        BotonAnalizarFichero = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        BotonVolverBidireccionalFichero = new javax.swing.JButton();
        BotonAbrirFichero = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TextCamino = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        TextCoste = new javax.swing.JTextArea();
        BotonGuardarEnFicheroBidireccionalFich = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout JPanelBidireccionalFicherosLayout = new javax.swing.GroupLayout(JPanelBidireccionalFicheros);
        JPanelBidireccionalFicheros.setLayout(JPanelBidireccionalFicherosLayout);
        JPanelBidireccionalFicherosLayout.setHorizontalGroup(
            JPanelBidireccionalFicherosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 823, Short.MAX_VALUE)
        );
        JPanelBidireccionalFicherosLayout.setVerticalGroup(
            JPanelBidireccionalFicherosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 634, Short.MAX_VALUE)
        );

        BotonAnalizarFichero.setText("Analizar");
        BotonAnalizarFichero.setActionCommand("AnalizarBidireccionalPorFichero");

        jLabel4.setText("Camino");

        BotonVolverBidireccionalFichero.setText("Volver");
        BotonVolverBidireccionalFichero.setActionCommand("Volver Bidireccional Fichero");

        BotonAbrirFichero.setText("Abrir fichero");
        BotonAbrirFichero.setActionCommand("Abrir fichero Bidireccional");

        jLabel1.setText("Coste");

        TextCamino.setColumns(20);
        TextCamino.setRows(5);
        jScrollPane1.setViewportView(TextCamino);

        TextCoste.setColumns(20);
        TextCoste.setRows(5);
        jScrollPane2.setViewportView(TextCoste);

        BotonGuardarEnFicheroBidireccionalFich.setText("Guardar en Fichero");
        BotonGuardarEnFicheroBidireccionalFich.setActionCommand("Guardar en Fichero Bidireccional fich");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(BotonAbrirFichero)
                        .addGap(47, 47, 47)
                        .addComponent(BotonAnalizarFichero, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BotonVolverBidireccionalFichero))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(JPanelBidireccionalFicheros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BotonGuardarEnFicheroBidireccionalFich, javax.swing.GroupLayout.Alignment.LEADING))))
                .addGap(29, 29, 29))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotonAnalizarFichero)
                    .addComponent(BotonAbrirFichero))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BotonVolverBidireccionalFichero)
                            .addComponent(BotonGuardarEnFicheroBidireccionalFich)))
                    .addComponent(JPanelBidireccionalFicheros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton BotonAbrirFichero;
    public javax.swing.JButton BotonAnalizarFichero;
    public javax.swing.JButton BotonGuardarEnFicheroBidireccionalFich;
    public javax.swing.JButton BotonVolverBidireccionalFichero;
    public javax.swing.JPanel JPanelBidireccionalFicheros;
    public javax.swing.JTextArea TextCamino;
    public javax.swing.JTextArea TextCoste;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
