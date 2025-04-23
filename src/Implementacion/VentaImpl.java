/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Implementacion;

import Gestor.MySQL;
import Interface.IVenta;
import Modelo.Venta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author mateo
 */
public class VentaImpl extends MySQL implements IVenta {

    @Override
    public void crear(Venta v) throws Exception {
        try {
            super.conectar();
            if (super.verifConexion() == true) {
                PreparedStatement ps = super.con.prepareStatement("insert into venta(fecha,cliente,total) values(?,?,?);");
                ps.setString(1, v.getFecha());
                ps.setString(2, v.getCliente());
                ps.setFloat(3, 0);
                ps.executeUpdate();
                ps.close();
            } else {
                this.jError();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            this.desconectar();
        }
    }

    @Override
    public ArrayList<Venta> listarTodo() throws Exception {
        ArrayList<Venta> vlist = new ArrayList<Venta>();
        try {
            super.conectar();
            if (super.verifConexion() == true) {
                PreparedStatement ps = super.con.prepareStatement("select * from venta");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Venta v = new Venta();
                    v.setFecha(rs.getString("fecha"));
                    v.setCliente(rs.getString("cliente"));
                    v.setCodigo(rs.getString("codigo"));
                    v.setTotal(rs.getFloat("total"));
                    vlist.add(v);
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
        return vlist;
    }

    @Override
    public void modificar(Venta v) throws Exception {
        try {
            super.conectar();
            if (super.verifConexion() == true) {
                PreparedStatement ps =super.con.prepareStatement("update venta set fecha=?,cliente=?,total=? where codigo =?;");
                ps.setString(1, v.getFecha());
                ps.setString(2, v.getCliente());
                ps.setFloat(3, v.getTotal());
                ps.setString(4, v.getCodigo());
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
    public void borrar(String cod) throws Exception {
        try {
            super.conectar();
            if (super.verifConexion() == true) {
                PreparedStatement ps = super.con.prepareStatement("delete from venta where codigo= ?;");
                ps.setString(1, cod);
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
    public Venta ventaActual() throws Exception {
        Venta v = new Venta();
        try {
            super.conectar();
            if (super.verifConexion() == true) {
                PreparedStatement ps = super.con.prepareStatement("SELECT * from venta ORDER BY codigo DESC LIMIT 1;");
                
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    v.setFecha(rs.getString("fecha"));
                    v.setCliente(rs.getString("cliente"));
                    v.setCodigo(rs.getString("codigo"));
                    v.setTotal(rs.getFloat("total"));
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
        return v;
    }
}
