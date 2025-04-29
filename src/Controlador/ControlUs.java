/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Implementacion.UserImpl;
import Interface.IUsuario;
import Modelo.Usuario;
import Recursos.Encriptar;
import Recursos.Permisos;
import Vista.PanelUsuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author mateo
 */
public class ControlUs implements ActionListener {

    PanelUsuario pu = new PanelUsuario();

    public ControlUs(PanelUsuario pu) {
        this.pu = pu;
        this.pu.jButtonCrear.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.pu.jButtonCrear && !this.pu.jTextFieldNom.getText().equals("")
                && !this.pu.jTextFieldNom.getText().equals("Ingrese su nombre de usuario")
                && !this.pu.jPasswordFieldClave.getText().equals("") && !this.pu.jPasswordFieldClave.getText().equals("               ")
                && !this.pu.jPasswordFieldCopia.getText().equals("") && !this.pu.jPasswordFieldCopia.getText().equals("               ")) {
            try {
                IUsuario iu = new UserImpl();
                Usuario us = new Usuario();
                Encriptar enc = new Encriptar();
                us.setNombre(this.pu.jTextFieldNom.getText());
                us.setEstado("activo");
                us.setVerif(iu.verificarUsuario(us.getNombre()).isVerif());
                if (this.pu.jPasswordFieldClave.getText().equals(this.pu.jPasswordFieldCopia.getText())
                        && !us.isVerif() && this.pu.jPasswordFieldClave.getText().trim().length() <= 30
                        && (this.pu.jCheckBoxCV.isSelected() || this.pu.jCheckBoxCiu.isSelected()
                        || this.pu.jCheckBoxCli.isSelected() || this.pu.jCheckBoxFact.isSelected()
                        || this.pu.jCheckBoxKardex.isSelected() || this.pu.jCheckBoxProduc.isSelected()
                        || this.pu.jCheckBoxProv.isSelected() || this.pu.jCheckBoxProvee.isSelected()
                        || this.pu.jCheckBoxUser.isSelected())) {
                    us.setClave(enc.encriptar(this.pu.jPasswordFieldClave.getText().trim()));
                    ArrayList<Boolean> bl = new ArrayList<Boolean>();
                    bl.add(this.pu.jCheckBoxProv.isSelected());
                    bl.add(this.pu.jCheckBoxCiu.isSelected());
                    bl.add(this.pu.jCheckBoxUser.isSelected());
                    bl.add(this.pu.jCheckBoxCli.isSelected());
                    bl.add(this.pu.jCheckBoxProduc.isSelected());
                    bl.add(this.pu.jCheckBoxProvee.isSelected());
                    bl.add(this.pu.jCheckBoxFact.isSelected());
                    bl.add(this.pu.jCheckBoxKardex.isSelected());
                    bl.add(this.pu.jCheckBoxCV.isSelected());
                    Permisos per = new Permisos();
                    us.setPermisos(per.Generar(bl));
                    iu.crear(us);
                    this.pu.jLabelMErrorclave.setVisible(false);
                    this.pu.jLabelMErrorUser.setVisible(false);
                    this.pu.jLabelMErrorPer.setVisible(false);
                    this.pu.jLabelMErrorclave1.setVisible(false);
                    this.pu.jTextFieldNom.setText("Ingrese su nombre de usuario");
                    this.pu.jPasswordFieldClave.setText("               ");
                    this.pu.jPasswordFieldCopia.setText("               ");
                    JOptionPane.showMessageDialog(this.pu, "Usuario registrado");
                } else {
                    if (!this.pu.jPasswordFieldClave.getText().equals(this.pu.jPasswordFieldCopia.getText())) {
                        this.pu.jLabelMErrorclave.setVisible(true);
                    }
                    if (us.isVerif()) {
                        this.pu.jLabelMErrorUser.setVisible(true);
                    }
                    if (this.pu.jPasswordFieldClave.getText().trim().length() >= 30) {
                        this.pu.jLabelMErrorclave1.setVisible(true);
                    }
                    if (!(this.pu.jCheckBoxCV.isSelected() || this.pu.jCheckBoxCiu.isSelected()
                            || this.pu.jCheckBoxCli.isSelected() || this.pu.jCheckBoxFact.isSelected()
                            || this.pu.jCheckBoxKardex.isSelected() || this.pu.jCheckBoxProduc.isSelected()
                            || this.pu.jCheckBoxProv.isSelected() || this.pu.jCheckBoxProvee.isSelected()
                            || this.pu.jCheckBoxUser.isSelected())) {
                        this.pu.jLabelMErrorPer.setVisible(true);
                    }
                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this.pu, "LLene todos los campos");
        }

    }
}
