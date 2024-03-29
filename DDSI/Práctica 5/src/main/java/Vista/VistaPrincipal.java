/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author 34667
 */
public class VistaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form VistaPrincipal
     */
    public VistaPrincipal() {
        initComponents();
        this.setResizable(false);
        this.setTitle("Proyecto DDSI Israel Fargas Asquith");
        Image imagen = new ImageIcon(this.getClass().getResource("../LogoGYM.png")).getImage();
        this.setIconImage(imagen);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuVistaPrincipal = new javax.swing.JMenuBar();
        jMenuHome = new javax.swing.JMenu();
        jMenuMonitores = new javax.swing.JMenu();
        jMenuItemMonitores = new javax.swing.JMenuItem();
        jMenuSocios = new javax.swing.JMenu();
        jMenuItemSocios = new javax.swing.JMenuItem();
        jMenuActividades = new javax.swing.JMenu();
        jMenuItemActividades = new javax.swing.JMenuItem();
        jMenuSalir = new javax.swing.JMenu();
        jMenuItemSalir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenuHome.setText("🏠");
        jMenuHome.setActionCommand("home");
        jMenuVistaPrincipal.add(jMenuHome);

        jMenuMonitores.setText("Monitores");

        jMenuItemMonitores.setText("Gestión de Monitores");
        jMenuMonitores.add(jMenuItemMonitores);

        jMenuVistaPrincipal.add(jMenuMonitores);

        jMenuSocios.setText("Socios");

        jMenuItemSocios.setText("Gestión de Socios");
        jMenuSocios.add(jMenuItemSocios);

        jMenuVistaPrincipal.add(jMenuSocios);

        jMenuActividades.setText("Actividades");

        jMenuItemActividades.setText("Gestionar Altas en Actividades");
        jMenuItemActividades.setActionCommand("GestionaAltas");
        jMenuActividades.add(jMenuItemActividades);

        jMenuVistaPrincipal.add(jMenuActividades);

        jMenuSalir.setText("Salir");

        jMenuItemSalir.setText("Salir de la aplicación");
        jMenuSalir.add(jMenuItemSalir);

        jMenuVistaPrincipal.add(jMenuSalir);

        setJMenuBar(jMenuVistaPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 950, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 577, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JMenu jMenuActividades;
    public javax.swing.JMenu jMenuHome;
    public javax.swing.JMenuItem jMenuItemActividades;
    public javax.swing.JMenuItem jMenuItemMonitores;
    public javax.swing.JMenuItem jMenuItemSalir;
    public javax.swing.JMenuItem jMenuItemSocios;
    public javax.swing.JMenu jMenuMonitores;
    public javax.swing.JMenu jMenuSalir;
    public javax.swing.JMenu jMenuSocios;
    public javax.swing.JMenuBar jMenuVistaPrincipal;
    // End of variables declaration//GEN-END:variables
}
