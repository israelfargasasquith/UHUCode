/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author marin
 */
public class Mtime {
    public static double performancecounterDiff(long a, long b){
        long freq=1000000;
        return (double)(a-b)/(double)freq;
    }
}
