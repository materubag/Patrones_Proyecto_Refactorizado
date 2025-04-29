/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Implementacion;

import Gestor.MySQL;
import Interface.IKardex;
import Modelo.Kardex;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author mateo
 */
public class KardexImpl extends MySQL implements IKardex {

    @Override
    public void crear(Kardex k) throws Exception {
        try {
            super.conectar();
            if (super.verifConexion() == true) {
                PreparedStatement ps = super.con.prepareStatement("insert into kardex(producto,fecha,codigoC,codigoV,costoU,entrada,salida,inventario) values(?,?,?,?,?,?,?,?);");
                ps.setString(1, k.getProducto());
                ps.setString(2, k.getFecha());
                ps.setString(3, k.getCodigoCompra());
                ps.setString(4, k.getCodigoVenta());
                ps.setFloat(5, k.getCostoUnitario());
                ps.setInt(6, k.getEntrada());
                ps.setInt(7, k.getSalida());
                ps.setInt(8, k.getInventario());
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
    public ArrayList<Kardex> listarProducto(String prod) throws Exception {
        ArrayList<Kardex> klist = new ArrayList<Kardex>();
        try {
            super.conectar();
            if (super.verifConexion() == true) {
                PreparedStatement ps = super.con.prepareStatement("select * from kardex where producto=?");
                ps.setString(1, prod);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Kardex k = new Kardex();
                    k.setFecha(rs.getString("fecha"));
                    k.setProducto(rs.getString("producto"));
                    k.setCodigoCompra(rs.getString("codigoC"));
                    k.setCodigoVenta(rs.getString("codigoV"));
                    k.setCodigo(rs.getString("codigo"));
                    k.setCostoUnitario(rs.getFloat("costoU"));
                    k.setEntrada(rs.getInt("entrada"));
                    k.setSalida(rs.getInt("salida"));
                    k.setInventario(rs.getInt("inventario"));
                    klist.add(k);
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
        return klist;
    }

    @Override
    public int inventario(String producto) throws Exception {
        int inventario = 0;
        try {
            super.conectar();
            if (super.verifConexion() == true) {
                PreparedStatement ps = super.con.prepareStatement("SELECT inventario from kardex where producto=? ORDER BY codigo DESC LIMIT 1;");
                ps.setString(1, producto);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    inventario = rs.getInt("inventario");
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
        return inventario;
    }

    public ArrayList<Kardex> listaCompra(String cc) throws Exception {
        ArrayList<Kardex> klist = new ArrayList<Kardex>();
        try {
            super.conectar();
            if (super.verifConexion() == true) {
                PreparedStatement ps = super.con.prepareStatement("select * from kardex where codigoC=?");
                ps.setString(1, cc);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Kardex k = new Kardex();
                    k.setFecha(rs.getString("fecha"));
                    k.setProducto(rs.getString("producto"));
                    k.setCodigoCompra(rs.getString("codigoC"));
                    k.setCodigoVenta(rs.getString("codigoV"));
                    k.setCodigo(rs.getString("codigo"));
                    k.setCostoUnitario(rs.getFloat("costoU"));
                    k.setEntrada(rs.getInt("entrada"));
                    k.setSalida(rs.getInt("salida"));
                    k.setInventario(rs.getInt("inventario"));
                    klist.add(k);
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
        return klist;
    }

    public ArrayList<Kardex> listaVenta(String cv) throws Exception {
        ArrayList<Kardex> klist = new ArrayList<Kardex>();
        try {
            super.conectar();
            if (super.verifConexion() == true) {
                PreparedStatement ps = super.con.prepareStatement("select * from kardex where codigoV=?");
                ps.setString(1, cv);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Kardex k = new Kardex();
                    k.setFecha(rs.getString("fecha"));
                    k.setProducto(rs.getString("producto"));
                    k.setCodigoCompra(rs.getString("codigoC"));
                    k.setCodigoVenta(rs.getString("codigoV"));
                    k.setCodigo(rs.getString("codigo"));
                    k.setCostoUnitario(rs.getFloat("costoU"));
                    k.setEntrada(rs.getInt("entrada"));
                    k.setSalida(rs.getInt("salida"));
                    k.setInventario(rs.getInt("inventario"));
                    klist.add(k);
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
        return klist;
    }

    public boolean ProductoE(String cod) throws Exception {
        boolean verif = false;
        try {
            super.conectar();
            if (super.verifConexion() == true) {
                PreparedStatement ps = super.con.prepareStatement("select * from kardex where producto=?");
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
