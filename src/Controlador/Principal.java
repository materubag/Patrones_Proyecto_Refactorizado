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
        
        FormMenu m = new FormMenu();
        PanelOpciones op = new PanelOpciones();
        PanelLogin lg = new PanelLogin();
        PanelCliente pcl= new PanelCliente();
        ControlCliente ccl = new ControlCliente(pcl);
        Panelproveedor pve = new Panelproveedor();
        ControlProveedor cpve = new ControlProveedor(pve);
        PanelProductos ppd = new PanelProductos();
        ControlProducto cpd = new ControlProducto(ppd);
        PanelCiudad pciu=new PanelCiudad();
        ControlCiu cc = new ControlCiu(pciu);
        PanelProvincia pprov = new PanelProvincia();
        ControlProvin cpv = new ControlProvin(pprov);
        PanelCompraVenta cv = new PanelCompraVenta();
        ControlCV ccv = new ControlCV(cv);
        PanelKardex pk = new PanelKardex();
        ControlKardex ck = new ControlKardex(pk);
        PanelFacturas ft = new PanelFacturas();
        ControlFact cf = new ControlFact(ft);
        PanelUsuario pu = new PanelUsuario();
        ControlUs cus = new ControlUs(pu);
        Control ct = new Control(m,pciu,op,lg,pprov,pcl,pve,ppd,cv,pk,ft,pu);
        
        ct.iniciar();
        m.setVisible(true);
        ct.verPrincipal(lg);
    }
}
