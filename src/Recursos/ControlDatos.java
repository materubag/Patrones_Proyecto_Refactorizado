/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Recursos;

/**
 *
 * @author mateo
 */
public class ControlDatos {

    public boolean tresP(String t) {
        String[] palabras = t.split(" ");
        boolean verif=false;
        if (palabras.length >= 3) {
            verif=true;
        } 
        return verif;
    }

    public boolean dosP(String t) {
        String[] palabras = t.split(" ");
        boolean verif=false;
        if (palabras.length >= 2) {
            verif=true;
        } 
        return verif;
    }

    public boolean numeros(String t) {
        boolean verif=false;
        if (t.matches("\\d+")) {
            verif=true;
        }
        return verif;
    }
    public boolean decimal(String t) {
        boolean verif=false;
        if (t.matches("^\\d*\\.?\\d*$")) {
            verif=true;
        }
        return verif;
    }
}
