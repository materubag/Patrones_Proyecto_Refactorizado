/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Implementacion.CiudadImpl;
import Implementacion.ClientImpl;
import Implementacion.ProductImpl;
import Implementacion.ProveedorImpl;
import Implementacion.ProvinciaImpl;
import Implementacion.UserImpl;
import Interface.*;
import Modelo.Ciudad;
import Modelo.Cliente;
import Modelo.Producto;
import Modelo.Proveedor;
import Modelo.Provincia;
import Modelo.Usuario;
import Recursos.Encriptar;
import Recursos.Permisos;
import Vista.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mateo
 */
public class Control implements ActionListener {

    private String permisos;
    frmMenu formularioMenu = new frmMenu();
    POpciones panelOpciones = new POpciones();
    PLogin panelLogin = new PLogin();
    PanelProvincia panelProvincia = new PanelProvincia();
    PanelCiudad panelCiudad = new PanelCiudad();
    PCliente panelCliente = new PCliente();
    Pproveedor panelProveedor = new Pproveedor();
    PProductos panelProductos = new PProductos();
    PCompraV panelCompraVenta = new PCompraV();
    PKardex panelKardex = new PKardex();
    PFacturas panelFacturas = new PFacturas();
    PUsuario panelUsuario = new PUsuario();

    public Control(frmMenu m, PanelCiudad pciu, POpciones op, PLogin lg, PanelProvincia pprov,
            PCliente pcl, Pproveedor pve, PProductos ppd, PCompraV cv, PKardex pk, PFacturas ft, PUsuario pu) {
        this.panelLogin = lg;
        this.formularioMenu = m;
        this.panelOpciones = op;
        this.panelProvincia = pprov;
        this.panelCliente = pcl;
        this.panelProveedor = pve;
        this.panelProductos = ppd;
        this.panelCiudad = pciu;
        this.panelCompraVenta = cv;
        this.panelKardex = pk;
        this.panelFacturas = ft;
        this.panelUsuario = pu;
        this.panelLogin.jButtonLogin.addActionListener(this);
        this.panelOpciones.jButtonClient.addActionListener(this);
        this.panelOpciones.jButtonProve.addActionListener(this);
        this.panelOpciones.jButtonProd.addActionListener(this);
        this.panelOpciones.jButtonCompraV.addActionListener(this);
        this.panelOpciones.jButtonKardex.addActionListener(this);
        this.panelOpciones.jButtonVerFactura.addActionListener(this);
        this.formularioMenu.jMenuHome.addActionListener(this);
        this.formularioMenu.jMenuItemProv.addActionListener(this);
        this.formularioMenu.jMenuItemCSesion.addActionListener(this);
        this.formularioMenu.jMenuItemCiu.addActionListener(this);
        this.formularioMenu.jMenuItemProv.addActionListener(this);
        this.formularioMenu.jMenuItemUser.addActionListener(this);
        this.panelLogin.jLabelMError.setVisible(false);
    }

    public String getPermisos() {
        return permisos;
    }

    public void setPermisos(String permisos) {
        this.permisos = permisos;
    }

    public void iniciar() {
        this.formularioMenu.setTitle("");
        this.formularioMenu.setLocationRelativeTo(null);
        this.formularioMenu.jMenuMe.setVisible(false);
        this.formularioMenu.PanelPrincipal.setSize(1000, 720);
        this.formularioMenu.PanelPrincipal.revalidate();
        this.formularioMenu.PanelPrincipal.repaint();

    }

    public void verPrincipal(JPanel p) {
        p.setSize(1000, 720);
        p.setLocation(0, 0);
        this.formularioMenu.PanelPrincipal.removeAll();
        this.formularioMenu.PanelPrincipal.add(p, BorderLayout.CENTER);
        this.formularioMenu.PanelPrincipal.revalidate();
        this.formularioMenu.PanelPrincipal.repaint();
    }
    private int cont = 0;

