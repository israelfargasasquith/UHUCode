/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package APP;

import Controlador.ControladorLogin;

/**
 *
 * @author 34667
 */
public class DDSIPractica3 {

    public static void main(String[] args) {
        try{
        ControladorLogin cl = new ControladorLogin();
        }catch(Exception e){
            System.out.println("Error fatal: "+e.getMessage());
        }
    }
}
