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
    FormMenu m = new FormMenu();
    PanelOpciones op = new PanelOpciones();
    PanelLogin lg = new PanelLogin();
    PanelProvincia pprov = new PanelProvincia();
    PanelCiudad pciu = new PanelCiudad();
    PanelCliente pcl = new PanelCliente();
    Panelproveedor ppee = new Panelproveedor();
    PanelProductos ppd = new PanelProductos();
    PanelCompraVenta cv = new PanelCompraVenta();
    PanelKardex pk = new PanelKardex();
    PanelFacturas ft = new PanelFacturas();
    PanelUsuario pu = new PanelUsuario();

    public Control(FormMenu m, PanelCiudad pciu, PanelOpciones op, PanelLogin lg, PanelProvincia pprov,
            PanelCliente pcl, Panelproveedor pve, PanelProductos ppd, PanelCompraVenta cv, PanelKardex pk, PanelFacturas ft, PanelUsuario pu) {
        this.lg = lg;
        this.m = m;
        this.op = op;
        this.pprov = pprov;
        this.pcl = pcl;
        this.ppee = pve;
        this.ppd = ppd;
        this.pciu = pciu;
        this.cv = cv;
        this.pk = pk;
        this.ft = ft;
        this.pu = pu;
        this.lg.jButtonLogin.addActionListener(this);
        this.op.jButtonClient.addActionListener(this);
        this.op.jButtonProve.addActionListener(this);
        this.op.jButtonProd.addActionListener(this);
        this.op.jButtonCompraV.addActionListener(this);
        this.op.jButtonKardex.addActionListener(this);
        this.op.jButtonVerFactura.addActionListener(this);
        this.m.jMenuHome.addActionListener(this);
        this.m.jMenuItemProv.addActionListener(this);
        this.m.jMenuItemCSesion.addActionListener(this);
        this.m.jMenuItemCiu.addActionListener(this);
        this.m.jMenuItemProv.addActionListener(this);
        this.m.jMenuItemUser.addActionListener(this);
        this.lg.jLabelMError.setVisible(false);
    }

    public String getPermisos() {
        return permisos;
    }

    public void setPermisos(String permisos) {
        this.permisos = permisos;
    }

    public void iniciar() {
        this.m.setTitle("");
        this.m.setLocationRelativeTo(null);
        this.m.jMenuMe.setVisible(false);
        this.m.PanelPrincipal.setSize(1000, 720);
        this.m.PanelPrincipal.revalidate();
        this.m.PanelPrincipal.repaint();

    }

    public void verPrincipal(JPanel p) {
        p.setSize(1000, 720);
        p.setLocation(0, 0);
        this.m.PanelPrincipal.removeAll();
        this.m.PanelPrincipal.add(p, BorderLayout.CENTER);
        this.m.PanelPrincipal.revalidate();
        this.m.PanelPrincipal.repaint();
    }
    private int cont = 0;

    @Override
    public void actionPerformed(ActionEvent ev) {
        //--------------Login--------------------------------------------------------------------------------
        if ((ev.getSource() == this.lg.jButtonLogin) && !((this.lg.jTextFieldNom.getText().isEmpty()
                || String.valueOf(this.lg.jPasswordField1.getPassword()).isEmpty())
                || (this.lg.jTextFieldNom.getText().equals("Ingrese su nombre de usuario")
                || String.valueOf(this.lg.jPasswordField1.getPassword()).equals("               ")))) {
            try {
                IUsuario ui = new UserImpl();
                Usuario u = new Usuario();
                Encriptar enc = new Encriptar();
                u.setNombre(this.lg.jTextFieldNom.getText().trim());
                //u.setClave(enc.encriptar(this.lg.jPasswordField1.getText().trim()));
                u = ui.verificarUsuario(u.getNombre());
                this.lg.jTextFieldNom.getKeyListeners();
                boolean c = this.lg.isCambio();
                if (c == true) {
                    this.cont = 0;
                    this.lg.setCambio(false);
                }
                if (u.isVerif() && !u.getEstado().equals("bloqueado")
                        && enc.encriptar(this.lg.jPasswordField1.getText().trim()).equals(u.getClave())) {

                    Permisos per = new Permisos();
                    ArrayList<Boolean> bl = per.Leer(u.getPermisos());
                    this.m.jMenuItemProv.setVisible(bl.get(0));
                    this.m.jMenuItemCiu.setVisible(bl.get(1));
                    this.m.jMenuItemUser.setVisible(bl.get(2));
                    this.op.jButtonClient.setVisible(bl.get(3));
                    this.op.jButtonProd.setVisible(bl.get(4));
                    this.op.jButtonProve.setVisible(bl.get(5));
                    this.op.jButtonVerFactura.setVisible(bl.get(6));
                    this.op.jButtonKardex.setVisible(bl.get(7));
                    this.op.jButtonCompraV.setVisible(bl.get(8));
                    this.verPrincipal(this.op);
                    this.m.jMenuMe.setVisible(true);
                    this.cont = 0;
                    this.setPermisos(u.getPermisos());

                } else if (u.isVerif()) {
                    System.out.println("sdf");
                    if (u.getEstado().equals("bloqueado")) {
                        JOptionPane.showMessageDialog(this.lg, "Usuario bloqueado");
                    } else if (this.cont == 2) {
                        IUsuario ui2 = new UserImpl();
                        ui2.modificarEstado(u.getNombre(), "bloqueado");
                        JOptionPane.showMessageDialog(this.lg, "Su usuario ha sido bloqueado");
                    } else {
                        this.cont++;
                        this.lg.jLabelMError.setVisible(true);
                        JOptionPane.showMessageDialog(this.lg, "le quedan " + (3 - this.cont) + " intentos");
                    }
                } else {
                    this.lg.jLabelMError.setVisible(true);
                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                System.out.println("ME" + ex.toString());
            }

        } else {
            this.lg.jLabelMError.setVisible(true);
        }

        /* if ((ev.getSource() == this.lg.jButtonLogin)) {
            this.verPrincipal(this.op);
            this.m.jMenuMe.setVisible(true);
        }*/
        //---------------Productos---------------------------------------------------------------------------
        if (ev.getSource()
                == this.op.jButtonProd) {
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            for (int i = 0; i < this.ppd.jTable1.getColumnModel().getColumnCount(); i++) {
                this.ppd.jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            this.verPrincipal(this.ppd);
            this.ppd.jButtonQR.setVisible(false);
            this.ppd.jButtonAccion.setVisible(false);
            this.ppd.jComboBoxCod.setVisible(false);
            this.ppd.jComboBoxRuc.setVisible(false);
            this.ppd.jLabelCod.setVisible(false);
            this.ppd.jLabelMError.setVisible(false);
            this.ppd.jLabelNom.setVisible(false);
            this.ppd.jLabelRuc.setVisible(false);
            this.ppd.jLabelQr.setVisible(false);
            this.ppd.jTextFieldPnombre.setVisible(false);
            DefaultTableModel modelo = (DefaultTableModel) this.ppd.jTable1.getModel();
            modelo.setRowCount(0);
            this.ppd.jComboBoxRuc.removeAllItems();
            try {
                IProducto pdimpl = new ProductImpl();
                ArrayList<Producto> pdlist = pdimpl.listarTodo();
                for (int i = 0; i < pdlist.size(); i++) {
                    modelo.addRow(new Object[]{pdlist.get(i).getCodigo(), pdlist.get(i).getNombre(), pdlist.get(i).getRucProveedo()});
                    this.ppd.jComboBoxCod.addItem(pdlist.get(i).getCodigo() + ": " + pdlist.get(i).getNombre());
                }
                IProveedor pveImpl = new ProveedorImpl();
                ArrayList<Proveedor> pveL = pveImpl.listarTodo();
                if (pveL.isEmpty() && this.getPermisos().substring(5, 6).equals("1")) {
                    String Jop[] = {"Aceptar", "Cancalar"};
                    int a = JOptionPane.showOptionDialog(this.ppd, "No se ha ingresado proveedores\n¿Desea ingresar uno?", "",
                            0, JOptionPane.QUESTION_MESSAGE, null, Jop, "Cancelar");
                    if (Jop[a].equals("Aceptar")) {
                        this.op.jButtonProve.doClick();
                    }
                } else {
                    for (int i = 0; i < pveL.size(); i++) {
                        this.ppd.jComboBoxRuc.addItem(pveL.get(i).getRuc());
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

//---------------------kardex----------------------------------------------
        if (ev.getSource()
                == this.op.jButtonKardex) {
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            for (int i = 0; i < this.pk.jTable1.getColumnModel().getColumnCount(); i++) {
                this.pk.jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            this.verPrincipal(this.pk);
            this.pk.jComboBoxPd.removeAllItems();
            this.pk.jComboBoxPd.addItem("Productos");
            try {
                IProducto pdimpl = new ProductImpl();
                ArrayList<Producto> pdlist = pdimpl.listarTodo();

                if (pdlist.isEmpty() && this.getPermisos().substring(7, 8).equals("1")) {
                    String Jop[] = {"Aceptar", "Cancalar"};
                    int a = JOptionPane.showOptionDialog(this.pk, "No se ha ingresado productos\n¿Desea ingresar uno?",
                            "", 0, JOptionPane.QUESTION_MESSAGE, null, Jop, "Cancelar");
                    if (Jop[a].equals("Aceptar")) {
                        this.op.jButtonProd.doClick();
                    }
                } else {
                    for (int i = 0; i < pdlist.size(); i++) {
                        this.pk.jComboBoxPd.addItem(pdlist.get(i).getCodigo() + ": " + pdlist.get(i).getNombre());
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
//--------------proveedor---------------------------------------------------------------------

        if (ev.getSource()
                == this.op.jButtonProve) {
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            for (int i = 0; i < this.ppee.jTable1.getColumnModel().getColumnCount(); i++) {
                this.ppee.jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            this.verPrincipal(this.ppee);
            this.ppee.ButtonAccion.setVisible(false);
            this.ppee.jComboBoxCiu.setVisible(false);
            this.ppee.jComboBoxProv.setVisible(false);
            this.ppee.jComboBoxRuc.setVisible(false);
            this.ppee.jLabelCiu.setVisible(false);
            this.ppee.jLabelDirec.setVisible(false);
            this.ppee.jLabelEruc.setVisible(false);
            this.ppee.jLabelEdirec.setVisible(false);
            this.ppee.jLabelEnom.setVisible(false);
            this.ppee.jLabelEtl.setVisible(false);
            this.ppee.jLabelNom.setVisible(false);
            this.ppee.jLabelProv.setVisible(false);
            this.ppee.jLabelRuc.setVisible(false);
            this.ppee.jLabelTl.setVisible(false);
            this.ppee.jSeparatorAc.setVisible(false);
            this.ppee.jTextFieldRuc.setVisible(false);
            this.ppee.jTextFieldDirec.setVisible(false);
            this.ppee.jTextFieldNom.setVisible(false);
            this.ppee.jTextFieldTl.setVisible(false);
            DefaultTableModel modelo = (DefaultTableModel) this.ppee.jTable1.getModel();
            modelo.setRowCount(0);
            this.ppee.jComboBoxRuc.removeAllItems();
            this.ppee.jComboBoxRuc.addItem("Proveedores");
            try {
                IProveedor pveImpl = new ProveedorImpl();
                ArrayList<Proveedor> pveL = pveImpl.listarTodo();
                CiudadImpl ci = new CiudadImpl();
                ProvinciaImpl pi = new ProvinciaImpl();
                for (int i = 0; i < pveL.size(); i++) {
                    pveL.get(i).setCodigoCiudad(pveL.get(i).getCodigoCiudad() + ": " + ci.nombre(pveL.get(i).getCodigoCiudad()));
                    pveL.get(i).setCodigoProvincia(pveL.get(i).getCodigoProvincia() + ": " + pi.nombre(pveL.get(i).getCodigoProvincia()));
                    modelo.addRow(new Object[]{pveL.get(i).getRuc(), pveL.get(i).getNombre(), pveL.get(i).getDireccion(),
                        pveL.get(i).getTelefono(), pveL.get(i).getCodigoProvincia(), pveL.get(i).getCodigoCiudad()});
                    this.ppee.jComboBoxRuc.addItem(pveL.get(i).getRuc());
                }
                this.ppee.jComboBoxProv.removeAllItems();
                ICiudad iciu = new CiudadImpl();
                ArrayList<String> cl = iciu.provinciaExistente();
                if (cl.isEmpty() && this.getPermisos().substring(0, 1).equals("1")) {
                    String Jop[] = {"Aceptar", "Cancalar"};
                    int a = JOptionPane.showOptionDialog(this.ppd, "No se ha ingresado provincias\n¿Desea ingresar uno?", "",
                            0, JOptionPane.QUESTION_MESSAGE, null, Jop, "Cancelar");
                    if (Jop[a].equals("Aceptar")) {
                        this.m.jMenuItemProv.doClick();
                    }
                } else {
                    for (int i = 0; i < cl.size(); i++) {
                        this.ppee.jComboBoxProv.addItem(cl.get(i));
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
//-----------Cliente-----------------------------------------------------------

        if (ev.getSource()
                == this.op.jButtonClient) {
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            for (int i = 0; i < this.pcl.jTable1.getColumnModel().getColumnCount(); i++) {
                this.pcl.jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            this.verPrincipal(this.pcl);
            this.pcl.ButtonAccion.setVisible(false);
            this.pcl.jComboBoxCiu.setVisible(false);
            this.pcl.jComboBoxProv.setVisible(false);
            this.pcl.jComboBoxCed.setVisible(false);
            this.pcl.jLabelCed.setVisible(false);
            this.pcl.jLabelCiu.setVisible(false);
            this.pcl.jLabelDirec.setVisible(false);
            this.pcl.jLabelEced.setVisible(false);
            this.pcl.jLabelEdirec.setVisible(false);
            this.pcl.jLabelEnom.setVisible(false);
            this.pcl.jLabelNom.setVisible(false);
            this.pcl.jLabelProv.setVisible(false);
            this.pcl.jSeparatorAc.setVisible(false);
            this.pcl.jTextFieldCed.setVisible(false);
            this.pcl.jTextFieldDirec.setVisible(false);
            this.pcl.jTextFieldNom.setVisible(false);
            DefaultTableModel modelo = (DefaultTableModel) this.pcl.jTable1.getModel();
            modelo.setRowCount(0);
            this.pcl.jComboBoxCed.removeAllItems();
            this.pcl.jComboBoxCed.addItem("Clientes");
            try {
                ICliente icl = new ClientImpl();
                CiudadImpl ci = new CiudadImpl();
                ProvinciaImpl pi = new ProvinciaImpl();
                ArrayList<Cliente> clist = icl.listarTodo();
                for (int i = 0; i < clist.size(); i++) {
                    clist.get(i).setCodigoCiudad(clist.get(i).getCodigoCiudad() + ": " + ci.nombre(clist.get(i).getCodigoCiudad()));
                    clist.get(i).setCodigoProvincia(clist.get(i).getCodigoProvincia() + ": " + pi.nombre(clist.get(i).getCodigoProvincia()));
                    modelo.addRow(new Object[]{clist.get(i).getCedula(), clist.get(i).getNombre(), clist.get(i).getDireccion(),
                        clist.get(i).getCodigoProvincia(), clist.get(i).getCodigoCiudad()});
                    this.pcl.jComboBoxCed.addItem(clist.get(i).getCedula());
                }
                ICiudad iciu = new CiudadImpl();
                this.pcl.jComboBoxProv.removeAllItems();
                ArrayList<String> clis = iciu.provinciaExistente();
                if (clis.isEmpty() && this.getPermisos().substring(0, 1).equals("1")) {
                    String Jop[] = {"Aceptar", "Cancalar"};
                    int a = JOptionPane.showOptionDialog(this.ppd, "No se ha ingresado provincias\n¿Desea ingresar uno?",
                            "", 0, JOptionPane.QUESTION_MESSAGE, null, Jop, "Cancelar");
                    if (Jop[a].equals("Aceptar")) {
                        this.m.jMenuItemProv.doClick();
                    }
                } else {
                    for (int i = 0; i < clis.size(); i++) {
                        this.pcl.jComboBoxProv.addItem(clis.get(i));
                    }
                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        }

//--------provincias ---------------------------------------------------------//    
        if (ev.getSource() == this.m.jMenuItemProv) {
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            for (int i = 0; i < this.pprov.jTable1.getColumnModel().getColumnCount(); i++) {
                this.pprov.jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            this.verPrincipal(this.pprov);
            this.pprov.jLabelMError.setVisible(false);
            this.pprov.jButtonAccion.setVisible(false);
            this.pprov.jLabelP1.setVisible(false);
            this.pprov.jComboBoxP.setVisible(false);
            this.pprov.jLabelP2.setVisible(false);
            this.pprov.jTextFieldPnombre.setVisible(false);
            DefaultTableModel modelo = (DefaultTableModel) this.pprov.jTable1.getModel();
            modelo.setRowCount(0);
            this.pprov.jComboBoxP.removeAllItems();
            this.pprov.jComboBoxP.addItem("Provincias");
            try {
                ProvinciaImpl iprov = new ProvinciaImpl();
                ArrayList<Provincia> plist = iprov.listarTodo();
                for (int i = 0; i < plist.size(); i++) {
                    modelo.addRow(new Object[]{plist.get(i).getCodigo(), plist.get(i).getNombre()});
                    this.pprov.jComboBoxP.addItem(plist.get(i).getCodigo() + ": " + plist.get(i).getNombre());
                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

//-------------ciudades---------------------------------------------------------------//
        if (ev.getSource()
                == this.m.jMenuItemCiu) {
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            for (int i = 0; i < this.pciu.jTableC.getColumnModel().getColumnCount(); i++) {
                this.pciu.jTableC.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            this.verPrincipal(this.pciu);
            this.pciu.jButtonAccion.setVisible(false);
            this.pciu.jLabelC1.setVisible(false);
            this.pciu.jComboBoxC.setVisible(false);
            this.pciu.jLabelC2.setVisible(false);
            this.pciu.jTextFieldNom.setVisible(false);
            this.pciu.jLabelC3.setVisible(false);
            this.pciu.jComboBoxP.setVisible(false);
            DefaultTableModel modelo = (DefaultTableModel) this.pciu.jTableC.getModel();
            modelo.setRowCount(0);
            this.pciu.jComboBoxC.removeAllItems();
            this.pciu.jComboBoxC.addItem("Ciudades");
            this.pciu.jComboBoxP.removeAllItems();
            this.pciu.jComboBoxP.addItem("Provincias");
            try {
                ICiudad iciu = new CiudadImpl();
                ArrayList<Ciudad> clist = iciu.listarTodo();
                for (int i = 0; i < clist.size(); i++) {
                    modelo.addRow(new Object[]{clist.get(i).getCodigo(), clist.get(i).getNombre(),
                        clist.get(i).getCodigoProvincia(), clist.get(i).getNombreProvincia()});
                    this.pciu.jComboBoxC.addItem(clist.get(i).getCodigo() + ": " + clist.get(i).getNombre());
                }
                ArrayList<String> pl = iciu.provinciaExistente();
                if (pl.isEmpty() && this.getPermisos().substring(0, 1).equals("1")) {
                    String Jop[] = {"Aceptar", "Cancalar"};
                    int a = JOptionPane.showOptionDialog(this.ppd, "No se ha ingresado provincias\n¿Desea ingresar uno?",
                            "", 0, JOptionPane.QUESTION_MESSAGE, null, Jop, "Cancelar");
                    if (Jop[a].equals("Aceptar")) {
                        this.m.jMenuItemProv.doClick();
                    }
                } else {
                    for (int i = 0; i < pl.size(); i++) {
                        this.pciu.jComboBoxP.addItem(pl.get(i));
                    }
                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        }
//-------------------------cerrar sesion--------------------------------------------------

        if (ev.getSource()
                == this.m.jMenuItemCSesion) {
            this.verPrincipal(this.lg);
            this.lg.jTextFieldNom.setText("Ingrese su nombre de usuario");
            this.lg.jPasswordField1.setText("               ");
            this.m.jMenuMe.setVisible(false);
            this.lg.jLabelMError.setVisible(false);
        }
//----------Opciones-----------------------------------------------

        if (ev.getSource()
                == this.m.jMenuHome) {
            this.verPrincipal(this.op);
        }
//----------compra y venta ----------------------------------------

        if (ev.getSource()
                == this.op.jButtonCompraV) {
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            for (int i = 0; i < this.cv.jTable.getColumnModel().getColumnCount(); i++) {
                this.cv.jTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            this.verPrincipal(this.cv);
            this.cv.jLabelProvClient.setVisible(false);
            this.cv.jComboBoxSelectPC.setVisible(false);
            this.cv.jLabelFecha.setVisible(false);
            this.cv.jDateChooser1.setVisible(false);
            this.cv.jButtonSelectCV.setVisible(false);
            this.cv.jButtonAddCV.setVisible(false);
            this.cv.jButtonQR.setVisible(false);
            this.cv.jComboBoxProducto.setVisible(false);
            this.cv.jLabelCant.setVisible(false);
            this.cv.jLabelErrorCant.setVisible(false);
            this.cv.jLabelErrorPrecio.setVisible(false);
            this.cv.jLabelErrorFecha.setVisible(false);
            this.cv.jLabelErroPvCl.setVisible(false);
            this.cv.jLabelProd.setVisible(false);
            this.cv.jLabelprecio.setVisible(false);
            this.cv.jLabelTotal.setVisible(false);
            this.cv.jTable.setVisible(false);
            this.cv.jSpinnerInv.setVisible(false);
            this.cv.jLabel$.setVisible(false);
            this.cv.jTextFieldPrecio.setVisible(false);
            DefaultTableModel modelo = (DefaultTableModel) this.cv.jTable.getModel();
            modelo.setRowCount(0);
        }

//-------------Factura-----------------------------------------------------------------------
        if (ev.getSource()
                == this.op.jButtonVerFactura) {
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            for (int i = 0; i < this.ft.jTable.getColumnModel().getColumnCount(); i++) {
                this.ft.jTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            this.verPrincipal(ft);
            this.ft.jButtonBorrar.setVisible(false);
            this.ft.jComboBoxFact.setVisible(false);
            this.ft.jLabelSelect.setVisible(false);
            this.ft.jLabelError.setVisible(false);
        }
//--------------usuario----------------------------------------------------------------------

        if (ev.getSource()
                == this.m.jMenuItemUser) {
            this.verPrincipal(pu);
            this.pu.jLabelMErrorUser.setVisible(false);
            this.pu.jLabelMErrorclave.setVisible(false);
            this.pu.jLabelMErrorPer.setVisible(false);
            this.pu.jLabelMErrorclave1.setVisible(false);
        }
    }
}
