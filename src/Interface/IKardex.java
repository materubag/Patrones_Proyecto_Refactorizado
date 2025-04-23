/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interface;

import Modelo.Kardex;
import java.util.ArrayList;

/**
 *
 * @author mateo
 */
public interface IKardex {
    public void crear(Kardex k)throws Exception;
    public ArrayList<Kardex> listarProducto(String prod)throws Exception;
    public int inventario(String producto)throws Exception;
}
