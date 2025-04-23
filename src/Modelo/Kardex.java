/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author mateo
 */
public class Kardex {
    private String codigo;
    private String producto;
    private String fecha;
    private String codigoC;
    private String codigoV;
    private float costoU;
    private int entrada;
    private int salida;
    private int inventario;

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getProducto() {
        return this.producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getFecha() {
        return this.fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCodigoC() {
        return this.codigoC;
    }

    public void setCodigoC(String codigoC) {
        this.codigoC = codigoC;
    }

    public String getCodigoV() {
        return this.codigoV;
    }

    public void setCodigoV(String codigoV) {
        this.codigoV = codigoV;
    }
    
    public float getCostoU() {
        return this.costoU;
    }

    public void setCostoU(float costoU) {
        this.costoU = costoU;
    }

    public int getEntrada() {
        return this.entrada;
    }

    public void setEntrada(int entrada) {
        this.entrada = entrada;
    }

    public int getSalida() {
        return this.salida;
    }

    public void setSalida(int salida) {
        this.salida = salida;
    }

    

    public int getInventario() {
        return this.inventario;
    }

    public void setInventario(int inventario) {
        this.inventario = inventario;
    }
    
}
