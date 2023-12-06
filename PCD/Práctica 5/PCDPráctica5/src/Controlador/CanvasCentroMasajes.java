/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.ClienteMasaje;
import Modelo.ClienteRehabilita;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author 34667
 */
public class CanvasCentroMasajes extends Canvas {

    private int alto, ancho;
    private ArrayList<String> acabados;
    private BufferedImage clienteMasaje, clienteRehabilitacion, masajista, fisio;
    private boolean masajeLibre;
    private boolean fisioLibre;
    private boolean vestuarioLibre;
    private String idClienteEnMasaje;
    private String idClienteEnRehabilitacion;
    private String idClienteEnVestuario;
    private String quienEnRehabilitacion; //M o R
    private String quienEnVestuario; // M o R
    private ArrayList<String> colaMasaje;
    private ArrayList<String> colaRehabilita;

    public CanvasCentroMasajes(int alto, int ancho) {

        this.alto = alto;
        this.ancho = ancho;
        idClienteEnMasaje = "";
        idClienteEnRehabilitacion = "";
        idClienteEnVestuario = "";
        quienEnRehabilitacion = "";
        quienEnVestuario = "";
        masajeLibre = true;
        fisioLibre = true;
        vestuarioLibre = true;
        colaRehabilita = new ArrayList<>();
        colaMasaje = new ArrayList<>();
        acabados = new ArrayList<>();

        try {
            clienteMasaje = ImageIO.read(new File("src\\Imagenes\\clienteMasaje.jpg"));
            clienteRehabilitacion = ImageIO.read(new File("src\\Imagenes\\dolorEspalda.jpg"));
            masajista = ImageIO.read(new File("src\\Imagenes\\masajista.jpg"));
            fisio = ImageIO.read(new File("src\\Imagenes\\fisio.jpg"));
        } catch (IOException ex) {
            System.out.println("Error con los archivos: " + ex.getMessage());
        }

        setBackground(Color.LIGHT_GRAY);
        setSize(alto, ancho);
        setVisible(true);
        repaint();
    }

