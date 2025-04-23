/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Implementacion.ProvinciaImpl;
import Interface.IProvincia;
import Modelo.Provincia;
import Recursos.Codigo;
import Recursos.ControlDatos;
import Vista.PanelProvincia;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mateo
 */
public class ControlProvin implements ActionListener {

    PanelProvincia pprov = new PanelProvincia();

    public ControlProvin(PanelProvincia pprov) {
        this.pprov = pprov;
        this.pprov.jButtonEdit.addActionListener(this);
        this.pprov.jButtonAd.addActionListener(this);
        this.pprov.jButtonAccion.addActionListener(this);
        this.pprov.jButtonElim.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.pprov.jButtonAd) {
            this.pprov.jButtonAccion.setText("Agregar");
            this.pprov.jLabelMError.setVisible(false);
            this.pprov.jButtonAccion.setVisible(true);
            this.pprov.jLabelP1.setVisible(false);
            this.pprov.jComboBoxP.setVisible(false);
            this.pprov.jLabelP2.setText("Nombre:");
            this.pprov.jLabelP2.setVisible(true);
            this.pprov.jTextFieldPnombre.setText("");
            this.pprov.jTextFieldPnombre.setVisible(true);

        }
        if (e.getSource() == this.pprov.jButtonAccion && this.pprov.jButtonAccion.getText().equals("Agregar")) {
            try {
                IProvincia iprov = new ProvinciaImpl();
                Provincia pv = new Provincia();
                ControlDatos cdt = new ControlDatos();
                pv.setNombre(this.pprov.jTextFieldPnombre.getText().trim());
                if (pv.getNombre().length() <= 49 && !cdt.numeros(pv.getNombre())
                        && !pv.getNombre().equals("")) {
                    iprov.crear(pv);
                    this.pprov.jTextFieldPnombre.setText("");
                    DefaultTableModel modelo = (DefaultTableModel) this.pprov.jTable1.getModel();
                    modelo.setRowCount(0);
                    this.pprov.jComboBoxP.removeAllItems();
                    this.pprov.jComboBoxP.addItem("Provincias");
                    ArrayList<Provincia> plist = iprov.listarTodo();
                    for (int i = 0; i < plist.size(); i++) {
                        modelo.addRow(new Object[]{plist.get(i).getCodigo(), plist.get(i).getNombre()});
                        this.pprov.jComboBoxP.addItem(plist.get(i).getCodigo() + ": " + plist.get(i).getNombre());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Nombre inválido");
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        }
        if (e.getSource() == this.pprov.jButtonEdit) {
            this.pprov.jLabelMError.setVisible(false);
            this.pprov.jButtonAccion.setText("Editar");
            this.pprov.jButtonAccion.setVisible(true);
            this.pprov.jLabelP1.setText("Provincia actual:");
            this.pprov.jLabelP1.setVisible(true);
            this.pprov.jComboBoxP.setVisible(true);
            this.pprov.jLabelP2.setVisible(true);
            this.pprov.jLabelP2.setText("Nombre nuevo:");
            this.pprov.jTextFieldPnombre.setText("");
            this.pprov.jTextFieldPnombre.setVisible(true);
        }
        if (e.getSource() == this.pprov.jButtonAccion && this.pprov.jButtonAccion.getText().equals("Editar")) {
            try {
                IProvincia iprov = new ProvinciaImpl();
                Provincia pv = new Provincia();
                Codigo cd = new Codigo();
                ControlDatos cdt = new ControlDatos();
                pv.setCodigo(cd.getCode(this.pprov.jComboBoxP.getSelectedItem().toString()));
                pv.setNombre(this.pprov.jTextFieldPnombre.getText().trim());
                if (this.pprov.jComboBoxP.getItemCount() == 1) {
                    JOptionPane.showMessageDialog(this.pprov, "No  existen provincias");
                } else if (pv.getNombre().length() >= 49 || cdt.numeros(pv.getNombre())
                        || pv.getNombre().equals("")) {
                    JOptionPane.showMessageDialog(this.pprov, "Nombre invalido");
                } else if(!this.pprov.jComboBoxP.getSelectedItem().toString().equals("Provincias")){
                    iprov.modificar(pv);
                    this.pprov.jTextFieldPnombre.setText("");
                    DefaultTableModel modelo = (DefaultTableModel) this.pprov.jTable1.getModel();
                    modelo.setRowCount(0);
                    this.pprov.jComboBoxP.removeAllItems();
                    this.pprov.jComboBoxP.addItem("Provincias");
                    ArrayList<Provincia> plist = iprov.listarTodo();
                    for (int i = 0; i < plist.size(); i++) {
                        modelo.addRow(new Object[]{plist.get(i).getCodigo(), plist.get(i).getNombre()});
                        this.pprov.jComboBoxP.addItem(plist.get(i).getCodigo() + ": " + plist.get(i).getNombre());
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        if (e.getSource() == this.pprov.jButtonElim) {
            this.pprov.jButtonAccion.setText("Borrar");
            this.pprov.jButtonAccion.setVisible(true);
            this.pprov.jLabelMError.setVisible(true);
            this.pprov.jLabelP1.setText("Provincia a borrar:");
            this.pprov.jLabelP1.setVisible(true);
            this.pprov.jComboBoxP.setVisible(true);
            this.pprov.jLabelP2.setVisible(false);
            this.pprov.jTextFieldPnombre.setVisible(false);
        }
        if (e.getSource() == this.pprov.jButtonAccion && this.pprov.jButtonAccion.getText().equals("Borrar")) {
            try {
                IProvincia iprov = new ProvinciaImpl();
                Codigo cd = new Codigo();
                if (this.pprov.jComboBoxP.getItemCount() == 1) {
                    JOptionPane.showMessageDialog(this.pprov, "No  existen provincias");
                } else if (!iprov.provUsada(cd.getCode(this.pprov.jComboBoxP.getSelectedItem().toString()))
                        && !this.pprov.jComboBoxP.getSelectedItem().toString().equals("Provincias")) {
                    iprov.borrar(cd.getCode(this.pprov.jComboBoxP.getSelectedItem().toString()));
                    iprov.delCiuAdjunta(cd.getCode(this.pprov.jComboBoxP.getSelectedItem().toString()));
                    DefaultTableModel modelo = (DefaultTableModel) this.pprov.jTable1.getModel();
                    modelo.setRowCount(0);
                    this.pprov.jComboBoxP.removeAllItems();
                    this.pprov.jComboBoxP.addItem("Provincias");
                    ArrayList<Provincia> plist = iprov.listarTodo();
                    for (int i = 0; i < plist.size(); i++) {
                        modelo.addRow(new Object[]{plist.get(i).getCodigo(), plist.get(i).getNombre()});
                        this.pprov.jComboBoxP.addItem(plist.get(i).getCodigo() + ": " + plist.get(i).getNombre());
                    }
                } else if (iprov.provUsada(cd.getCode(this.pprov.jComboBoxP.getSelectedItem().toString()))) {
                    JOptionPane.showMessageDialog(null, "No se puede borrar\nPertenece a un cliente o proveedor");
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

}
