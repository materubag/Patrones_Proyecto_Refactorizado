/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author mateo
 */
public class Usuario {
    private String nombre;
    private String clave;
    private String estado;
    private String Permisos;
    private int intentos;
    private boolean verif;

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return this.clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    public String getPermisos() {
        return this.Permisos;
    }

    public void setPermisos(String Permisos) {
        this.Permisos = Permisos;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }

    public boolean isVerif() {
        return this.verif;
    }

    public void setVerif(boolean verif) {
        this.verif = verif;
    }
    
}
