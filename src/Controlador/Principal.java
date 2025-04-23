/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Vista.*;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialDarkerIJTheme;
import javax.swing.UIManager;

/**
 *
 * @author mateo
 */
public class Principal {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatMaterialDarkerIJTheme());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        
        frmMenu m = new frmMenu();
        POpciones op = new POpciones();
        PLogin lg = new PLogin();
        PCliente pcl= new PCliente();
        ControlCliente ccl = new ControlCliente(pcl);
        Pproveedor pve = new Pproveedor();
        ControlProveedor cpve = new ControlProveedor(pve);
        PProductos ppd = new PProductos();
        ControlProducto cpd = new ControlProducto(ppd);
        PanelCiudad pciu=new PanelCiudad();
        ControlCiu cc = new ControlCiu(pciu);
        PanelProvincia pprov = new PanelProvincia();
        ControlProvin cpv = new ControlProvin(pprov);
        PCompraV cv = new PCompraV();
        ControlCV ccv = new ControlCV(cv);
        PKardex pk = new PKardex();
        ControlKardex ck = new ControlKardex(pk);
        PFacturas ft = new PFacturas();
        ControlFact cf = new ControlFact(ft);
        PUsuario pu = new PUsuario();
        ControlUs cus = new ControlUs(pu);
        Control ct = new Control(m,pciu,op,lg,pprov,pcl,pve,ppd,cv,pk,ft,pu);
        
        ct.iniciar();
        m.setVisible(true);
        ct.verPrincipal(lg);
    }
}
