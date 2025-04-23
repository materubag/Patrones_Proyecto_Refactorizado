/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Recursos;

import java.util.ArrayList;

/**
 *
 * @author mateo
 */
public class Permisos {

    public String Generar(ArrayList<Boolean> bl) {
        String p = "111111111";
        if (!bl.get(0)) {
            p = "0" + p.substring(1);
        }
        if (!bl.get(1)) {
            p = p.substring(0, 1) + "0" + p.substring(2);
        }
        if (!bl.get(2)) {
            p = p.substring(0, 2) + "0" + p.substring(3);
        }
        if (!bl.get(3)) {
            p = p.substring(0, 3) + "0" + p.substring(4);
        }
        if (!bl.get(4)) {
            p = p.substring(0, 4) + "0" + p.substring(5);
        }
        if (!bl.get(5)) {
            p = p.substring(0, 5) + "0" + p.substring(6);
        }
        if (!bl.get(6)) {
            p = p.substring(0, 6) + "0" + p.substring(7);
        }
        if (!bl.get(7)) {
            p = p.substring(0, 7) + "0" + p.substring(8);
        }
        if (!bl.get(8)) {
            p = p.substring(0, 8) + "0" ;
        }
        return p;
    }

    public ArrayList<Boolean> Leer(String p) {
        ArrayList<Boolean> bl = new ArrayList<Boolean>();
        if (p.substring(0, 1).equals("1")) {
            bl.add(true);
        }else bl.add(false);
        if (p.substring(1, 2).equals("1")) {
            bl.add(true);
        }else bl.add(false);
        if (p.substring(2, 3).equals("1")) {
            bl.add(true);
        }else bl.add(false);
        if (p.substring(3, 4).equals("1")) {
            bl.add(true);
        }else bl.add(false);
        if (p.substring(4, 5).equals("1")) {
            bl.add(true);
        }else bl.add(false);
        if (p.substring(5, 6).equals("1")) {
            bl.add(true);
        }else bl.add(false);
        if (p.substring(6, 7).equals("1")) {
            bl.add(true);
        }else bl.add(false);
        if (p.substring(7, 8).equals("1")) {
            bl.add(true);
        }else bl.add(false);
        if (p.substring(8).equals("1")) {
            bl.add(true);
        }else bl.add(false);
        return bl;
    }
}
