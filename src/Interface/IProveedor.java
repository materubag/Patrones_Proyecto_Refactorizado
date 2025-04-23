/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interface;

import Modelo.Proveedor;
import java.util.ArrayList;

/**
 *
 * @author mateo
 */
public interface IProveedor {
    public void crear(Proveedor pr)throws Exception;
    public void modificar(Proveedor pr)throws Exception;
    public ArrayList<Proveedor> listarTodo()throws Exception;
    public void borrar(String ruc)throws Exception;
    public boolean existe(String ruc)throws Exception;
}
