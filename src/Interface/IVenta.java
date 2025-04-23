/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interface;

import Modelo.Venta;
import java.util.ArrayList;

/**
 *
 * @author mateo
 */
public interface IVenta {
    public void crear(Venta v)throws Exception;
    public ArrayList<Venta> listarTodo()throws Exception;
    public void modificar(Venta v)throws Exception;
    public void borrar(String cod)throws Exception;
}
