/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interface;

import Modelo.Ciudad;
import java.util.ArrayList;

/**
 *
 * @author mateo
 */
public interface ICiudad {
    public void crear(Ciudad c)throws Exception;
    public void modificar(Ciudad c)throws Exception;
    public ArrayList<Ciudad> listarTodo()throws Exception;
    public void borrar(String codigo)throws Exception;
    public void provinciaAdjunta(Ciudad c)throws Exception;
    public ArrayList<String> provinciaExistente()throws Exception;
    public boolean ciudadUsada(String cod) throws Exception;
}
