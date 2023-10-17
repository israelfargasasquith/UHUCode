/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Modelo;

/**
 *
 * @author 34667
 */
public interface IPila {
    public int getNumElementos();
    public void apila(Object par) throws java.lang.Exception;
    public Object desapila() throws java.lang.Exception;
    public Object primero() throws java.lang.Exception;
}
