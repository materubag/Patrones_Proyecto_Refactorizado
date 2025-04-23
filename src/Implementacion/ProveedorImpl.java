/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Implementacion;

import Gestor.MySQL;
import Interface.IProveedor;
import Modelo.Proveedor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author mateo
 */
public class ProveedorImpl extends MySQL implements IProveedor {

    @Override
    public void crear(Proveedor pr) throws Exception {
         try {
            super.conectar();
            if (super.verifConexion() == true) {
                PreparedStatement ps =super.con.prepareStatement("insert into proveedor(ruc,nombre,direccion,telefono,codigoProv,codigoCiu) values(?,?,?,?,?,?);");               
                ps.setString(1, pr.getRuc());
                ps.setString(2, pr.getNombre());
                ps.setString(3, pr.getDireccion());
                ps.setString(4, pr.getTelefono());
                ps.setString(5, pr.getCodigoProv());
                ps.setString(6, pr.getCodigoCiu());
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
    public void modificar(Proveedor pr) throws Exception {
         try {
            super.conectar();
            if (super.verifConexion() == true) {
                PreparedStatement ps = super.con.prepareStatement("update proveedor set nombre=?,direccion=?,telefono=?,codigoProv=?,codigoCiu=? where ruc =?;");
                ps.setString(1, pr.getNombre());
                ps.setString(2, pr.getDireccion());
                ps.setString(3, pr.getTelefono());
                ps.setString(4, pr.getCodigoProv());
                ps.setString(5, pr.getCodigoCiu());
                ps.setString(6, pr.getRuc());
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
    public ArrayList<Proveedor> listarTodo() throws Exception {
        ArrayList<Proveedor> prlist = new ArrayList<Proveedor>();
        try {
            super.conectar();
            if (super.verifConexion() == true) {
                PreparedStatement ps = super.con.prepareStatement("select * from proveedor");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Proveedor pr= new Proveedor();
                    pr.setRuc(rs.getString("ruc"));
                    pr.setNombre(rs.getString("nombre"));
                    pr.setDireccion(rs.getString("direccion"));
                    pr.setTelefono(rs.getString("telefono"));
                    pr.setCodigoProv(rs.getString("codigoProv"));
                    pr.setCodigoCiu(rs.getString("codigoCiu"));
                    prlist.add(pr);
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
        return prlist;
    }

    @Override
    public void borrar(String ruc) throws Exception {
        try {
            super.conectar();
            if (super.verifConexion() == true) {
                PreparedStatement ps = super.con.prepareStatement("delete from proveedor where ruc= ?;");
                ps.setString(1, ruc);
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
    public boolean existe(String ruc) throws Exception {
        Boolean verif =false;
        try {
            super.conectar();
            if (super.verifConexion() == true) {
                PreparedStatement ps = super.con.prepareStatement("select * from proveedor where ruc=?");
                ps.setString(1, ruc);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    verif = true;
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
        return verif;
    }
    
    public boolean TieneProducto(String cod) throws Exception {
        boolean verif = false;
        try {
            super.conectar();
            if (super.verifConexion() == true) {
                PreparedStatement ps = super.con.prepareStatement("select * from producto where rucProveedor=?");
                ps.setString(1, cod);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    verif = true;
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
        return verif;
    }
}
