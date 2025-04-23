/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author mateo
 */
public class Cliente {
    private String cedula;
    private String nombre;
    private String direccion;
    private String codigoProv;
    private String codigoCiu;

    public String getCedula() {
        return this.cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCodigoProv() {
        return this.codigoProv;
    }

    public void setCodigoProv(String codigoProv) {
        this.codigoProv = codigoProv;
    }

    public String getCodigoCiu() {
        return this.codigoCiu;
    }

    public void setCodigoCiu(String codigoCiu) {
        this.codigoCiu = codigoCiu;
    }
    
}
