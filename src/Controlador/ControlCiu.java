/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Implementacion.CiudadImpl;
import Interface.ICiudad;
import Modelo.Ciudad;
import Recursos.Codigo;
import Recursos.ControlDatos;
import Vista.PanelCiudad;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mateo
 */
public class ControlCiu implements ActionListener {

    PanelCiudad pciu = new PanelCiudad();

    public ControlCiu(PanelCiudad pciu) {
        this.pciu = pciu;
        this.pciu.jButtonAccion.addActionListener(this);
        this.pciu.jButtonAd.addActionListener(this);
        this.pciu.jButtonEdit.addActionListener(this);
        this.pciu.jButtonElim.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        if (ev.getSource() == this.pciu.jButtonAd) {
            this.pciu.jButtonAccion.setVisible(true);
            this.pciu.jButtonAccion.setText("Agregar");
            this.pciu.jLabelC1.setVisible(false);
            this.pciu.jComboBoxC.setVisible(false);
            this.pciu.jLabelC2.setText("Nombre:");
            this.pciu.jLabelC2.setVisible(true);
            this.pciu.jTextFieldNom.setText("");
            this.pciu.jTextFieldNom.setVisible(true);
            this.pciu.jLabelC3.setText("Provincia:");
            this.pciu.jLabelC3.setVisible(true);
            this.pciu.jComboBoxP.setVisible(true);
        }
        if (ev.getSource() == this.pciu.jButtonAccion && this.pciu.jButtonAccion.getText().equals("Agregar")) {
            try {
                Codigo cd = new Codigo();
                ICiudad iciu = new CiudadImpl();
                Ciudad c = new Ciudad();
                ControlDatos cdt = new ControlDatos();
                c.setNombre(this.pciu.jTextFieldNom.getText().trim());
                c.setCodigoProv(cd.getCode(this.pciu.jComboBoxP.getSelectedItem().toString()));
                if (c.getNombre().length() <= 49 && !cdt.numeros(c.getNombre())
                        && !this.pciu.jComboBoxP.getSelectedItem().toString().equals("Provincias")
                        && !this.pciu.jTextFieldNom.getText().equals("")) {
                    iciu.crear(c);
                    DefaultTableModel modelo = (DefaultTableModel) this.pciu.jTableC.getModel();
                    modelo.setRowCount(0);
                    this.pciu.jComboBoxC.removeAllItems();
                    this.pciu.jComboBoxC.addItem("Ciudades");
                    this.pciu.jTextFieldNom.setText("");
                    ArrayList<Ciudad> clist = iciu.listarTodo();
                    for (int i = 0; i < clist.size(); i++) {
                        modelo.addRow(new Object[]{clist.get(i).getCodigo(), clist.get(i).getNombre(),
                            clist.get(i).getCodigoProv(), clist.get(i).getNombreProv()});
                        this.pciu.jComboBoxC.addItem(clist.get(i).getCodigo() + ": " + clist.get(i).getNombre());
                    }
                } else {
                    if (this.pciu.jComboBoxP.getItemCount() == 1) {
                        JOptionPane.showMessageDialog(null, "No existen Provincias");
                    }
                    if (c.getNombre().length() >= 49 || cdt.numeros(c.getNombre())
                            || this.pciu.jTextFieldNom.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Nombre inválido");
                    }

                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        }
        if (ev.getSource() == this.pciu.jButtonEdit) {
            this.pciu.jButtonAccion.setText("Editar");
            this.pciu.jButtonAccion.setVisible(true);
            this.pciu.jLabelC1.setText("Ciudad");
            this.pciu.jLabelC1.setVisible(true);
            this.pciu.jComboBoxC.setVisible(true);
            this.pciu.jLabelC2.setText("Cambiar nombre:");
            this.pciu.jLabelC2.setVisible(true);
            this.pciu.jTextFieldNom.setText("");
            this.pciu.jTextFieldNom.setVisible(true);
            this.pciu.jLabelC3.setText("Cambiar Provincia");
            this.pciu.jLabelC3.setVisible(true);
            this.pciu.jComboBoxP.setVisible(true);
        }
        if (ev.getSource() == this.pciu.jButtonAccion && this.pciu.jButtonAccion.getText().equals("Editar")) {

            Codigo cd = new Codigo();
            try {
                ICiudad iciu = new CiudadImpl();
                Ciudad c = new Ciudad();
                ControlDatos cdt = new ControlDatos();
                c.setCodigo(cd.getCode(this.pciu.jComboBoxC.getSelectedItem().toString()));
                c.setNombre(this.pciu.jTextFieldNom.getText().trim());
                c.setCodigoProv(cd.getCode(this.pciu.jComboBoxP.getSelectedItem().toString()));
                if (!this.pciu.jComboBoxC.getSelectedItem().toString().equals("Ciudades")
                        && c.getNombre().length() <= 49 && !cdt.numeros(c.getNombre())
                        && !this.pciu.jComboBoxP.getSelectedItem().toString().equals("Provincias")
                        && !this.pciu.jTextFieldNom.getText().equals("")) {
                    iciu.modificar(c);
                    DefaultTableModel modelo = (DefaultTableModel) this.pciu.jTableC.getModel();
                    modelo.setRowCount(0);
                    this.pciu.jComboBoxC.removeAllItems();
                    this.pciu.jComboBoxC.addItem("Ciudades");
                    this.pciu.jTextFieldNom.setText("");
                    ArrayList<Ciudad> clist = iciu.listarTodo();
                    for (int i = 0; i < clist.size(); i++) {
                        modelo.addRow(new Object[]{clist.get(i).getCodigo(), clist.get(i).getNombre(),
                            clist.get(i).getCodigoProv(), clist.get(i).getNombreProv()});
                        this.pciu.jComboBoxC.addItem(clist.get(i).getCodigo() + ": " + clist.get(i).getNombre());
                        this.pciu.jComboBoxP.addItem(clist.get(i).getCodigoProv() + ": " + clist.get(i).getNombreProv());
                    }
                } else {
                    if (this.pciu.jComboBoxC.getItemCount() == 1) {
                        JOptionPane.showMessageDialog(null, "No existen Ciudades");
                    }
                    if (c.getNombre().length() >= 49 || cdt.numeros(c.getNombre())
                            ||this.pciu.jTextFieldNom.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Nombre demasiado largo");
                    }
                    if (this.pciu.jComboBoxP.getItemCount() == 1) {
                        JOptionPane.showMessageDialog(null, "No existen Provincias");
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        }
        if (ev.getSource() == this.pciu.jButtonElim) {
            this.pciu.jButtonAccion.setText("Borrar");
            this.pciu.jButtonAccion.setVisible(true);
            this.pciu.jLabelC1.setVisible(true);
            this.pciu.jComboBoxC.setVisible(true);
            this.pciu.jLabelC2.setVisible(false);
            this.pciu.jTextFieldNom.setText("");
            this.pciu.jTextFieldNom.setVisible(false);
            this.pciu.jLabelC3.setVisible(false);
            this.pciu.jComboBoxP.setVisible(false);
        }
        if (ev.getSource() == this.pciu.jButtonAccion && this.pciu.jButtonAccion.getText().equals("Borrar")) {

            try {
                Codigo cd = new Codigo();
                ICiudad iciu = new CiudadImpl();
                if (!this.pciu.jComboBoxC.getSelectedItem().toString().equals("Ciudades")
                        && !iciu.ciuUsada(cd.getCode(this.pciu.jComboBoxC.getSelectedItem().toString()))) {

                    iciu.borrar(cd.getCode(this.pciu.jComboBoxC.getSelectedItem().toString()));
                    DefaultTableModel modelo = (DefaultTableModel) this.pciu.jTableC.getModel();
                    modelo.setRowCount(0);
                    this.pciu.jComboBoxC.removeAllItems();
                    this.pciu.jComboBoxC.addItem("Ciudades");
                    ArrayList<Ciudad> clist = iciu.listarTodo();
                    for (int i = 0; i < clist.size(); i++) {
                        modelo.addRow(new Object[]{clist.get(i).getCodigo(), clist.get(i).getNombre(),
                            clist.get(i).getCodigoProv(), clist.get(i).getNombreProv()});
                        this.pciu.jComboBoxC.addItem(clist.get(i).getCodigo() + ": " + clist.get(i).getNombre());
                    }
                } else {
                    if (this.pciu.jComboBoxC.getItemCount() == 1) {
                        JOptionPane.showMessageDialog(this.pciu, "No ha ingresado ciudades");
                    }
                    if (iciu.ciuUsada(cd.getCode(this.pciu.jComboBoxC.getSelectedItem().toString()))) {
                        JOptionPane.showMessageDialog(this.pciu, "No se puede borrar\n Pertenece a un cliente o proveedor");
                    }
                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        }
    }

}
