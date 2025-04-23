/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Implementacion.CompraImpl;
import Implementacion.KardexImpl;
import Implementacion.VentaImpl;
import Interface.IVenta;
import Modelo.Compra;
import Modelo.Kardex;
import Modelo.Venta;
import Vista.PFacturas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mateo
 */
public class ControlFact implements ActionListener {

    PFacturas ft = new PFacturas();

    public ControlFact(PFacturas ft) {
        this.ft = ft;
        this.ft.jButtonCompra.addActionListener(this);
        this.ft.jButtonVenta.addActionListener(this);
        this.ft.jButtonBorrar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.ft.jButtonCompra) {
            this.ft.jButtonBorrar.setText("Borrar Compra");
            try {
                CompraImpl ic = new CompraImpl();
                DefaultTableModel modelo = (DefaultTableModel) this.ft.jTable.getModel();
                modelo.setRowCount(0);
                modelo.setColumnIdentifiers(new Object[]{modelo.getColumnName(0), "Proveedor", modelo.getColumnName(2)});
                this.ft.jComboBoxFact.removeAllItems();
                this.ft.jComboBoxFact.addItem("Codigo de compra");
                this.ft.jButtonBorrar.setVisible(true);
                this.ft.jLabelSelect.setVisible(true);
                this.ft.jComboBoxFact.setVisible(true);
                this.ft.jLabelError.setVisible(false);
                ArrayList<Compra> cl = ic.listarTodo();
                if (cl.isEmpty()) {
                    JOptionPane.showMessageDialog(this.ft, "No existen Compras");
                }
                for (int i = 0; i < cl.size(); i++) {
                    if (cl.get(i).getTotal() == 0) {
                        ic.borrar(cl.get(i).getCodigo());
                    } else {
                        modelo.addRow(new Object[]{"Compra: " + cl.get(i).getCodigo(), cl.get(i).getProveedor(), "$ "+cl.get(i).getTotal()});
                        this.ft.jComboBoxFact.addItem(cl.get(i).getCodigo());
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        if (e.getSource() == this.ft.jButtonVenta) {
            this.ft.jButtonBorrar.setText("Borrar Venta");
            try {
                IVenta iv = new VentaImpl();
                DefaultTableModel modelo = (DefaultTableModel) this.ft.jTable.getModel();
                modelo.setRowCount(0);
                modelo.setColumnIdentifiers(new Object[]{modelo.getColumnName(0), "Cliente", modelo.getColumnName(2)});
                this.ft.jComboBoxFact.removeAllItems();
                this.ft.jComboBoxFact.addItem("Codigo de venta");
                this.ft.jButtonBorrar.setVisible(true);
                this.ft.jLabelSelect.setVisible(true);
                this.ft.jComboBoxFact.setVisible(true);
                this.ft.jLabelError.setVisible(false);
                ArrayList<Venta> vl = iv.listarTodo();
                if (vl.isEmpty()) {
                    JOptionPane.showMessageDialog(this.ft, "No existen Ventas");
                }
                for (int i = 0; i < vl.size(); i++) {
                    if (vl.get(i).getTotal() == 0) {
                        iv.borrar(vl.get(i).getCodigo());
                    } else {
                        modelo.addRow(new Object[]{"Venta: " + vl.get(i).getCodigo(), vl.get(i).getCliente(), "$ "+vl.get(i).getTotal()});
                        this.ft.jComboBoxFact.addItem(vl.get(i).getCodigo());
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        if (e.getSource() == this.ft.jButtonBorrar && this.ft.jButtonBorrar.getText().equals("Borrar Compra")) {
            try {
                if (this.ft.jComboBoxFact.getSelectedItem().toString().equals("Codigo de compra")) {
                    this.ft.jLabelError.setVisible(true);
                } else {
                    CompraImpl ic = new CompraImpl();
                    KardexImpl ik = new KardexImpl();

                    ic.borrar(this.ft.jComboBoxFact.getSelectedItem().toString());
                    ArrayList<Kardex> kl = ik.listaCompra(this.ft.jComboBoxFact.getSelectedItem().toString());
                    for (int i = 0; i < kl.size(); i++) {
                        kl.get(i).setSalida(kl.get(i).getEntrada());
                        kl.get(i).setEntrada(0);
                        kl.get(i).setInventario(ik.inventario(kl.get(i).getProducto()) - kl.get(i).getSalida());
                        ik.crear(kl.get(i));
                    }
                    DefaultTableModel modelo = (DefaultTableModel) this.ft.jTable.getModel();
                    modelo.setRowCount(0);
                    modelo.setColumnIdentifiers(new Object[]{modelo.getColumnName(0), "Proveedor", modelo.getColumnName(2)});
                    this.ft.jComboBoxFact.removeAllItems();
                    this.ft.jComboBoxFact.addItem("Codigo de Compra");
                    this.ft.jLabelError.setVisible(false);
                    ArrayList<Compra> cl = ic.listarTodo();
                    for (int i = 0; i < cl.size(); i++) {
                        modelo.addRow(new Object[]{"Compra: " + cl.get(i).getCodigo(), cl.get(i).getProveedor(), "$ "+cl.get(i).getTotal()});
                        this.ft.jComboBoxFact.addItem(cl.get(i).getCodigo());
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

        if (e.getSource() == this.ft.jButtonBorrar && this.ft.jButtonBorrar.getText().equals("Borrar Venta")) {
            try {
                if (this.ft.jComboBoxFact.getSelectedItem().toString().equals("Codigo de venta")) {
                    this.ft.jLabelError.setVisible(true);
                } else {
                    IVenta iv = new VentaImpl();
                    KardexImpl ik = new KardexImpl();
                    iv.borrar(this.ft.jComboBoxFact.getSelectedItem().toString());
                    ArrayList<Kardex> kl = ik.listaVenta(this.ft.jComboBoxFact.getSelectedItem().toString());
                    for (int i = 0; i < kl.size(); i++) {
                        kl.get(i).setEntrada(kl.get(i).getSalida());
                        kl.get(i).setSalida(0);
                        kl.get(i).setInventario(ik.inventario(kl.get(i).getProducto()) + kl.get(i).getEntrada());
                        ik.crear(kl.get(i));
                    }
                    DefaultTableModel modelo = (DefaultTableModel) this.ft.jTable.getModel();
                    modelo.setRowCount(0);
                    modelo.setColumnIdentifiers(new Object[]{modelo.getColumnName(0), "Cliente", modelo.getColumnName(2)});
                    this.ft.jComboBoxFact.removeAllItems();
                    this.ft.jComboBoxFact.addItem("Codigo de venta");
                    this.ft.jLabelError.setVisible(false);
                    ArrayList<Venta> vl = iv.listarTodo();
                    for (int i = 0; i < vl.size(); i++) {
                        modelo.addRow(new Object[]{"Venta: " + vl.get(i).getCodigo(), vl.get(i).getCliente(), "$ "+vl.get(i).getTotal()});
                        this.ft.jComboBoxFact.addItem(vl.get(i).getCodigo());
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        }
    }
}
