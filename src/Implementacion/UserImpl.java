/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Implementacion;

import Gestor.MySQL;
import Interface.IUsuario;
import Modelo.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author mateo
 */
public class UserImpl extends MySQL implements IUsuario {

    @Override
    public void crear(Usuario u) throws Exception {
        try {
            super.conectar();
            if (super.verifConexion() == true) {
                PreparedStatement ps = super.con.prepareStatement("insert into usuario(nombre,clave,estado,permisos) values(?,?,?,?)");
                ps.setString(1, u.getNombre());
                ps.setString(2, u.getClave());
                ps.setString(3, u.getEstado());
                ps.setString(4, u.getPermisos());
                ps.executeUpdate();
                ps.close();
            } else {
                super.jError();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            super.desconectar();
        }
    }


    @Override
    public ArrayList<Usuario> listarTodo() throws Exception {
        ArrayList<Usuario> ulist = null;
        try {
            super.conectar();
            if (super.verifConexion() == true) {
                PreparedStatement ps = super.con.prepareStatement("select * from usuario;");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Usuario us = new Usuario();
                    us.setNombre(rs.getString("nombre"));
                    us.setClave(rs.getString("clave"));
                    us.setClave(rs.getString("estado"));
                    us.setPermisos(rs.getString("permisos"));
                    ulist.add(us);

                }
                rs.close();
                ps.close();
            } else {
                super.jError();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            super.desconectar();
        }
        return ulist;
    }

    public Usuario verifUser(String nom) throws Exception {
        Usuario u = new Usuario();
        try {
            super.conectar();
            if (super.verifConexion() == true) {
                PreparedStatement ps = super.con.prepareStatement("select * from usuario where nombre=?;");
                ps.setString(1, nom);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    u.setVerif(true);
                    u.setEstado(rs.getString("estado"));
                    u.setPermisos(rs.getString("permisos"));
                    u.setClave(rs.getString("clave"));
                } else {
                    u.setVerif(false);
                }
                rs.close();
                ps.close();
            } else {
                super.jError();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            super.desconectar();
        }
        return u;
    }

    @Override
    public void modEst(String n,String e) throws Exception {
        try {
            super.conectar();
            if (super.verifConexion() == true) {
                PreparedStatement ps = super.con.prepareStatement("update usuario set estado=? where nombre = ?;");
                ps.setString(1, e);
                ps.setString(2, n);
                ps.executeUpdate();
                ps.close();
                System.out.println("Se modifico");
            } else {
                super.jError();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            super.desconectar();
        }
    }
}
