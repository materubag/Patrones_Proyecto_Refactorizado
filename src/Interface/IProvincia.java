/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interface;

import Modelo.Provincia;
import java.util.ArrayList;

/**
 *
 * @author mateo
 */
public interface IProvincia {
    public void crear(Provincia p)throws Exception;
    public void modificar(Provincia p)throws Exception;
    public ArrayList<Provincia> listarTodo()throws Exception;
    public void borrar(String codigo)throws Exception;
    public void delCiuAdjunta(String codigo)throws Exception;
    public boolean provUsada(String cod) throws Exception;
}
