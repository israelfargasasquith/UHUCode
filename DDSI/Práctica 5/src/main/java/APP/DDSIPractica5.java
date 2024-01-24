/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package APP;

import Controlador.ControladorLogin;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 *
 * @author 34667
 */
public class DDSIPractica5 {

    public static void main(String[] args) {
        try {
            ControladorLogin cl = new ControladorLogin();
        } catch (Exception e) {
            System.out.println("Error fatal: " + e.getMessage());
            System.exit(1);
        }
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (Exception ex) {
            System.out.println("Mensaje de error: " + ex.getMessage());
            System.exit(1);

        }
    }
}
