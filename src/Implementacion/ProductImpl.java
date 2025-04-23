/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Implementacion;

import Gestor.MySQL;
import Interface.IProducto;
import Modelo.Producto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author mateo
 */
public class ProductImpl extends MySQL implements IProducto {

    @Override
   public void crear(Producto pd) throws Exception {
    try {
        super.conectar();
        if (super.verifConexion() == true) {
            PreparedStatement ps = super.con.prepareStatement("insert into producto(nombre,rucProveedor) values(?,?);");
            ps.setString(1, pd.getNombre());
            ps.setString(2, pd.getRucProveedo());
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
public void modificar(Producto pd) throws Exception {
    try {
        super.conectar();
        if (super.verifConexion() == true) {
            PreparedStatement ps = super.con.prepareStatement("update producto set nombre=?,rucProveedor=? where codigo = ?;");
            ps.setString(1, pd.getNombre());
            ps.setString(2, pd.getRucProveedo());
            ps.setString(3, pd.getCodigo());

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
public ArrayList<Producto> listarTodo() throws Exception {
    ArrayList<Producto> plist = new ArrayList<Producto>();
    try {
        super.conectar();
        if (super.verifConexion() == true) {
            PreparedStatement ps = super.con.prepareStatement("select * from producto");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Producto pd = new Producto();
                pd.setNombre(rs.getString("nombre"));
                pd.setCodigo(rs.getString("codigo"));
                pd.setRucProveedo(rs.getString("rucProveedor"));
                plist.add(pd);
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
    return plist;
}

@Override
public void borrar(String codigo) throws Exception {
    try {
        super.conectar();
        if (super.verifConexion() == true) {
            PreparedStatement ps = super.con.prepareStatement("delete from producto where codigo= ?;");
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

public ArrayList<String> listarRucP(String ruc) throws Exception {
    ArrayList<String> prlist = new ArrayList<String>();
    try {
        super.conectar();
        if (super.verifConexion() == true) {
            PreparedStatement ps = super.con.prepareStatement("select * from producto where rucProveedor=?");
            ps.setString(1, ruc);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                prlist.add(rs.getString("codigo") + ": " + rs.getString("nombre"));
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

public Producto QrP(String cod) throws Exception {
    Producto p = new Producto();
    try {
        super.conectar();
        if (super.verifConexion() == true) {
            PreparedStatement ps = super.con.prepareStatement("select * from producto where codigo=?");
            ps.setString(1, cod);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                p.setCodigo(cod);
                p.setNombre(rs.getString("nombre"));
                p.setRucProveedo(rs.getString("rucProveedor"));
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
    return p;
}
}
