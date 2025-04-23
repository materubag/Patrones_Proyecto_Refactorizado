/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Recursos;


/**
 *
 * @author mateo
 */
public class Cedula {

    public boolean largo(String cedula) {
        boolean verif = false;
        if (cedula.length() == 10 && cedula.matches("\\d+")) {
            if (this.validar(cedula)) {
                verif = true;
            }
        }
        return verif;
    }

    public boolean validar(String cedula) {
        boolean verif = false;
        int provincia = Integer.parseInt(cedula.substring(0, 2));
        int tercer = Integer.parseInt(cedula.substring(2, 3));
        int num1 = Integer.parseInt(cedula.substring(0, 1));
        int num2 = Integer.parseInt(cedula.substring(1, 2));
        int num3 = Integer.parseInt(cedula.substring(2, 3));
        int num4 = Integer.parseInt(cedula.substring(3, 4));
        int num5 = Integer.parseInt(cedula.substring(4, 5));
        int num6 = Integer.parseInt(cedula.substring(5, 6));
        int num7 = Integer.parseInt(cedula.substring(6, 7));
        int num8 = Integer.parseInt(cedula.substring(7, 8));
        int num9 = Integer.parseInt(cedula.substring(8, 9));
        int num10 = Integer.parseInt(cedula.substring(9, 10));
        int mult1 = 2 * num1;
        if (mult1 >= 10) {
            mult1 = mult1 - 9;
        }
        int mult2 = 1 * num2;
        if (mult2 >= 10) {
            mult2 = mult2 - 9;
        }
        int mult3 = 2 * num3;
        if (mult3 >= 10) {
            mult3 = mult3 - 9;
        }
        int mult4 = 1 * num4;
        if (mult4 >= 10) {
            mult4 = mult4 - 9;
        }
        int mult5 = 2 * num5;
        if (mult5 >= 10) {
            mult5 = mult5 - 9;
        }
        int mult6 = 1 * num6;
        if (mult6 >= 10) {
            mult6 = mult6 - 9;
        }
        int mult7 = 2 * num7;
        if (mult7 >= 10) {
            mult7 = mult7 - 9;
        }
        int mult8 = 1 * num8;
        if (mult8 >= 10) {
            mult8 = mult8 - 9;
        }
        int mult9 = 2 * num9;
        if (mult9 >= 10) {
            mult9 = mult9 - 9;
        }
        int sum = mult1 + mult2 + mult3 + mult4 + mult5 + mult6 + mult7 + mult8 + mult9;
        int sum2;
        if ((sum >= 10) && (sum % 10 == 0)) {
            sum2 = sum - sum;
        } else {
            if ((sum > 10) && (sum % 5 == 0)) {
                sum2 = (sum + 5) - sum;
            } else {
                if ((sum > 10)) {
                    sum2 = ((sum - (sum % 10) + 10) - sum);
                } else {
                    sum2 = sum;
                }
            }
        }
        int resultado = sum2;

        if (((provincia <= 24 && provincia > 0) || provincia == 30) && (tercer <= 5) && (resultado == num10)) {
            verif = true;
        }

        return verif;
    }
}
