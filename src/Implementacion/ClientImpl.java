/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Implementacion;

import Gestor.MySQL;
import Interface.ICliente;
import Modelo.Cliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author mateo
 */
public class ClientImpl extends MySQL implements ICliente {

    @Override
    public void crear(Cliente cl) throws Exception {
        try {
            super.conectar();
            if (super.verifConexion() == true) {
                PreparedStatement ps = super.con.prepareStatement("insert into cliente(cedula,nombre,direccion,codigoProv,codigoCiu) values(?,?,?,?,?);");
                ps.setString(1, cl.getCedula());
                ps.setString(2, cl.getNombre());
                ps.setString(3, cl.getDireccion());
                ps.setString(4, cl.getCodigoProvincia());
                ps.setString(5, cl.getCodigoCiudad());
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
    public void modificar(Cliente cl) throws Exception {
        try {
            super.conectar();
            if (super.verifConexion() == true) {
                PreparedStatement ps = super.con.prepareStatement("update cliente set nombre=?,direccion=?,codigoProv=?,codigoCiu=? where cedula =?;");
                ps.setString(1, cl.getNombre());
                ps.setString(2, cl.getDireccion());
                ps.setString(3, cl.getCodigoProvincia());
                ps.setString(4, cl.getCodigoCiudad());
                ps.setString(5, cl.getCedula());
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
    public ArrayList<Cliente> listarTodo() throws Exception {
        ArrayList<Cliente> clist = new ArrayList<Cliente>();
        try {
            super.conectar();
            if (super.verifConexion() == true) {
                PreparedStatement ps = super.con.prepareStatement("select * from cliente");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Cliente cl = new Cliente();
                    cl.setCedula(rs.getString("cedula"));
                    cl.setNombre(rs.getString("nombre"));
                    cl.setDireccion(rs.getString("direccion"));
                    cl.setCodigoProvincia(rs.getString("codigoProv"));
                    cl.setCodigoCiudad(rs.getString("codigoCiu"));
                    clist.add(cl);
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
    public void borrar(String cedula) throws Exception {
        try {
            super.conectar();
            if (super.verifConexion() == true) {
                PreparedStatement ps = super.con.prepareStatement("delete from cliente where cedula= ?;");
                ps.setString(1, cedula);
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
    public ArrayList<String> ciudades(String cod) throws Exception {
        ArrayList<String> clist = new ArrayList<>();
        try {
            super.conectar();
            if (super.verifConexion() == true) {
                PreparedStatement ps = super.con.prepareStatement("select * from ciudad where codigoProv=?;");
                ps.setString(1, cod);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String cd = (rs.getString("codigo"));
                    String nomb = (rs.getString("nombre"));
                    clist.add(cd + ": " + nomb);
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
    public boolean existe(String cedula) throws Exception {
        boolean verif = false;
        try {
            super.conectar();
            if (super.verifConexion() == true) {
                PreparedStatement ps = super.con.prepareStatement("select nombre from cliente where cedula=?");
                ps.setString(1, cedula);
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

    public String nombre(String cedula) throws Exception {
        String nom = "";
        try {
            super.conectar();
            if (super.verifConexion() == true) {
                PreparedStatement ps = super.con.prepareStatement("select nombre from cliente where cedula=?");
                ps.setString(1, cedula);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    nom = (rs.getString("nombre"));
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
        return nom;
    }
}
