/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Gato;
import Modelo.Perro;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author 34667
 */
public class CanvasPerrosGatos extends Canvas {

    private ArrayList<Long> colaPerros;
    private ArrayList<Long> colaGatos;
    private ArrayList<Long> acabados;
    private ArrayList<Long> gatosComiendo;
    private ArrayList<Long> perrosComiendo;
    private Image gato;
    private Image perro;
    private Image perroComiendo;
    private Image gatoComiendo;

    public CanvasPerrosGatos(int ancho, int alto) {
        super.setSize(ancho, alto);
        super.setBackground(Color.lightGray);

        colaGatos = new ArrayList<>();
        colaPerros = new ArrayList<>();
        acabados = new ArrayList<>();
        gatosComiendo = new ArrayList<>();
        perrosComiendo =  new ArrayList<>();
                
        gato = getIcono("src\\Imagenes\\gato.png", 70, 70).getImage();
        perro = getIcono("src\\Imagenes\\perro.png", 70, 70).getImage();
        gatoComiendo = getIcono("src\\Imagenes\\gatoComiendo.png", 70, 70).getImage();
        perroComiendo = getIcono("src\\Imagenes\\perroComiendo2.png", 70, 70).getImage();
    }

    private ImageIcon getIcono(String ruta, int x, int y) {

        ImageIcon img = new ImageIcon(ruta);
        Image imagen = img.getImage();
        Image newimg = imagen.getScaledInstance(x, y, Image.SCALE_SMOOTH);
        ImageIcon icono = new ImageIcon(newimg);

        return icono;
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void paint(Graphics g) {

        Image imagen = createImage(getWidth(), getHeight());
        Font f1 = new Font("Arial", Font.BOLD, 20);
        Font f2 = new Font("Arial", Font.BOLD, 16);

        Graphics gbuf = imagen.getGraphics();

        gbuf.setFont(f1);
        gbuf.setColor(Color.cyan);

        gbuf.fillRect(50, 60, 400, 200); //Cola espera perros
        gbuf.fillRect(550, 60, 400, 200); //Cola espera gatos
        gbuf.fillRect(350, 350, 500, 200); //Comedero
        gbuf.fillRect(50, 600, 900, 100); //Acabados
        gbuf.setColor(Color.black);
        gbuf.drawString("Cola espera perros", 50, 55);
        gbuf.drawString("Cola espera gatos", 550, 55);
        gbuf.drawString("Comedero", 350, 345);
        gbuf.drawString("Acabados", 50, 595);

        
        gbuf.setFont(f2);
        for (int i = 0; i < colaGatos.size(); i++) { //rellena cola espera gatos
            gbuf.drawString(""+colaGatos.get(i), 555 + 90 * i, 85);
            gbuf.drawImage(gato, 555 + 90 * i, 85, this);
        }
        for (int i = 0; i < colaPerros.size(); i++) { //rellena cola espera perros
            gbuf.drawString(""+colaPerros.get(i), 55 + 90 * i, 85);
            gbuf.drawImage(perro, 55 + 90 * i, 85, this);
        }

        for (int i = 0; i < gatosComiendo.size(); i++) { //pinta en el comedero arriba los gatos 
            gbuf.drawString(""+gatosComiendo.get(i), 350 + 90 * i, 365);
            gbuf.drawImage(gatoComiendo, 350+90*i, 365, this);
        }
        
        for (int i = 0; i < perrosComiendo.size(); i++) { //pinta en el comedero arriba los gatos 
            gbuf.drawString(""+perrosComiendo.get(i), 350 + 90 * i, 465);
            gbuf.drawImage(perroComiendo, 350+90*i, 465, this);
        }

        for (int i = 0; i < acabados.size(); i++) {
            if (i < 10) {
                gbuf.drawString(""+acabados.get(i), 50 + 90 * i, 615);
            } else if(i <20){
                gbuf.drawString(""+acabados.get(i), 50 + 90 * (i - 10), 645);
            }else{
                gbuf.drawString(""+acabados.get(i), 50 + 90 * (i - 20), 675);
            }

        }

        g.drawImage(imagen, 0, 0, this);
    }

    public void pintaEsperaPerro(Long id) {

        colaPerros.add(id);
        repaint();

    }

    public void borraEsperaPerro(Long id) {
        colaPerros.remove(colaPerros.indexOf(id));
        repaint();
    }

    public void pintaEsperaGato(Long id) {
        colaGatos.add(id);
        repaint();
    }

    public void borraEsperaGato(Long id) {
        colaGatos.remove(colaGatos.indexOf(id));
        repaint();
    }

    public void pintaEntraGato(Long id) {
        gatosComiendo.add(id);
        repaint();
    }

    public void pintaEntraPerro(Long id) {
        perrosComiendo.add(id);
        repaint();
    }

    public void borraGatoComiendo(Long id) { //Y ambos pintan en la cola de acabados
        acabados.add(gatosComiendo.get(gatosComiendo.indexOf(id)));
        gatosComiendo.remove(gatosComiendo.indexOf(id));
        repaint();
    }

    public void borraPerroComiendo(Long id) {
        acabados.add(perrosComiendo.get(perrosComiendo.indexOf(id)));
        perrosComiendo.remove(perrosComiendo.indexOf(id));
        repaint();
    }

}
