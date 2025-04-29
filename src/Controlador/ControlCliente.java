/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Implementacion.CiudadImpl;
import Implementacion.ClientImpl;
import Implementacion.ProvinciaImpl;
import Interface.ICliente;
import Modelo.Cliente;
import Recursos.Cedula;
import Recursos.Codigo;
import Recursos.ControlDatos;
import Vista.PanelCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mateo
 */
public class ControlCliente implements ActionListener {

    PanelCliente pcl = new PanelCliente();

    public ControlCliente(PanelCliente pcl) {
        this.pcl = pcl;
        this.pcl.ButtonAccion.addActionListener(this);
        this.pcl.jButtonAd.addActionListener(this);
        this.pcl.jButtonEdit.addActionListener(this);
        this.pcl.jButtonElim.addActionListener(this);
        this.pcl.jComboBoxProv.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.pcl.jButtonAd) {
            this.pcl.ButtonAccion.setText("Agregar");
            this.pcl.jTextFieldNom.setText("");
            this.pcl.jTextFieldCed.setText("");
            this.pcl.jTextFieldDirec.setText("");
            this.pcl.ButtonAccion.setVisible(true);
            this.pcl.jComboBoxCiu.setVisible(true);
            this.pcl.jComboBoxProv.setVisible(true);
            this.pcl.jComboBoxCed.setVisible(false);
            this.pcl.jLabelCed.setVisible(true);
            this.pcl.jLabelCiu.setVisible(true);
            this.pcl.jLabelDirec.setVisible(true);
            this.pcl.jLabelEced.setVisible(false);
            this.pcl.jLabelEdirec.setVisible(false);
            this.pcl.jLabelEnom.setVisible(false);
            this.pcl.jLabelNom.setVisible(true);
            this.pcl.jLabelProv.setText("Provincia:");
            this.pcl.jLabelProv.setVisible(true);
            this.pcl.jSeparatorAc.setVisible(true);
            this.pcl.jTextFieldCed.setVisible(true);
            this.pcl.jTextFieldDirec.setVisible(true);
            this.pcl.jTextFieldNom.setVisible(true);

        }

