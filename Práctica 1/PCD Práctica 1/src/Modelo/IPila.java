/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Modelo;

/**
 *
 * @author israel
 */
public interface IPila {
    public int getnNum();
    public void apila(Object obj) throws java.lang.Exception;
    public Object desapila() throws java.lang.Exception;
    public Object primero() throws java.lang.Exception;
    
}