    @Override
    public synchronized void paint(Graphics g) {
        Image buffer = createImage(alto, ancho);
        Graphics tmp = buffer.getGraphics();

        Font f1 = new Font("Helvetica", Font.BOLD, 20);
        tmp.setFont(f1);
        int posMasajista[] = {625, 100};
        int posFisio[] = {625, 625};
        int posVestuario[] = {850, 300};
        int posAcabados[] = {20, 380};
        int mover = 90; //pixeles 
        int posColaMasaje[] = {20, 100};
        int posColaRehabilitacion[] = {20, 650};
        int nMasaje = colaMasaje.size();
        int nRehabilita = colaRehabilita.size();
        int nAcabados = acabados.size();

        tmp.drawString("Cola de Masajes:", 20, 95);
        tmp.drawString("Cola de Fisio:", 20, 600);
        tmp.drawString("Masajista:", 625, 95);
        tmp.drawString("Fisioterapeuta:", 625, 620);
        tmp.drawString("Vestuario:", 850, 295);
        tmp.drawString("Acabados:", 20, 375);

        tmp.setColor(Color.WHITE);
        tmp.fillRect(posColaMasaje[0], posColaMasaje[1], 600, 100); //Cola masajes
        tmp.fillRect(posColaRehabilitacion[0], posColaRehabilitacion[1], 600, 100); //Cola fisio
        tmp.fillRect(posMasajista[0], posMasajista[1], 200, 125); //Masajista
        tmp.fillRect(posFisio[0], posFisio[1], 200, 125); //Fisio
        tmp.fillRect(posVestuario[0], posVestuario[1], 250, 250); //Vestuario
        tmp.fillRect(posAcabados[0], posAcabados[1], 600, 100); //Acabados

        tmp.drawImage(fisio, posFisio[0], posFisio[1], 90, 100, this);
        tmp.drawImage(masajista, posMasajista[0], posMasajista[1], 90, 100, this);

        tmp.setColor(Color.black);

        System.out.println("Hay esperando fisio: " + colaRehabilita.size() + " esperando masaje: " + colaMasaje.size());

        for (String idRehabilita : colaRehabilita) {
            tmp.drawImage(clienteRehabilitacion, posColaRehabilitacion[0] + mover * (nRehabilita - 1), posColaRehabilitacion[1], 90, 100, this);
            tmp.drawString(idRehabilita, posColaRehabilitacion[0] + mover * (nRehabilita - 1), posColaRehabilitacion[1] + 20);
            nRehabilita--;
        }
        for (String idMasaje : colaMasaje) {
            tmp.drawImage(clienteMasaje, posColaMasaje[0] + mover * (nMasaje - 1), posColaMasaje[1], 90, 100, this);
            tmp.drawString(idMasaje, posColaMasaje[0] + mover * (nMasaje - 1), posColaMasaje[1] + 20);
            nMasaje--;
        }

        for (String idAcabado : acabados) {
            if (nAcabados > 7) {
                tmp.drawString(idAcabado, posAcabados[0] + mover * (nAcabados - 1) - 630, posAcabados[1] + 90);
            } else {
                tmp.drawString(idAcabado, posAcabados[0] + mover * (nAcabados - 1), posAcabados[1] + 30);
            }
            nAcabados--;
        }
        if (!fisioLibre) {
            if (quienEnRehabilitacion.equalsIgnoreCase("M")) {
                tmp.drawImage(clienteMasaje, posFisio[0] + mover, posFisio[1], 90, 100, this);
                tmp.drawString(idClienteEnRehabilitacion, posFisio[0] + mover, posFisio[1] + 20);
            } else {
                tmp.drawImage(clienteRehabilitacion, posFisio[0] + mover, posFisio[1], 90, 100, this);
                tmp.drawString(idClienteEnRehabilitacion, posFisio[0] + mover, posFisio[1] + 20);

            }
        }
        if (!masajeLibre) {
            tmp.drawImage(clienteMasaje, posMasajista[0] + mover, posMasajista[1], 90, 100, this);
            tmp.drawString(idClienteEnMasaje, posMasajista[0] + mover, posMasajista[1] + 20);

        }
        if (!vestuarioLibre) {
            if (quienEnVestuario.equalsIgnoreCase("M")) {
                tmp.drawImage(clienteMasaje, posVestuario[0] + mover, posVestuario[1], 90, 100, this);
                tmp.drawString(idClienteEnVestuario, posVestuario[0] + mover, posVestuario[1] + 20);

            } else {
                tmp.drawImage(clienteRehabilitacion, posVestuario[0] + mover, posVestuario[1], 90, 100, this);
                tmp.drawString(idClienteEnVestuario, posVestuario[0] + mover, posVestuario[1] + 20);

            }
        }

        tmp.drawImage(buffer, 0, 0, null); //pintamos la imagen de buffer en el frame para evitar parpadeos
        g.drawImage(buffer, 0, 0, null);
    }

    @Override
    public synchronized void update(Graphics g) {
        paint(g);
    }

    public synchronized void pintaEsperandoMasaje(String id) {
        colaMasaje.add(id);
        repaint();
    }

    public synchronized void pintaEsperandoRehabilitacion(String id) {
        colaRehabilita.add(id);
        repaint();
    }

    public synchronized void borraEsperandoMasaje(String id) {
        colaMasaje.remove(id);
        repaint();

    }

    public synchronized void borraEsperandoRehabilitacion(String id) {
        colaRehabilita.remove(id);
        repaint();
    }

    public synchronized void pintaEnFisio(String id, String quien) {
        quienEnRehabilitacion = quien;
        idClienteEnRehabilitacion = id;
        fisioLibre = false;
    }

    public synchronized void pintaEnMasaje(String id) {
        idClienteEnMasaje = id;
        masajeLibre = false;
    }

    public synchronized void borraMasaje() {
        masajeLibre = true;
    }

    public synchronized void borraRehabilita() {
        fisioLibre = true;
    }

    public synchronized void pintaVestuario(String id, String quien) {
        idClienteEnVestuario = id;
        quienEnVestuario = quien;
        vestuarioLibre = false;
    }

    public synchronized void borraVestuario(String id) {
        vestuarioLibre = true;
        acabados.add(id);
    }

}