    @Override
    public void actionPerformed(ActionEvent ev) {
        //--------------Login--------------------------------------------------------------------------------
        if ((ev.getSource() == this.panelLogin.jButtonLogin) && !((this.panelLogin.jTextFieldNom.getText().isEmpty()
                || String.valueOf(this.panelLogin.jPasswordField1.getPassword()).isEmpty())
                || (this.panelLogin.jTextFieldNom.getText().equals("Ingrese su nombre de usuario")
                || String.valueOf(this.panelLogin.jPasswordField1.getPassword()).equals("               ")))) {
            try {
                IUsuario ui = new UserImpl();
                Usuario u = new Usuario();
                Encriptar enc = new Encriptar();
                u.setNombre(this.panelLogin.jTextFieldNom.getText().trim());
                //u.setClave(enc.encriptar(this.panelLogin.jPasswordField1.getText().trim()));
                u = ui.verifUser(u.getNombre());
                this.panelLogin.jTextFieldNom.getKeyListeners();
                boolean c = this.panelLogin.isCambio();
                if (c == true) {
                    this.cont = 0;
                    this.panelLogin.setCambio(false);
                }
                if (u.isVerif() && !u.getEstado().equals("bloqueado")
                        && enc.encriptar(this.panelLogin.jPasswordField1.getText().trim()).equals(u.getClave())) {

                    Permisos per = new Permisos();
                    ArrayList<Boolean> bl = per.Leer(u.getPermisos());
                    this.formularioMenu.jMenuItemProv.setVisible(bl.get(0));
                    this.formularioMenu.jMenuItemCiu.setVisible(bl.get(1));
                    this.formularioMenu.jMenuItemUser.setVisible(bl.get(2));
                    this.panelOpciones.jButtonClient.setVisible(bl.get(3));
                    this.panelOpciones.jButtonProd.setVisible(bl.get(4));
                    this.panelOpciones.jButtonProve.setVisible(bl.get(5));
                    this.panelOpciones.jButtonVerFactura.setVisible(bl.get(6));
                    this.panelOpciones.jButtonKardex.setVisible(bl.get(7));
                    this.panelOpciones.jButtonCompraV.setVisible(bl.get(8));
                    this.verPrincipal(this.panelOpciones);
                    this.formularioMenu.jMenuMe.setVisible(true);
                    this.cont = 0;
                    this.setPermisos(u.getPermisos());

                } else if (u.isVerif()) {
                    System.out.println("sdf");
                    if (u.getEstado().equals("bloqueado")) {
                        JOptionPane.showMessageDialog(this.panelLogin, "Usuario bloqueado");
                    } else if (this.cont == 2) {
                        IUsuario ui2 = new UserImpl();
                        ui2.modEst(u.getNombre(), "bloqueado");
                        JOptionPane.showMessageDialog(this.panelLogin, "Su usuario ha sido bloqueado");
                    } else {
                        this.cont++;
                        this.panelLogin.jLabelMError.setVisible(true);
                        JOptionPane.showMessageDialog(this.panelLogin, "le quedan " + (3 - this.cont) + " intentos");
                    }
                } else {
                    this.panelLogin.jLabelMError.setVisible(true);
                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                System.out.println("ME" + ex.toString());
            }

        } else {
            this.panelLogin.jLabelMError.setVisible(true);
        }

        /* if ((ev.getSource() == this.panelLogin.jButtonLogin)) {
            this.verPrincipal(this.panelOpciones);
            this.formularioMenu.jMenuMe.setVisible(true);
        }*/
        //---------------Productos---------------------------------------------------------------------------
        if (ev.getSource()
                == this.panelOpciones.jButtonProd) {
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            for (int i = 0; i < this.panelProductos.jTable1.getColumnModel().getColumnCount(); i++) {
                this.panelProductos.jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            this.verPrincipal(this.panelProductos);
            this.panelProductos.jButtonQR.setVisible(false);
            this.panelProductos.jButtonAccion.setVisible(false);
            this.panelProductos.jComboBoxCod.setVisible(false);
            this.panelProductos.jComboBoxRuc.setVisible(false);
            this.panelProductos.jLabelCod.setVisible(false);
            this.panelProductos.jLabelMError.setVisible(false);
            this.panelProductos.jLabelNom.setVisible(false);
            this.panelProductos.jLabelRuc.setVisible(false);
            this.panelProductos.jLabelQr.setVisible(false);
            this.panelProductos.jTextFieldPnombre.setVisible(false);
            DefaultTableModel modelo = (DefaultTableModel) this.panelProductos.jTable1.getModel();
            modelo.setRowCount(0);
            this.panelProductos.jComboBoxRuc.removeAllItems();
            try {
                IProducto pdimpl = new ProductImpl();
                ArrayList<Producto> pdlist = pdimpl.listarTodo();
                for (int i = 0; i < pdlist.size(); i++) {
                    modelo.addRow(new Object[]{pdlist.get(i).getCodigo(), pdlist.get(i).getNombre(), pdlist.get(i).getRucProveedo()});
                    this.panelProductos.jComboBoxCod.addItem(pdlist.get(i).getCodigo() + ": " + pdlist.get(i).getNombre());
                }
                IProveedor pveImpl = new ProveedorImpl();
                ArrayList<Proveedor> pveL = pveImpl.listarTodo();
                if (pveL.isEmpty() && this.getPermisos().substring(5, 6).equals("1")) {
                    String Jop[] = {"Aceptar", "Cancalar"};
                    int a = JOptionPane.showOptionDialog(this.panelProductos, "No se ha ingresado proveedores\n¿Desea ingresar uno?", "",
                            0, JOptionPane.QUESTION_MESSAGE, null, Jop, "Cancelar");
                    if (Jop[a].equals("Aceptar")) {
                        this.panelOpciones.jButtonProve.doClick();
                    }
                } else {
                    for (int i = 0; i < pveL.size(); i++) {
                        this.panelProductos.jComboBoxRuc.addItem(pveL.get(i).getRuc());
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

//---------------------kardex----------------------------------------------
        if (ev.getSource()
                == this.panelOpciones.jButtonKardex) {
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            for (int i = 0; i < this.panelKardex.jTable1.getColumnModel().getColumnCount(); i++) {
                this.panelKardex.jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            this.verPrincipal(this.panelKardex);
            this.panelKardex.jComboBoxPd.removeAllItems();
            this.panelKardex.jComboBoxPd.addItem("Productos");
            try {
                IProducto pdimpl = new ProductImpl();
                ArrayList<Producto> pdlist = pdimpl.listarTodo();

                if (pdlist.isEmpty() && this.getPermisos().substring(7, 8).equals("1")) {
                    String Jop[] = {"Aceptar", "Cancalar"};
                    int a = JOptionPane.showOptionDialog(this.panelKardex, "No se ha ingresado productos\n¿Desea ingresar uno?",
                            "", 0, JOptionPane.QUESTION_MESSAGE, null, Jop, "Cancelar");
                    if (Jop[a].equals("Aceptar")) {
                        this.panelOpciones.jButtonProd.doClick();
                    }
                } else {
                    for (int i = 0; i < pdlist.size(); i++) {
                        this.panelKardex.jComboBoxPd.addItem(pdlist.get(i).getCodigo() + ": " + pdlist.get(i).getNombre());
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
//--------------proveedor---------------------------------------------------------------------

        if (ev.getSource()
                == this.panelOpciones.jButtonProve) {
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            for (int i = 0; i < this.panelProveedor.jTable1.getColumnModel().getColumnCount(); i++) {
                this.panelProveedor.jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            this.verPrincipal(this.panelProveedor);
            this.panelProveedor.ButtonAccion.setVisible(false);
            this.panelProveedor.jComboBoxCiu.setVisible(false);
            this.panelProveedor.jComboBoxProv.setVisible(false);
            this.panelProveedor.jComboBoxRuc.setVisible(false);
            this.panelProveedor.jLabelCiu.setVisible(false);
            this.panelProveedor.jLabelDirec.setVisible(false);
            this.panelProveedor.jLabelEruc.setVisible(false);
            this.panelProveedor.jLabelEdirec.setVisible(false);
            this.panelProveedor.jLabelEnom.setVisible(false);
            this.panelProveedor.jLabelEtl.setVisible(false);
            this.panelProveedor.jLabelNom.setVisible(false);
            this.panelProveedor.jLabelProv.setVisible(false);
            this.panelProveedor.jLabelRuc.setVisible(false);
            this.panelProveedor.jLabelTl.setVisible(false);
            this.panelProveedor.jSeparatorAc.setVisible(false);
            this.panelProveedor.jTextFieldRuc.setVisible(false);
            this.panelProveedor.jTextFieldDirec.setVisible(false);
            this.panelProveedor.jTextFieldNom.setVisible(false);
            this.panelProveedor.jTextFieldTl.setVisible(false);
            DefaultTableModel modelo = (DefaultTableModel) this.panelProveedor.jTable1.getModel();
            modelo.setRowCount(0);
            this.panelProveedor.jComboBoxRuc.removeAllItems();
            this.panelProveedor.jComboBoxRuc.addItem("Proveedores");
            try {
                IProveedor pveImpl = new ProveedorImpl();
                ArrayList<Proveedor> pveL = pveImpl.listarTodo();
                CiudadImpl ci = new CiudadImpl();
                ProvinciaImpl pi = new ProvinciaImpl();
                for (int i = 0; i < pveL.size(); i++) {
                    pveL.get(i).setCodigoCiu(pveL.get(i).getCodigoCiu() + ": " + ci.nombre(pveL.get(i).getCodigoCiu()));
                    pveL.get(i).setCodigoProv(pveL.get(i).getCodigoProv() + ": " + pi.nombre(pveL.get(i).getCodigoProv()));
                    modelo.addRow(new Object[]{pveL.get(i).getRuc(), pveL.get(i).getNombre(), pveL.get(i).getDireccion(),
                        pveL.get(i).getTelefono(), pveL.get(i).getCodigoProv(), pveL.get(i).getCodigoCiu()});
                    this.panelProveedor.jComboBoxRuc.addItem(pveL.get(i).getRuc());
                }
                this.panelProveedor.jComboBoxProv.removeAllItems();
                ICiudad iciu = new CiudadImpl();
                ArrayList<String> cl = iciu.provExistente();
                if (cl.isEmpty() && this.getPermisos().substring(0, 1).equals("1")) {
                    String Jop[] = {"Aceptar", "Cancalar"};
                    int a = JOptionPane.showOptionDialog(this.panelProductos, "No se ha ingresado provincias\n¿Desea ingresar uno?", "",
                            0, JOptionPane.QUESTION_MESSAGE, null, Jop, "Cancelar");
                    if (Jop[a].equals("Aceptar")) {
                        this.formularioMenu.jMenuItemProv.doClick();
                    }
                } else {
                    for (int i = 0; i < cl.size(); i++) {
                        this.panelProveedor.jComboBoxProv.addItem(cl.get(i));
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
//-----------Cliente-----------------------------------------------------------

        if (ev.getSource()
                == this.panelOpciones.jButtonClient) {
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            for (int i = 0; i < this.panelCliente.jTable1.getColumnModel().getColumnCount(); i++) {
                this.panelCliente.jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            this.verPrincipal(this.panelCliente);
            this.panelCliente.ButtonAccion.setVisible(false);
            this.panelCliente.jComboBoxCiu.setVisible(false);
            this.panelCliente.jComboBoxProv.setVisible(false);
            this.panelCliente.jComboBoxCed.setVisible(false);
            this.panelCliente.jLabelCed.setVisible(false);
            this.panelCliente.jLabelCiu.setVisible(false);
            this.panelCliente.jLabelDirec.setVisible(false);
            this.panelCliente.jLabelEced.setVisible(false);
            this.panelCliente.jLabelEdirec.setVisible(false);
            this.panelCliente.jLabelEnom.setVisible(false);
            this.panelCliente.jLabelNom.setVisible(false);
            this.panelCliente.jLabelProv.setVisible(false);
            this.panelCliente.jSeparatorAc.setVisible(false);
            this.panelCliente.jTextFieldCed.setVisible(false);
            this.panelCliente.jTextFieldDirec.setVisible(false);
            this.panelCliente.jTextFieldNom.setVisible(false);
            DefaultTableModel modelo = (DefaultTableModel) this.panelCliente.jTable1.getModel();
            modelo.setRowCount(0);
            this.panelCliente.jComboBoxCed.removeAllItems();
            this.panelCliente.jComboBoxCed.addItem("Clientes");
            try {
                ICliente icl = new ClientImpl();
                CiudadImpl ci = new CiudadImpl();
                ProvinciaImpl pi = new ProvinciaImpl();
                ArrayList<Cliente> clist = icl.listarTodo();
                for (int i = 0; i < clist.size(); i++) {
                    clist.get(i).setCodigoCiu(clist.get(i).getCodigoCiu() + ": " + ci.nombre(clist.get(i).getCodigoCiu()));
                    clist.get(i).setCodigoProv(clist.get(i).getCodigoProv() + ": " + pi.nombre(clist.get(i).getCodigoProv()));
                    modelo.addRow(new Object[]{clist.get(i).getCedula(), clist.get(i).getNombre(), clist.get(i).getDireccion(),
                        clist.get(i).getCodigoProv(), clist.get(i).getCodigoCiu()});
                    this.panelCliente.jComboBoxCed.addItem(clist.get(i).getCedula());
                }
                ICiudad iciu = new CiudadImpl();
                this.panelCliente.jComboBoxProv.removeAllItems();
                ArrayList<String> clis = iciu.provExistente();
                if (clis.isEmpty() && this.getPermisos().substring(0, 1).equals("1")) {
                    String Jop[] = {"Aceptar", "Cancalar"};
                    int a = JOptionPane.showOptionDialog(this.panelProductos, "No se ha ingresado provincias\n¿Desea ingresar uno?",
                            "", 0, JOptionPane.QUESTION_MESSAGE, null, Jop, "Cancelar");
                    if (Jop[a].equals("Aceptar")) {
                        this.formularioMenu.jMenuItemProv.doClick();
                    }
                } else {
                    for (int i = 0; i < clis.size(); i++) {
                        this.panelCliente.jComboBoxProv.addItem(clis.get(i));
                    }
                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        }

//--------provincias ---------------------------------------------------------//    
        if (ev.getSource() == this.formularioMenu.jMenuItemProv) {
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            for (int i = 0; i < this.panelProvincia.jTable1.getColumnModel().getColumnCount(); i++) {
                this.panelProvincia.jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            this.verPrincipal(this.panelProvincia);
            this.panelProvincia.jLabelMError.setVisible(false);
            this.panelProvincia.jButtonAccion.setVisible(false);
            this.panelProvincia.jLabelP1.setVisible(false);
            this.panelProvincia.jComboBoxP.setVisible(false);
            this.panelProvincia.jLabelP2.setVisible(false);
            this.panelProvincia.jTextFieldPnombre.setVisible(false);
            DefaultTableModel modelo = (DefaultTableModel) this.panelProvincia.jTable1.getModel();
            modelo.setRowCount(0);
            this.panelProvincia.jComboBoxP.removeAllItems();
            this.panelProvincia.jComboBoxP.addItem("Provincias");
            try {
                ProvinciaImpl iprov = new ProvinciaImpl();
                ArrayList<Provincia> plist = iprov.listarTodo();
                for (int i = 0; i < plist.size(); i++) {
                    modelo.addRow(new Object[]{plist.get(i).getCodigo(), plist.get(i).getNombre()});
                    this.panelProvincia.jComboBoxP.addItem(plist.get(i).getCodigo() + ": " + plist.get(i).getNombre());
                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

//-------------ciudades---------------------------------------------------------------//
        if (ev.getSource()
                == this.formularioMenu.jMenuItemCiu) {
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            for (int i = 0; i < this.panelCiudad.jTableC.getColumnModel().getColumnCount(); i++) {
                this.panelCiudad.jTableC.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            this.verPrincipal(this.panelCiudad);
            this.panelCiudad.jButtonAccion.setVisible(false);
            this.panelCiudad.jLabelC1.setVisible(false);
            this.panelCiudad.jComboBoxC.setVisible(false);
            this.panelCiudad.jLabelC2.setVisible(false);
            this.panelCiudad.jTextFieldNom.setVisible(false);
            this.panelCiudad.jLabelC3.setVisible(false);
            this.panelCiudad.jComboBoxP.setVisible(false);
            DefaultTableModel modelo = (DefaultTableModel) this.panelCiudad.jTableC.getModel();
            modelo.setRowCount(0);
            this.panelCiudad.jComboBoxC.removeAllItems();
            this.panelCiudad.jComboBoxC.addItem("Ciudades");
            this.panelCiudad.jComboBoxP.removeAllItems();
            this.panelCiudad.jComboBoxP.addItem("Provincias");
            try {
                ICiudad iciu = new CiudadImpl();
                ArrayList<Ciudad> clist = iciu.listarTodo();
                for (int i = 0; i < clist.size(); i++) {
                    modelo.addRow(new Object[]{clist.get(i).getCodigo(), clist.get(i).getNombre(),
                        clist.get(i).getCodigoProv(), clist.get(i).getNombreProv()});
                    this.panelCiudad.jComboBoxC.addItem(clist.get(i).getCodigo() + ": " + clist.get(i).getNombre());
                }
                ArrayList<String> pl = iciu.provExistente();
                if (pl.isEmpty() && this.getPermisos().substring(0, 1).equals("1")) {
                    String Jop[] = {"Aceptar", "Cancalar"};
                    int a = JOptionPane.showOptionDialog(this.panelProductos, "No se ha ingresado provincias\n¿Desea ingresar uno?",
                            "", 0, JOptionPane.QUESTION_MESSAGE, null, Jop, "Cancelar");
                    if (Jop[a].equals("Aceptar")) {
                        this.formularioMenu.jMenuItemProv.doClick();
                    }
                } else {
                    for (int i = 0; i < pl.size(); i++) {
                        this.panelCiudad.jComboBoxP.addItem(pl.get(i));
                    }
                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        }
//-------------------------cerrar sesion--------------------------------------------------

        if (ev.getSource()
                == this.formularioMenu.jMenuItemCSesion) {
            this.verPrincipal(this.panelLogin);
            this.panelLogin.jTextFieldNom.setText("Ingrese su nombre de usuario");
            this.panelLogin.jPasswordField1.setText("               ");
            this.formularioMenu.jMenuMe.setVisible(false);
            this.panelLogin.jLabelMError.setVisible(false);
        }
//----------Opciones-----------------------------------------------

        if (ev.getSource()
                == this.formularioMenu.jMenuHome) {
            this.verPrincipal(this.panelOpciones);
        }
//----------compra y venta ----------------------------------------

        if (ev.getSource()
                == this.panelOpciones.jButtonCompraV) {
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            for (int i = 0; i < this.panelCompraVenta.jTable.getColumnModel().getColumnCount(); i++) {
                this.panelCompraVenta.jTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            this.verPrincipal(this.panelCompraVenta);
            this.panelCompraVenta.jLabelProvClient.setVisible(false);
            this.panelCompraVenta.jComboBoxSelectPC.setVisible(false);
            this.panelCompraVenta.jLabelFecha.setVisible(false);
            this.panelCompraVenta.jDateChooser1.setVisible(false);
            this.panelCompraVenta.jButtonSelectCV.setVisible(false);
            this.panelCompraVenta.jButtonAddCV.setVisible(false);
            this.panelCompraVenta.jButtonQR.setVisible(false);
            this.panelCompraVenta.jComboBoxProducto.setVisible(false);
            this.panelCompraVenta.jLabelCant.setVisible(false);
            this.panelCompraVenta.jLabelErrorCant.setVisible(false);
            this.panelCompraVenta.jLabelErrorPrecio.setVisible(false);
            this.panelCompraVenta.jLabelErrorFecha.setVisible(false);
            this.panelCompraVenta.jLabelErroPvCl.setVisible(false);
            this.panelCompraVenta.jLabelProd.setVisible(false);
            this.panelCompraVenta.jLabelprecio.setVisible(false);
            this.panelCompraVenta.jLabelTotal.setVisible(false);
            this.panelCompraVenta.jTable.setVisible(false);
            this.panelCompraVenta.jSpinnerInv.setVisible(false);
            this.panelCompraVenta.jLabel$.setVisible(false);
            this.panelCompraVenta.jTextFieldPrecio.setVisible(false);
            DefaultTableModel modelo = (DefaultTableModel) this.panelCompraVenta.jTable.getModel();
            modelo.setRowCount(0);
        }

//-------------Factura-----------------------------------------------------------------------
        if (ev.getSource()
                == this.panelOpciones.jButtonVerFactura) {
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            for (int i = 0; i < this.panelFacturas.jTable.getColumnModel().getColumnCount(); i++) {
                this.panelFacturas.jTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            this.verPrincipal(panelFacturas);
            this.panelFacturas.jButtonBorrar.setVisible(false);
            this.panelFacturas.jComboBoxFact.setVisible(false);
            this.panelFacturas.jLabelSelect.setVisible(false);
            this.panelFacturas.jLabelError.setVisible(false);
        }
//--------------usuario----------------------------------------------------------------------

        if (ev.getSource()
                == this.formularioMenu.jMenuItemUser) {
            this.verPrincipal(panelUsuario);
            this.panelUsuario.jLabelMErrorUser.setVisible(false);
            this.panelUsuario.jLabelMErrorclave.setVisible(false);
            this.panelUsuario.jLabelMErrorPer.setVisible(false);
            this.panelUsuario.jLabelMErrorclave1.setVisible(false);
        }
    }
}
