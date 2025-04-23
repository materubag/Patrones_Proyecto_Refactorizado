/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interface;

import Modelo.Cliente;
import java.util.ArrayList;

/**
 *
 * @author mateo
 */
public interface ICliente {
    public void crear(Cliente cl)throws Exception;
    public void modificar(Cliente cl)throws Exception;
    public ArrayList<Cliente> listarTodo()throws Exception;
    public void borrar(String cedula)throws Exception;
    public ArrayList<String> ciudades(String cod)throws Exception;
    public boolean existe(String cedula)throws Exception;
}
