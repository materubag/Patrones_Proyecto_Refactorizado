/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interface;

import Modelo.Compra;
import java.util.ArrayList;

/**
 *
 * @author mateo
 */
public interface ICompra {
    public void crear(Compra c)throws Exception;
    public ArrayList<Compra> listarTodo()throws Exception;
    public void modificar(Compra c)throws Exception;
    public void borrar(String ruc)throws Exception;
}
