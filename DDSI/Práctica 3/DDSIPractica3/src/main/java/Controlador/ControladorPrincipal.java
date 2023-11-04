/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author 34667
 */
public class ControladorPrincipal {
    private Session sesion;
    private  Transaction transacion;
    
    public ControladorPrincipal(SessionFactory sesion){
        this.sesion =  sesion.openSession();
        transacion = this.sesion.beginTransaction();
    }
    
}
