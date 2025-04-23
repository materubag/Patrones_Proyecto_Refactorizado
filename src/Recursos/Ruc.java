/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Recursos;


/**
 *
 * @author mateo
 */
public class Ruc {

    public boolean validar(String ruc) {
        boolean verif = false;
        if (ruc.length() == 13 && ruc.matches("\\d+")) {
            if (this.RucNat(ruc)) {
                verif = true;
            }
        }
        return verif;
    }

    public boolean RucNat(String ruc) {
        boolean verif = false;
        Cedula ced = new Cedula();
        if (ced.validar(ruc.substring(0, 10))) {
            if (Integer.parseInt(ruc.substring(10, 13)) != 0) {
                verif = true;
            }
        }
        return verif;
    }
}
