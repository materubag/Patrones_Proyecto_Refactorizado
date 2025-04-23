package Gestor;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author mateo
 */
public class MySQL extends GestorBD {

    //private static final String url = "jdbc:mysql://WINDOWS:3306/proyecto";
    //private static final String driver = "com.mysql.cj.jdbc.Driver";
    //protected static Connection con;

    private static final String url = "jdbc:mysql://localhost/proyecto";
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    protected static Connection con;
    @Override
    public void conectar() {
        this.con = null;
        //super.setUser("root2");
        //super.setClave("1234");

        super.setUser("root");
        super.setClave("");
        try {

            Class.forName(this.driver);
            DriverManager.setLoginTimeout(2);
            this.con = (Connection) DriverManager.getConnection(this.url, super.getUser(), super.getClave());

            if (this.con != null) {
                //System.out.println("conectado");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void desconectar() throws SQLException {
        if (this.con != null) {
            if (!this.con.isClosed()) {
                this.con.close();
                //System.out.println("desconectado");
            }
        }
    }

    public boolean verifConexion() throws SQLException {
        boolean conec = false;
        if (this.con != null) {
            conec = true;
        }
        return conec;
    }

    public void jError() {
        JOptionPane.showMessageDialog(null, "Erro de conexion");
    }

}
