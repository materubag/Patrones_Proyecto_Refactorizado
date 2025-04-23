/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Implementacion.KardexImpl;
import Interface.IKardex;
import Modelo.Kardex;
import Recursos.Codigo;
import Vista.PKardex;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mateo
 */
public class ControlKardex implements ActionListener {

    PKardex pk = new PKardex();

    public ControlKardex(PKardex pk) {
        this.pk = pk;
        this.pk.jComboBoxPd.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.pk.jComboBoxPd && this.pk.jComboBoxPd.getSelectedItem() != null
                && !this.pk.jComboBoxPd.getSelectedItem().toString().equals("Productos")) {
            try {
                DefaultTableModel modelo = (DefaultTableModel) this.pk.jTable1.getModel();
                modelo.setRowCount(0);
                IKardex ik = new KardexImpl();
                Codigo cd = new Codigo();
                ArrayList<Kardex> kl = ik.listarProducto(cd.getCode(this.pk.jComboBoxPd.getSelectedItem().toString()));
                if (kl.isEmpty()) {
                    JOptionPane.showMessageDialog(this.pk, "No hay historial del producto");
                } else {
                    for (int i = 0; i < kl.size(); i++) {
                        if (kl.get(i).getCodigoC() == null) {
                            kl.get(i).setCodigoC("Venta: " + kl.get(i).getCodigoV());
                        } else {
                            kl.get(i).setCodigoC("Compra: " + kl.get(i).getCodigoC());
                        }
                        String entrada, salida;
                        if (kl.get(i).getEntrada() == 0) {
                            entrada = "";
                        } else {
                            entrada = String.valueOf(kl.get(i).getEntrada());
                        }
                        if (kl.get(i).getSalida() == 0) {
                            salida = "";
                        } else {
                            salida = String.valueOf(kl.get(i).getSalida());
                        }
                        
                        modelo.addRow(new Object[]{kl.get(i).getCodigo(), kl.get(i).getFecha(), kl.get(i).getCodigoC(),
                            "$ " + kl.get(i).getCostoU(), entrada, salida, kl.get(i).getInventario()});
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

}
