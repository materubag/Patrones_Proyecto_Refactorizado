/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interface;

import Modelo.Usuario;
import java.util.ArrayList;

/**
 *
 * @author mateo
 */
public interface IUsuario {
    public void crear(Usuario u)throws Exception;
    public ArrayList<Usuario> listarTodo()throws Exception;
    public Usuario verifUser(String nom) throws Exception;
    public void modEst(String n,String e)throws Exception;
}