        if (e.getSource() == this.pcl.jComboBoxProv) {
            try {
                ICliente icl = new ClientImpl();
                Codigo cod = new Codigo();
                ArrayList<String> cl = icl.ciudades(cod.getCode(this.pcl.jComboBoxProv.getSelectedItem().toString()));
                this.pcl.jComboBoxCiu.removeAllItems();
                if (cl.isEmpty()) {
                    JOptionPane.showMessageDialog(this.pcl, "No existen ciudades para la provincia ");
                }
                for (int i = 0; i < cl.size(); i++) {
                    this.pcl.jComboBoxCiu.addItem(cl.get(i));
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

        if (e.getSource() == this.pcl.ButtonAccion && this.pcl.ButtonAccion.getText().equals("Agregar")) {
            try {
                Cliente cl = new Cliente();
                ControlDatos cdat = new ControlDatos();
                Codigo cod = new Codigo();
                Cedula ced = new Cedula();
                cl.setCedula(this.pcl.jTextFieldCed.getText().trim());
                cl.setNombre(this.pcl.jTextFieldNom.getText().trim());
                cl.setDireccion(this.pcl.jTextFieldDirec.getText().trim());
                cl.setCodigoProvincia(cod.getCode(this.pcl.jComboBoxProv.getSelectedItem().toString()));
                cl.setCodigoCiudad(cod.getCode(this.pcl.jComboBoxCiu.getSelectedItem().toString()));
                ICliente icl = new ClientImpl();
                if (!icl.existe(cl.getCedula()) && ced.largo(cl.getCedula()) && cdat.dosP(cl.getNombre())
                        && cl.getNombre().length() <= 49 && cl.getDireccion().length() <= 49
                        && cdat.tresP(cl.getDireccion()) && this.pcl.jComboBoxCiu.getSelectedItem() != null
                        && this.pcl.jComboBoxProv.getSelectedItem() != null) {

                    icl.crear(cl);
                    this.pcl.jTextFieldNom.setText("");
                    this.pcl.jTextFieldCed.setText("");
                    this.pcl.jTextFieldDirec.setText("");
                    this.pcl.jLabelEced.setVisible(false);
                    this.pcl.jLabelEdirec.setVisible(false);
                    this.pcl.jLabelEnom.setVisible(false);
                    DefaultTableModel modelo = (DefaultTableModel) this.pcl.jTable1.getModel();
                    modelo.setRowCount(0);
                    this.pcl.jComboBoxCed.removeAllItems();
                    this.pcl.jComboBoxCed.addItem("Clientes");
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
                } else {
                    if (!ced.largo(cl.getCedula()) || icl.existe(cl.getCedula())) {
                        this.pcl.jLabelEced.setVisible(true);
                    }
                    if (!cdat.dosP(cl.getNombre())) {
                        this.pcl.jLabelEnom.setVisible(true);
                    }
                    if (!cdat.tresP(cl.getDireccion())) {
                        this.pcl.jLabelEdirec.setVisible(true);
                    }
                    if (cl.getNombre().length() >= 49) {
                        JOptionPane.showMessageDialog(this.pcl, "Nombre demasiado extenso");
                    }
                    if (cl.getDireccion().length() >= 49) {
                        JOptionPane.showMessageDialog(this.pcl, "Texto demasiado extenso");
                    }
                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        if (e.getSource() == this.pcl.jButtonEdit) {
            this.pcl.ButtonAccion.setText("Editar");
            this.pcl.ButtonAccion.setVisible(true);
            this.pcl.jComboBoxCiu.setVisible(true);
            this.pcl.jComboBoxProv.setVisible(true);
            this.pcl.jComboBoxCed.setVisible(true);
            this.pcl.jLabelCed.setVisible(true);
            this.pcl.jLabelCiu.setVisible(true);
            this.pcl.jTextFieldNom.setText("");
            this.pcl.jTextFieldCed.setText("");
            this.pcl.jTextFieldDirec.setText("");
            this.pcl.jLabelDirec.setVisible(true);
            this.pcl.jLabelEced.setVisible(false);
            this.pcl.jLabelEdirec.setVisible(false);
            this.pcl.jLabelEnom.setVisible(false);
            this.pcl.jLabelNom.setVisible(true);
            this.pcl.jLabelProv.setText("Provincia:");
            this.pcl.jLabelProv.setVisible(true);
            this.pcl.jSeparatorAc.setVisible(true);
            this.pcl.jTextFieldCed.setVisible(false);
            this.pcl.jTextFieldDirec.setVisible(true);
            this.pcl.jTextFieldNom.setVisible(true);
        }
        if (e.getSource() == this.pcl.ButtonAccion && this.pcl.ButtonAccion.getText().equals("Editar")) {
            try {
                Cliente cl = new Cliente();
                ControlDatos cdat = new ControlDatos();
                Codigo cod = new Codigo();
                cl.setCedula(this.pcl.jComboBoxCed.getSelectedItem().toString());
                cl.setNombre(this.pcl.jTextFieldNom.getText().trim());
                cl.setDireccion(this.pcl.jTextFieldDirec.getText().trim());
                cl.setCodigoCiudad(cod.getCode(this.pcl.jComboBoxCiu.getSelectedItem().toString()));
                cl.setCodigoProvincia(cod.getCode(this.pcl.jComboBoxProv.getSelectedItem().toString()));
                if (cdat.dosP(cl.getNombre()) && cdat.tresP(cl.getDireccion())
                        && cl.getNombre().length() <= 49 && cl.getDireccion().length() <= 49
                        && this.pcl.jComboBoxCed.getItemCount() != 1 && !cl.getCedula().equals("Clientes")) {

                    ICliente icl = new ClientImpl();
                    icl.modificar(cl);
                    this.pcl.jLabelEdirec.setVisible(false);
                    this.pcl.jLabelEnom.setVisible(false);
                    this.pcl.jTextFieldDirec.setText("");
                    this.pcl.jTextFieldNom.setText("");
                    DefaultTableModel modelo = (DefaultTableModel) this.pcl.jTable1.getModel();
                    modelo.setRowCount(0);
                    this.pcl.jComboBoxCed.removeAllItems();
                    this.pcl.jComboBoxCed.addItem("Clientes");
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
                } else {
                    if (!cdat.dosP(cl.getNombre())) {
                        this.pcl.jLabelEnom.setVisible(true);
                    }
                    if (!cdat.tresP(cl.getDireccion())) {
                        this.pcl.jLabelEdirec.setVisible(true);
                    }
                    if (this.pcl.jComboBoxCed.getItemCount() == 1) {
                         JOptionPane.showMessageDialog(this.pcl, "No ha ingresado clientes");
                    }
                    if (cl.getNombre().length() >= 49) {
                        JOptionPane.showMessageDialog(this.pcl, "Nombre demasiado extenso");
                    }
                    if (cl.getDireccion().length() >= 49) {
                        JOptionPane.showMessageDialog(this.pcl, "Texto demasiado extenso");
                    }
                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        if (e.getSource() == this.pcl.jButtonElim) {
            this.pcl.ButtonAccion.setText("Borrar");
            this.pcl.ButtonAccion.setVisible(true);
            this.pcl.jComboBoxCiu.setVisible(true);
            this.pcl.jComboBoxProv.setVisible(false);
            this.pcl.jComboBoxCed.setVisible(false);
            this.pcl.jLabelCed.setVisible(false);
            this.pcl.jLabelCiu.setVisible(false);
            this.pcl.jLabelDirec.setVisible(false);
            this.pcl.jLabelEced.setVisible(false);
            this.pcl.jLabelEdirec.setVisible(false);
            this.pcl.jLabelEnom.setVisible(false);
            this.pcl.jLabelNom.setVisible(false);
            this.pcl.jLabelProv.setText("Cedula:");
            this.pcl.jLabelProv.setVisible(true);
            this.pcl.jSeparatorAc.setVisible(false);
            this.pcl.jTextFieldCed.setVisible(false);
            this.pcl.jTextFieldDirec.setVisible(false);
            this.pcl.jTextFieldNom.setVisible(false);
            try {
                ICliente icl = new ClientImpl();
                ArrayList<Cliente> clist = icl.listarTodo();
                this.pcl.jComboBoxCiu.removeAllItems();
                for (int i = 0; i < clist.size(); i++) {
                    this.pcl.jComboBoxCiu.addItem(clist.get(i).getCedula());
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        if (e.getSource() == this.pcl.ButtonAccion && this.pcl.ButtonAccion.getText().equals("Borrar")) {
            try {
                if (this.pcl.jComboBoxCiu.getSelectedItem() != null) {
                    ICliente icl = new ClientImpl();
                    icl.borrar(this.pcl.jComboBoxCiu.getSelectedItem().toString().trim());
                    DefaultTableModel modelo = (DefaultTableModel) this.pcl.jTable1.getModel();
                    modelo.setRowCount(0);
                    this.pcl.jComboBoxCed.removeAllItems();
                    this.pcl.jComboBoxCiu.removeAllItems();
                    this.pcl.jComboBoxCed.addItem("Clientes");
                    CiudadImpl ci = new CiudadImpl();
                    ProvinciaImpl pi = new ProvinciaImpl();
                    ArrayList<Cliente> clist = icl.listarTodo();
                    for (int i = 0; i < clist.size(); i++) {
                        clist.get(i).setCodigoCiudad(clist.get(i).getCodigoCiudad() + ": " + ci.nombre(clist.get(i).getCodigoCiudad()));
                        clist.get(i).setCodigoProvincia(clist.get(i).getCodigoProvincia() + ": " + pi.nombre(clist.get(i).getCodigoProvincia()));
                        modelo.addRow(new Object[]{clist.get(i).getCedula(), clist.get(i).getNombre(), clist.get(i).getDireccion(),
                            clist.get(i).getCodigoProvincia(), clist.get(i).getCodigoCiudad()});
                        this.pcl.jComboBoxCed.addItem(clist.get(i).getCedula());
                        this.pcl.jComboBoxCiu.addItem(clist.get(i).getCedula());
                    }
                } else {
                    JOptionPane.showMessageDialog(this.pcl, "No ha ingresado clientes");
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
