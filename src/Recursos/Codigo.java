/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Recursos;


/**
 *
 * @author mateo
 */
public class Codigo {
    public String getCode(String texto) {
        int indice = texto.indexOf(":"); 
        String code=texto;
        if (indice != -1) {
            code = texto.substring(0, indice); 
        } 
        return code;
    }
}
