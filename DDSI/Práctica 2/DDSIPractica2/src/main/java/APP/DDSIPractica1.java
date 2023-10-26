/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package APP;

import Controlador.ControladorLogin;
import Controlador.ControladorPrincipal;

/**
 *
 * @author 34667
 */
public class DDSIPractica1 {

    public static void main(String[] args) {
        ControladorLogin cLogin = new ControladorLogin();
        ControladorPrincipal cPrincipal = new ControladorPrincipal(cLogin.getConexion());
        
        cLogin.desconectarBD();
    }
}
