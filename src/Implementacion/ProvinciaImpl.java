/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Implementacion;

import Gestor.MySQL;
import Interface.IProvincia;
import Modelo.Provincia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author mateo
 */
public class ProvinciaImpl extends MySQL implements IProvincia {

    @Override
    public void crear(Provincia p) throws Exception {
        try {
            super.conectar();
            if (super.verifConexion() == true) {
                PreparedStatement ps = super.con.prepareStatement("insert into provincia set  nombre=?");
                ps.setString(1, p.getNombre());

                ps.executeUpdate();
                ps.close();
            } else {
                super.jError();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println(e.getMessage());
        } finally {
            super.desconectar();
        }
    }

    @Override
    public void modificar(Provincia p) throws Exception {
        try {
            super.conectar();
            if (super.verifConexion() == true) {
                PreparedStatement ps = super.con.prepareStatement("update provincia set nombre=? where codigo = ?;");
                ps.setString(1, p.getNombre());
                ps.setString(2, p.getCodigo());

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
    public ArrayList<Provincia> listarTodo() throws Exception {
        ArrayList<Provincia> plist = new ArrayList<Provincia>();
        try {
            super.conectar();
            if (super.verifConexion() == true) {
                PreparedStatement ps = super.con.prepareStatement("select * from provincia;");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Provincia pv = new Provincia();
                    pv.setCodigo(rs.getString("codigo"));
                    pv.setNombre(rs.getString("nombre"));
                    plist.add(pv);
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
                PreparedStatement ps = super.con.prepareStatement("delete from provincia where codigo= ?;");
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

    @Override
    public void delCiuAdjunta(String codigo) throws Exception {
        try {
            super.conectar();
            if (super.verifConexion() == true) {
                PreparedStatement ps = super.con.prepareStatement("delete from ciudad where codigoProv= ?;");
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

    @Override
    public boolean provUsada(String cod) throws Exception {
        boolean verif = false;
        try {
            super.conectar();
            if (super.verifConexion() == true) {
                PreparedStatement ps = super.con.prepareStatement("select * from cliente where codigoProv=?");
                ps.setString(1, cod);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    verif = true;
                }
                ps = this.con.prepareStatement("select * from proveedor where codigoProv=?");
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
            if (super.verifConexion() == true) {
                PreparedStatement ps = super.con.prepareStatement("select nombre from provincia where codigo=?;");
                ps.setString(1, c);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    c = (rs.getString("nombre"));
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
