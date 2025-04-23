/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Implementacion.KardexImpl;
import Implementacion.ProductImpl;
import Interface.IProducto;
import Modelo.Producto;
import Recursos.Codigo;
import Recursos.QR;
import Vista.PProductos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mateo
 */
public class ControlProducto implements ActionListener {

    PProductos ppd = new PProductos();

    public ControlProducto(PProductos ppd) {
        this.ppd = ppd;
        this.ppd.jButtonAccion.addActionListener(this);
        this.ppd.jButtonAdd.addActionListener(this);
        this.ppd.jButtonEdi.addActionListener(this);
        this.ppd.jButtonGenQR.addActionListener(this);
        this.ppd.jButtonElimi.addActionListener(this);
        this.ppd.jButtonQR.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.ppd.jButtonQR) {
            try {
                QR qr = new QR();
                Codigo cd = new Codigo();
                ProductImpl pim = new ProductImpl();
                if (this.ppd.jComboBoxCod.getSelectedItem() != null) {
                    Producto p = pim.QrP(cd.getCode(this.ppd.jComboBoxCod.getSelectedItem().toString()));
                    ImageIcon ic = qr.generar("Codigo de Producto: " + p.getCodigo()
                            + "\nNombre del Producto: " + p.getNombre()
                            + "\nProveedor: " + p.getRucProveedo());

                    this.ppd.jLabelQr.setIcon(ic);
                } else {
                    JOptionPane.showMessageDialog(this.ppd, "No existen Productos");
                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        if (e.getSource() == this.ppd.jButtonAdd) {
            this.ppd.jButtonQR.setVisible(false);
            this.ppd.jButtonAccion.setText("Agregar");
            this.ppd.jButtonAccion.setVisible(true);
            this.ppd.jComboBoxCod.setVisible(false);
            this.ppd.jComboBoxRuc.setVisible(true);
            this.ppd.jLabelCod.setVisible(false);
            this.ppd.jLabelQr.setVisible(false);
            this.ppd.jLabelMError.setVisible(false);
            this.ppd.jLabelNom.setVisible(true);
            this.ppd.jLabelRuc.setVisible(true);
            this.ppd.jTextFieldPnombre.setVisible(true);
        }
        if (e.getSource() == this.ppd.jButtonAccion && this.ppd.jButtonAccion.getText().equals("Agregar")) {
            try {
                if (this.ppd.jTextFieldPnombre.getText().length() <= 49 && this.ppd.jComboBoxRuc.getSelectedItem() != null) {
                    IProducto pdimpl = new ProductImpl();
                    Producto pd = new Producto();
                    pd.setNombre(this.ppd.jTextFieldPnombre.getText().trim());
                    pd.setRucProveedo(this.ppd.jComboBoxRuc.getSelectedItem().toString());
                    this.ppd.jLabelMError.setVisible(false);
                    this.ppd.jTextFieldPnombre.setText("");
                    pdimpl.crear(pd);
                    DefaultTableModel modelo = (DefaultTableModel) this.ppd.jTable1.getModel();
                    modelo.setRowCount(0);
                    this.ppd.jComboBoxCod.removeAllItems();
                    ArrayList<Producto> pdlist = pdimpl.listarTodo();
                    for (int i = 0; i < pdlist.size(); i++) {
                        modelo.addRow(new Object[]{pdlist.get(i).getCodigo(), pdlist.get(i).getNombre(), pdlist.get(i).getRucProveedo()});
                        this.ppd.jComboBoxCod.addItem(pdlist.get(i).getCodigo() + ": " + pdlist.get(i).getNombre());
                    }
                } else {
                    if (this.ppd.jTextFieldPnombre.getText().length() >= 49) {
                        this.ppd.jLabelMError.setVisible(true);
                    }
                    if (this.ppd.jComboBoxRuc.getSelectedItem() == null) {
                        JOptionPane.showMessageDialog(this.ppd, "No existen Proveedores");
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        }
        if (e.getSource() == this.ppd.jButtonEdi) {
            this.ppd.jButtonQR.setVisible(false);
            this.ppd.jButtonAccion.setText("Editar");
            this.ppd.jButtonAccion.setVisible(true);
            this.ppd.jComboBoxCod.setVisible(true);
            this.ppd.jComboBoxRuc.setVisible(true);
            this.ppd.jLabelCod.setVisible(true);
            this.ppd.jLabelQr.setVisible(false);
            this.ppd.jLabelNom.setVisible(true);
            this.ppd.jLabelRuc.setVisible(true);
            this.ppd.jTextFieldPnombre.setVisible(true);
        }
        if (e.getSource() == this.ppd.jButtonAccion && this.ppd.jButtonAccion.getText().equals("Editar")) {
            try {
                if (this.ppd.jTextFieldPnombre.getText().length() <= 49 && this.ppd.jComboBoxCod.getSelectedItem() != null
                        && this.ppd.jComboBoxRuc.getSelectedItem() != null) {
                    IProducto pdimpl = new ProductImpl();
                    Producto pd = new Producto();
                    Codigo cod = new Codigo();
                    pd.setCodigo(cod.getCode(this.ppd.jComboBoxCod.getSelectedItem().toString()));
                    pd.setNombre(this.ppd.jTextFieldPnombre.getText().trim());
                    pd.setRucProveedo(this.ppd.jComboBoxRuc.getSelectedItem().toString());
                    pdimpl.modificar(pd);
                    this.ppd.jTextFieldPnombre.setText("");
                    DefaultTableModel modelo = (DefaultTableModel) this.ppd.jTable1.getModel();
                    modelo.setRowCount(0);
                    this.ppd.jLabelMError.setVisible(false);
                    this.ppd.jComboBoxCod.removeAllItems();
                    ArrayList<Producto> pdlist = pdimpl.listarTodo();

                    for (int i = 0; i < pdlist.size(); i++) {
                        modelo.addRow(new Object[]{pdlist.get(i).getCodigo(), pdlist.get(i).getNombre(), pdlist.get(i).getRucProveedo()});
                        this.ppd.jComboBoxCod.addItem(pdlist.get(i).getCodigo() + ": " + pdlist.get(i).getNombre());
                    }
                } else {
                    if (this.ppd.jTextFieldPnombre.getText().length() >= 49) {
                        this.ppd.jLabelMError.setVisible(true);
                    }
                    if (this.ppd.jComboBoxRuc.getSelectedItem() == null) {
                        JOptionPane.showMessageDialog(this.ppd, "No existen Proveedores");
                    }
                    if (this.ppd.jComboBoxCod.getSelectedItem() == null) {
                        JOptionPane.showMessageDialog(this.ppd, "No existen Productos");
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        }
        if (e.getSource() == this.ppd.jButtonElimi) {
            this.ppd.jButtonQR.setVisible(false);
            this.ppd.jButtonAccion.setText("Borrar");
            this.ppd.jButtonAccion.setVisible(true);
            this.ppd.jComboBoxCod.setVisible(true);
            this.ppd.jComboBoxRuc.setVisible(false);
            this.ppd.jLabelCod.setVisible(true);
            this.ppd.jLabelQr.setVisible(false);
            this.ppd.jLabelMError.setVisible(false);
            this.ppd.jLabelNom.setVisible(false);
            this.ppd.jLabelRuc.setVisible(false);
            this.ppd.jTextFieldPnombre.setVisible(false);
        }
        if (e.getSource() == this.ppd.jButtonAccion && this.ppd.jButtonAccion.getText().equals("Borrar")) {
            try {
                KardexImpl ik = new KardexImpl();
                IProducto pdimpl = new ProductImpl();
                Codigo cod = new Codigo();
                if (this.ppd.jComboBoxCod.getSelectedItem() != null
                        && !ik.ProductoE(cod.getCode(this.ppd.jComboBoxCod.getSelectedItem().toString()))) {

                    pdimpl.borrar(cod.getCode(this.ppd.jComboBoxCod.getSelectedItem().toString()));
                    DefaultTableModel modelo = (DefaultTableModel) this.ppd.jTable1.getModel();
                    modelo.setRowCount(0);
                    this.ppd.jComboBoxCod.removeAllItems();
                    ArrayList<Producto> pdlist = pdimpl.listarTodo();
                    for (int i = 0; i < pdlist.size(); i++) {
                        modelo.addRow(new Object[]{pdlist.get(i).getCodigo(), pdlist.get(i).getNombre(), pdlist.get(i).getRucProveedo()});
                        this.ppd.jComboBoxCod.addItem(pdlist.get(i).getCodigo() + ": " + pdlist.get(i).getNombre());
                    }
                } else {
                    if (this.ppd.jComboBoxRuc.getSelectedItem() == null) {
                        JOptionPane.showMessageDialog(this.ppd, "No existen productos");
                    }
                    if (ik.ProductoE(cod.getCode(this.ppd.jComboBoxCod.getSelectedItem().toString()))) {
                        JOptionPane.showMessageDialog(this.ppd, "No se puede borrar\nProducto usado");
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        }
        if (e.getSource() == this.ppd.jButtonGenQR) {
            this.ppd.jButtonQR.setVisible(true);
            this.ppd.jButtonAccion.setVisible(false);
            this.ppd.jComboBoxCod.setVisible(true);
            this.ppd.jComboBoxRuc.setVisible(false);
            this.ppd.jLabelCod.setVisible(true);
            this.ppd.jLabelMError.setVisible(false);
            this.ppd.jLabelNom.setVisible(false);
            this.ppd.jLabelRuc.setVisible(false);
            this.ppd.jLabelQr.setVisible(true);
            this.ppd.jTextFieldPnombre.setVisible(false);
        }
    }
}
