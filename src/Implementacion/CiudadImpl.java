/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Implementacion;

import Gestor.MySQL;
import Interface.ICiudad;
import Modelo.Ciudad;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author mateo
 */
public class CiudadImpl extends MySQL implements ICiudad {

    @Override
    public void crear(Ciudad c) throws Exception {
        try {
            super.conectar();
            if (super.verifConexion() == true) {
                PreparedStatement ps = super.con.prepareStatement("insert into ciudad(nombre,codigoProv) values(?,?);");
                ps.setString(1, c.getNombre());
                ps.setString(2, c.getCodigoProvincia());
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
    public void modificar(Ciudad c) throws Exception {
        try {
            super.conectar();
            if (super.verifConexion() == true) {
                PreparedStatement ps = super.con.prepareStatement("update ciudad set nombre=?,codigoProv=? where codigo =?;");
                ps.setString(1, c.getNombre());
                ps.setString(2, c.getCodigoProvincia());
                ps.setString(3, c.getCodigo());
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
    public ArrayList<Ciudad> listarTodo() throws Exception {
        ArrayList<Ciudad> clist = new ArrayList<Ciudad>();
        try {
            super.conectar();
            if (super.verifConexion() == true) {
                PreparedStatement ps = super.con.prepareStatement("select * from ciudad");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Ciudad ciu = new Ciudad();
                    ciu.setNombre(rs.getString("nombre"));
                    ciu.setCodigo(rs.getString("codigo"));
                    ciu.setCodigoProvincia(rs.getString("codigoProv"));
                    clist.add(ciu);
                }
                for (int i = 0; i < clist.size(); i++) {
                    this.provinciaAdjunta(clist.get(i));
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
                PreparedStatement ps = super.con.prepareStatement("delete from ciudad where codigo= ?;");
                ps.setString(1, codigo);
                ps.executeUpdate();
                ps.close();
            } else {
                this.jError();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            super.desconectar();
        }
    }

    @Override
    public void provinciaAdjunta(Ciudad c) throws Exception {
        try {
            super.conectar();
            if (super.verifConexion() == true) {
                PreparedStatement ps = super.con.prepareStatement("SELECT nombre FROM provincia WHERE codigo =?;");
                ps.setString(1, c.getCodigoProvincia());
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    c.setNombreProvincia(rs.getString("nombre"));
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
    }

    @Override
    public ArrayList<String> provinciaExistente() throws Exception {
        ArrayList<String> plist = new ArrayList<>();
        try {
            super.conectar();
            if (super.verifConexion() == true) {
                PreparedStatement ps = super.con.prepareStatement("select * from provincia;");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String cd = (rs.getString("codigo"));
                    String nomb = (rs.getString("nombre"));
                    plist.add(cd + ": " + nomb);
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
    public boolean ciudadUsada(String cod) throws Exception {
        boolean verif = false;
        try {
            super.conectar();
            if (super.verifConexion() == true) {
                PreparedStatement ps = super.con.prepareStatement("select * from cliente where codigoCiu=?");
                ps.setString(1, cod);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    verif = true;
                }
                ps = this.con.prepareStatement("select * from proveedor where codigoCiu=?");
                ps.setString(1, cod);
                rs = ps.executeQuery();
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
    public String nombre(String c) throws Exception {
        try {
            super.conectar();
            if(super.verifConexion()==true){
            PreparedStatement ps = super.con.prepareStatement("select nombre from ciudad where codigo=?;");
            ps.setString(1, c);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                c=rs.getString("nombre");
            }
            rs.close();
            ps.close();
            }else super.jError();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            super.desconectar();
        }
        return c;
    }
}
