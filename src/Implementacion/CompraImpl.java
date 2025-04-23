/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Implementacion;

import Gestor.MySQL;
import Interface.ICompra;
import Modelo.Compra;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author mateo
 */
public class CompraImpl extends MySQL implements ICompra {

    @Override
    public void crear(Compra c) throws Exception {
        try {
            super.conectar();
            if (super.verifConexion() == true) {
                PreparedStatement ps = super.con.prepareStatement("insert into compra(fecha,proveedor,total) values(?,?,?);");
                ps.setString(1, c.getFecha());
                ps.setString(2, c.getProveedor());
                ps.setFloat(3, 0);
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
    public void modificar(Compra c) throws Exception {
        try {
            super.conectar();
            if (super.verifConexion() == true) {
                PreparedStatement ps = super.con.prepareStatement("update compra set fecha=?,proveedor=?,total=? where codigo =?;");
                ps.setString(1, c.getFecha());
                ps.setString(2, c.getProveedor());
                ps.setFloat(3, c.getTotal());
                ps.setString(4, c.getCodigo());
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
    public ArrayList<Compra> listarTodo() throws Exception {
        ArrayList<Compra> clist = new ArrayList<Compra>();
        try {
            super.conectar();
            if (super.verifConexion() == true) {
                PreparedStatement ps = super.con.prepareStatement("select * from compra");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Compra c = new Compra();
                    c.setFecha(rs.getString("fecha"));
                    c.setProveedor(rs.getString("proveedor"));
                    c.setCodigo(rs.getString("codigo"));
                    c.setTotal(rs.getFloat("total"));
                    clist.add(c);
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
        return clist;
    }

    @Override
    public void borrar(String codigo) throws Exception {
        try {
            super.conectar();
            if (super.verifConexion() == true) {
                PreparedStatement ps = super.con.prepareStatement("delete from compra where codigo= ?;");
                ps.setString(1, codigo);
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

    public Compra compraActual() throws Exception {
        Compra c = new Compra();
        try {
            super.conectar();
            if (super.verifConexion() == true) {
                PreparedStatement ps = super.con.prepareStatement("SELECT * from compra ORDER BY codigo DESC LIMIT 1;");

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    c.setFecha(rs.getString("fecha"));
                    c.setProveedor(rs.getString("proveedor"));
                    c.setCodigo(rs.getString("codigo"));
                    c.setTotal(rs.getFloat("total"));
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
        return c;
    }
}
