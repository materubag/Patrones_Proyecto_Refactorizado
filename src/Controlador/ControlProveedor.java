/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Implementacion.CiudadImpl;
import Implementacion.ClientImpl;
import Implementacion.ProveedorImpl;
import Implementacion.ProvinciaImpl;
import Interface.ICliente;
import Interface.IProveedor;
import Modelo.Proveedor;
import Recursos.Codigo;
import Recursos.ControlDatos;
import Recursos.Ruc;
import Vista.Panelproveedor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mateo
 */
public class ControlProveedor implements ActionListener {

    Panelproveedor pve = new Panelproveedor();

    public ControlProveedor(Panelproveedor pve) {
        this.pve = pve;
        this.pve.ButtonAccion.addActionListener(this);
        this.pve.jButtonAd.addActionListener(this);
        this.pve.jButtonEdit.addActionListener(this);
        this.pve.jButtonElim.addActionListener(this);
        this.pve.jComboBoxProv.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.pve.jComboBoxProv) {
            try {
                ICliente icl = new ClientImpl();
                Codigo cod = new Codigo();
                ArrayList<String> cl = icl.ciudades(cod.getCode(this.pve.jComboBoxProv.getSelectedItem().toString()));
                if (cl.isEmpty()) {
                    JOptionPane.showMessageDialog(this.pve, "No existen ciudades para la provincia");
                }
                this.pve.jComboBoxCiu.removeAllItems();
                for (int i = 0; i < cl.size(); i++) {
                    this.pve.jComboBoxCiu.addItem(cl.get(i));
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        if (e.getSource() == this.pve.jButtonAd) {
            this.pve.ButtonAccion.setText("Agregar");
            this.pve.ButtonAccion.setVisible(true);
            this.pve.jComboBoxCiu.setVisible(true);
            this.pve.jComboBoxProv.setVisible(true);
            this.pve.jComboBoxRuc.setVisible(false);
            this.pve.jLabelCiu.setVisible(true);
            this.pve.jLabelDirec.setVisible(true);
            this.pve.jLabelEruc.setVisible(false);
            this.pve.jLabelEdirec.setVisible(false);
            this.pve.jLabelEnom.setVisible(false);
            this.pve.jLabelEtl.setVisible(false);
            this.pve.jLabelNom.setVisible(true);
            this.pve.jLabelProv.setText("Provincia:");
            this.pve.jLabelProv.setVisible(true);
            this.pve.jLabelRuc.setVisible(true);
            this.pve.jLabelTl.setVisible(true);
            this.pve.jSeparatorAc.setVisible(true);
            this.pve.jTextFieldRuc.setVisible(true);
            this.pve.jTextFieldDirec.setVisible(true);
            this.pve.jTextFieldNom.setVisible(true);
            this.pve.jTextFieldTl.setVisible(true);

        }
        if (e.getSource() == this.pve.ButtonAccion && this.pve.ButtonAccion.getText().equals("Agregar")) {
            try {
                Proveedor pe = new Proveedor();
                Codigo cod = new Codigo();
                ControlDatos d = new ControlDatos();
                Ruc vruc = new Ruc();
                pe.setRuc(this.pve.jTextFieldRuc.getText().trim());
                pe.setNombre(this.pve.jTextFieldNom.getText().trim());
                pe.setDireccion(this.pve.jTextFieldDirec.getText().trim());
                pe.setTelefono(this.pve.jTextFieldTl.getText().trim());
                pe.setCodigoCiudad(cod.getCode(this.pve.jComboBoxCiu.getSelectedItem().toString()));
                pe.setCodigoProvincia(cod.getCode(this.pve.jComboBoxProv.getSelectedItem().toString()));
                IProveedor pvimpl = new ProveedorImpl();
                if (!pvimpl.existe(pe.getRuc()) && vruc.validar(pe.getRuc()) && d.dosP(pe.getNombre())
                        && d.dosP(pe.getDireccion()) && d.numeros(pe.getTelefono())
                        && pe.getNombre().length() <= 49 && pe.getDireccion().length() <= 49
                        && pe.getTelefono().length() <= 10 && pe.getTelefono().length() >= 7
                        && this.pve.jComboBoxCiu.getSelectedItem() != null
                        && this.pve.jComboBoxProv.getSelectedItem() != null) {

                    pvimpl.crear(pe);
                    DefaultTableModel modelo = (DefaultTableModel) this.pve.jTable1.getModel();
                    modelo.setRowCount(0);
                    this.pve.jLabelEruc.setVisible(false);
                    this.pve.jLabelEdirec.setVisible(false);
                    this.pve.jLabelEnom.setVisible(false);
                    this.pve.jLabelEtl.setVisible(false);
                    this.pve.jTextFieldRuc.setText("");
                    this.pve.jTextFieldDirec.setText("");
                    this.pve.jTextFieldNom.setText("");
                    this.pve.jTextFieldTl.setText("");
                    this.pve.jComboBoxRuc.removeAllItems();
                    this.pve.jComboBoxRuc.addItem("Proveedores");
                    IProveedor pveImpl = new ProveedorImpl();
                    ArrayList<Proveedor> pveL = pveImpl.listarTodo();
                    CiudadImpl ci = new CiudadImpl();
                    ProvinciaImpl pi = new ProvinciaImpl();

                    for (int i = 0; i < pveL.size(); i++) {
                        pveL.get(i).setCodigoCiudad(pveL.get(i).getCodigoCiudad() + ": " + ci.nombre(pveL.get(i).getCodigoCiudad()));
                        pveL.get(i).setCodigoProvincia(pveL.get(i).getCodigoProvincia() + ": " + pi.nombre(pveL.get(i).getCodigoProvincia()));
                        modelo.addRow(new Object[]{pveL.get(i).getRuc(), pveL.get(i).getNombre(), pveL.get(i).getDireccion(),
                            pveL.get(i).getTelefono(), pveL.get(i).getCodigoProvincia(), pveL.get(i).getCodigoCiudad()});
                        this.pve.jComboBoxRuc.addItem(pveL.get(i).getRuc());
                    }
                } else {
                    if (!vruc.validar(pe.getRuc()) || pvimpl.existe(pe.getRuc())) {
                        this.pve.jLabelEruc.setVisible(true);
                    }
                    if (!d.dosP(pe.getNombre())) {
                        this.pve.jLabelEnom.setVisible(true);
                    }
                    if (!d.dosP(pe.getDireccion())) {
                        this.pve.jLabelEdirec.setVisible(true);
                    }
                    if (!d.numeros(pe.getTelefono())) {
                        this.pve.jLabelEtl.setVisible(true);
                    }
                    if (this.pve.jComboBoxProv.getSelectedItem() == null) {
                        JOptionPane.showMessageDialog(this.pve, "No existen provincias");
                    }
                    if (pe.getNombre().length() >= 49) {
                        JOptionPane.showMessageDialog(this.pve, "Nombre demasiado extenso");
                    }
                    if (pe.getDireccion().length() >= 49) {
                        JOptionPane.showMessageDialog(this.pve, "Texto demasiado extenso");
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        if (e.getSource() == this.pve.jButtonEdit) {
            this.pve.ButtonAccion.setText("Editar");
            this.pve.ButtonAccion.setVisible(true);
            this.pve.jComboBoxCiu.setVisible(true);
            this.pve.jComboBoxProv.setVisible(true);
            this.pve.jComboBoxRuc.setVisible(true);
            this.pve.jLabelCiu.setVisible(true);
            this.pve.jLabelDirec.setVisible(true);
            this.pve.jLabelEruc.setVisible(false);
            this.pve.jLabelEdirec.setVisible(false);
            this.pve.jLabelEnom.setVisible(false);
            this.pve.jLabelEtl.setVisible(false);
            this.pve.jLabelNom.setVisible(true);
            this.pve.jLabelProv.setText("Provincia:");
            this.pve.jLabelProv.setVisible(true);
            this.pve.jLabelRuc.setVisible(true);
            this.pve.jLabelTl.setVisible(true);
            this.pve.jSeparatorAc.setVisible(true);
            this.pve.jTextFieldRuc.setVisible(false);
            this.pve.jTextFieldDirec.setVisible(true);
            this.pve.jTextFieldNom.setVisible(true);
            this.pve.jTextFieldTl.setVisible(true);

        }
        if (e.getSource() == this.pve.ButtonAccion && this.pve.ButtonAccion.getText().equals("Editar")) {
            try {
                Proveedor pe = new Proveedor();
                Codigo cod = new Codigo();
                ControlDatos d = new ControlDatos();
                pe.setRuc(this.pve.jComboBoxRuc.getSelectedItem().toString());
                pe.setNombre(this.pve.jTextFieldNom.getText().trim());
                pe.setDireccion(this.pve.jTextFieldDirec.getText());
                pe.setTelefono(this.pve.jTextFieldTl.getText().trim());
                pe.setCodigoCiudad(cod.getCode(this.pve.jComboBoxCiu.getSelectedItem().toString()));
                pe.setCodigoProvincia(cod.getCode(this.pve.jComboBoxProv.getSelectedItem().toString()));
                if (d.dosP(pe.getNombre()) && d.dosP(pe.getDireccion()) && d.numeros(pe.getTelefono())
                        && pe.getNombre().length() <= 49 && pe.getDireccion().length() <= 49
                        && this.pve.jComboBoxRuc.getItemCount() != 1 && !pe.getRuc().equals("Proveedores")) {
                    IProveedor pvimpl = new ProveedorImpl();
                    pvimpl.modificar(pe);
                    DefaultTableModel modelo = (DefaultTableModel) this.pve.jTable1.getModel();
                    modelo.setRowCount(0);
                    this.pve.jLabelEruc.setVisible(false);
                    this.pve.jLabelEdirec.setVisible(false);
                    this.pve.jLabelEnom.setVisible(false);
                    this.pve.jLabelEtl.setVisible(false);
                    this.pve.jTextFieldRuc.setText("");
                    this.pve.jTextFieldDirec.setText("");
                    this.pve.jTextFieldTl.setText("");
                    this.pve.jComboBoxRuc.removeAllItems();
                    this.pve.jComboBoxRuc.addItem("Proveedores");
                    IProveedor pveImpl = new ProveedorImpl();
                    ArrayList<Proveedor> pveL = pveImpl.listarTodo();
                    CiudadImpl ci = new CiudadImpl();
                    ProvinciaImpl pi = new ProvinciaImpl();
                    for (int i = 0; i < pveL.size(); i++) {
                        pveL.get(i).setCodigoCiudad(pveL.get(i).getCodigoCiudad() + ": " + ci.nombre(pveL.get(i).getCodigoCiudad()));
                        pveL.get(i).setCodigoProvincia(pveL.get(i).getCodigoProvincia() + ": " + pi.nombre(pveL.get(i).getCodigoProvincia()));
                        modelo.addRow(new Object[]{pveL.get(i).getRuc(), pveL.get(i).getNombre(), pveL.get(i).getDireccion(),
                            pveL.get(i).getTelefono(), pveL.get(i).getCodigoProvincia(), pveL.get(i).getCodigoCiudad()});
                        this.pve.jComboBoxRuc.addItem(pveL.get(i).getRuc());
                    }
                } else {
                    if (!d.dosP(pe.getNombre())) {
                        this.pve.jLabelEnom.setVisible(true);
                    }
                    if (!d.dosP(pe.getDireccion())) {
                        this.pve.jLabelEdirec.setVisible(true);
                    }
                    if (!d.numeros(pe.getTelefono())) {
                        this.pve.jLabelEtl.setVisible(true);
                    }
                    if (this.pve.jComboBoxRuc.getItemCount() == 1) {
                        JOptionPane.showMessageDialog(this.pve, "No ha ingresado proveedores");
                    }
                    if (pe.getRuc().equals("Proveedores")) {
                        this.pve.jLabelEruc.setVisible(true);
                    }
                    if (pe.getNombre().length() >= 49) {
                        JOptionPane.showMessageDialog(this.pve, "Nombre demasiado extenso");
                    }
                    if (pe.getDireccion().length() >= 49) {
                        JOptionPane.showMessageDialog(this.pve, "Texto demasiado extenso");
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        if (e.getSource() == this.pve.jButtonElim) {
            this.pve.ButtonAccion.setText("Borrar");
            this.pve.ButtonAccion.setVisible(true);
            this.pve.jComboBoxCiu.setVisible(true);
            this.pve.jComboBoxProv.setVisible(false);
            this.pve.jComboBoxRuc.setVisible(false);
            this.pve.jLabelCiu.setVisible(false);
            this.pve.jLabelDirec.setVisible(false);
            this.pve.jLabelEruc.setVisible(false);
            this.pve.jLabelEdirec.setVisible(false);
            this.pve.jLabelEnom.setVisible(false);
            this.pve.jLabelEtl.setVisible(false);
            this.pve.jLabelNom.setVisible(false);
            this.pve.jLabelProv.setText("Proveedor:");
            this.pve.jLabelProv.setVisible(true);
            this.pve.jLabelRuc.setVisible(false);
            this.pve.jLabelTl.setVisible(false);
            this.pve.jSeparatorAc.setVisible(false);
            this.pve.jTextFieldRuc.setVisible(false);
            this.pve.jTextFieldDirec.setVisible(false);
            this.pve.jTextFieldNom.setVisible(false);
            this.pve.jTextFieldTl.setVisible(false);
            try {
                ProveedorImpl pi = new ProveedorImpl();
                ArrayList<Proveedor> plist = pi.listarTodo();
                this.pve.jComboBoxCiu.removeAllItems();
                for (int i = 0; i < plist.size(); i++) {
                    this.pve.jComboBoxCiu.addItem(plist.get(i).getRuc());
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        if (e.getSource() == this.pve.ButtonAccion && this.pve.ButtonAccion.getText().equals("Borrar")) {
            try {
                ProveedorImpl pvi = new ProveedorImpl();
                if (this.pve.jComboBoxCiu.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(this.pve, "No ha ingresado proveedores");
                } else if (pvi.TieneProducto(this.pve.jComboBoxCiu.getSelectedItem().toString())) {
                    JOptionPane.showMessageDialog(this.pve, "No se puede borrar\nTiene productos registrados");
                } else {
                    pvi.borrar(this.pve.jComboBoxCiu.getSelectedItem().toString());
                    DefaultTableModel modelo = (DefaultTableModel) this.pve.jTable1.getModel();
                    modelo.setRowCount(0);
                    this.pve.jComboBoxRuc.removeAllItems();
                    this.pve.jComboBoxCiu.removeAllItems();
                    this.pve.jComboBoxRuc.addItem("Proveedores");
                    ArrayList<Proveedor> pveL = pvi.listarTodo();
                    CiudadImpl ci = new CiudadImpl();
                    ProvinciaImpl pi = new ProvinciaImpl();
                    for (int i = 0; i < pveL.size(); i++) {
                        pveL.get(i).setCodigoCiudad(pveL.get(i).getCodigoCiudad() + ": " + ci.nombre(pveL.get(i).getCodigoCiudad()));
                        pveL.get(i).setCodigoProvincia(pveL.get(i).getCodigoProvincia() + ": " + pi.nombre(pveL.get(i).getCodigoProvincia()));
                        modelo.addRow(new Object[]{pveL.get(i).getRuc(), pveL.get(i).getNombre(), pveL.get(i).getDireccion(),
                            pveL.get(i).getTelefono(), pveL.get(i).getCodigoProvincia(), pveL.get(i).getCodigoCiudad()});
                        this.pve.jComboBoxRuc.addItem(pveL.get(i).getRuc());
                        this.pve.jComboBoxCiu.addItem(pveL.get(i).getRuc());
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
