/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interface;

import Modelo.Producto;
import java.util.ArrayList;

/**
 *
 * @author mateo
 */
public interface IProducto {
    public void crear(Producto pd)throws Exception;
    public void modificar(Producto pd)throws Exception;
    public ArrayList<Producto> listarTodo()throws Exception;
    public void borrar(String codigo)throws Exception;
}
