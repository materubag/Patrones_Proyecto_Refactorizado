/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Implementacion.VentaImpl;
import Implementacion.ClientImpl;
import Implementacion.CompraImpl;
import Implementacion.KardexImpl;
import Implementacion.ProductImpl;
import Implementacion.ProveedorImpl;
import Interface.ICliente;
import Interface.ICompra;
import Interface.IKardex;
import Interface.IProveedor;
import Interface.IVenta;
import Modelo.Cliente;
import Modelo.Compra;
import Modelo.Kardex;
import Modelo.Producto;
import Modelo.Proveedor;
import Modelo.Venta;
import Recursos.Codigo;
import Recursos.ControlDatos;
import Recursos.QR;
import Vista.PanelCompraVenta;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mateo
 */
public class ControlCV implements ActionListener {

    PanelCompraVenta cv = new PanelCompraVenta();

    public ControlCV(PanelCompraVenta cv) {
        this.cv = cv;
        this.cv.jButtonAddCV.addActionListener(this);
        this.cv.jButtonCompra.addActionListener(this);
        this.cv.jButtonQR.addActionListener(this);
        this.cv.jButtonVenta.addActionListener(this);
        this.cv.jButtonSelectCV.addActionListener(this);
        this.cv.jComboBoxProducto.addActionListener(this);
        JTextField textField = (JTextField) this.cv.jDateChooser1.getDateEditor().getUiComponent();
        textField.setBackground(Color.lightGray);
        this.cv.jDateChooser1.getJCalendar().getYearChooser().getSpinner().setBackground(Color.LIGHT_GRAY);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.cv.jButtonQR) {
            try {
                QR qr = new QR();
                ClientImpl ci = new ClientImpl();
                this.cv.jLabelQr.setVisible(true);
                JTextField jdate = (JTextField) this.cv.jDateChooser1.getDateEditor().getUiComponent();
                ImageIcon ic = qr.generar("Cliente: " + this.cv.jComboBoxSelectPC.getSelectedItem()
                        + "\nNombre: " + ci.nombre(this.cv.jComboBoxSelectPC.getSelectedItem().toString().trim())
                        + "\nFecha: " + jdate.getText() + "\n" + this.cv.jLabelTotal.getText());
                this.cv.jLabelQr.setIcon(ic);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        if (e.getSource() == this.cv.jButtonCompra) {
            this.cv.jLabelProvClient.setText("Proveedor");
            this.cv.jLabelProvClient.setVisible(true);
            this.cv.jComboBoxSelectPC.setVisible(true);
            this.cv.jLabelFecha.setVisible(true);
            this.cv.jDateChooser1.setVisible(true);
            this.cv.jButtonSelectCV.setText("Comprar");
            this.cv.jButtonSelectCV.setVisible(true);
            this.cv.jButtonQR.setVisible(false);
            this.cv.jLabelErroPvCl.setVisible(false);
            this.cv.jLabelErrorFecha.setVisible(false);
            this.cv.jButtonAddCV.setVisible(false);
            this.cv.jButtonQR.setVisible(false);
            this.cv.jComboBoxProducto.setVisible(false);
            this.cv.jLabelCant.setVisible(false);
            this.cv.jLabelErrorCant.setVisible(false);
            this.cv.jLabelErrorPrecio.setVisible(false);
            this.cv.jLabelProd.setVisible(false);
            this.cv.jLabelprecio.setVisible(false);
            this.cv.jLabelTotal.setVisible(false);
            this.cv.jSpinnerInv.setVisible(false);
            this.cv.jLabel$.setVisible(false);
            this.cv.jTextFieldPrecio.setVisible(false);
            try {
                IProveedor pveImpl = new ProveedorImpl();
                ArrayList<Proveedor> pveL = pveImpl.listarTodo();
                this.cv.jComboBoxSelectPC.removeAllItems();
                if (pveL.isEmpty()) {
                    JOptionPane.showMessageDialog(this.cv, "No existen Proveedores");
                }
                JTextField jdate = (JTextField) this.cv.jDateChooser1.getDateEditor().getUiComponent();
                jdate.setEditable(false);
                this.cv.jComboBoxSelectPC.addItem("Proveedores");
                for (int i = 0; i < pveL.size(); i++) {
                    this.cv.jComboBoxSelectPC.addItem(pveL.get(i).getRuc());
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        }

        if (e.getSource() == this.cv.jButtonSelectCV && this.cv.jButtonSelectCV.getText().equals("Comprar")) {
            JTextField jdate = (JTextField) this.cv.jDateChooser1.getDateEditor().getUiComponent();

            if (!jdate.getText().equals("") && !this.cv.jComboBoxSelectPC.getSelectedItem().toString().equals("Proveedores")) {
                this.cv.jButtonAddCV.setText("Comprar");
                this.cv.jButtonAddCV.setVisible(true);
                this.cv.jButtonQR.setVisible(false);
                this.cv.jComboBoxProducto.setVisible(true);
                this.cv.jLabelCant.setVisible(true);
                this.cv.jLabel$.setVisible(true);
                this.cv.jLabelProd.setVisible(true);
                this.cv.jLabelprecio.setVisible(true);
                this.cv.jTable.setVisible(true);
                this.cv.jSpinnerInv.setVisible(true);
                this.cv.jTextFieldPrecio.setVisible(true);
                this.cv.jLabelProvClient.setVisible(false);
                this.cv.jComboBoxSelectPC.setVisible(false);
                this.cv.jLabelFecha.setVisible(false);
                this.cv.jDateChooser1.setVisible(false);
                this.cv.jButtonSelectCV.setVisible(false);
                this.cv.jLabelErroPvCl.setVisible(false);
                this.cv.jLabelErrorFecha.setVisible(false);
                DefaultTableModel modelo = (DefaultTableModel) this.cv.jTable.getModel();
                modelo.setRowCount(0);
                try {
                    ICompra ic = new CompraImpl();
                    Compra c = new Compra();
                    c.setFecha(jdate.getText());
                    c.setProveedor(this.cv.jComboBoxSelectPC.getSelectedItem().toString());
                    ic.crear(c);
                    ProductImpl ipr = new ProductImpl();
                    this.cv.jComboBoxProducto.removeAllItems();
                    ArrayList<String> pl = ipr.listarRucP(this.cv.jComboBoxSelectPC.getSelectedItem().toString());
                    if (pl.isEmpty()) {
                        JOptionPane.showMessageDialog(this.cv, "El Proveedor no tiene productos");
                    }
                    for (int i = 0; i < pl.size(); i++) {
                        this.cv.jComboBoxProducto.addItem(pl.get(i));
                    }
                    JTextField spinner = ((JSpinner.DefaultEditor) this.cv.jSpinnerInv.getEditor()).getTextField();
                    spinner.setEditable(false);
                    this.cv.jLabelErrorFecha.setVisible(false);
                    this.cv.jLabelErroPvCl.setVisible(false);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                if (jdate.getText().equals("")) {
                    this.cv.jLabelErrorFecha.setVisible(true);
                }
                if (this.cv.jComboBoxSelectPC.getSelectedItem().toString().equals("Proveedores")) {
                    this.cv.jLabelErroPvCl.setVisible(true);
                }
            }

        }

        if (e.getSource() == this.cv.jButtonAddCV && this.cv.jButtonAddCV.getText().equals("Comprar")) {
            try {
                CompraImpl ic = new CompraImpl();
                IKardex ik = new KardexImpl();
                Kardex k = new Kardex();
                Compra c = ic.compraActual();
                Codigo cod = new Codigo();
                ControlDatos cd = new ControlDatos();

                k.setProducto(cod.getCode(this.cv.jComboBoxProducto.getSelectedItem().toString()));
                k.setFecha(c.getFecha());

                k.setEntrada(Integer.parseInt(this.cv.jSpinnerInv.getValue().toString().trim()));
                k.setSalida(0);
                if (cd.decimal(this.cv.jTextFieldPrecio.getText()) 
                        && !this.cv.jTextFieldPrecio.getText().trim().equals("")) {
                    k.setCostoUnitario(Float.parseFloat(this.cv.jTextFieldPrecio.getText()));
                    this.cv.jLabelTotal.setVisible(true);
                    this.cv.jLabelErrorPrecio.setVisible(false);
                    this.cv.jLabelErrorCant.setVisible(false);
                    DefaultTableModel modelo = (DefaultTableModel) this.cv.jTable.getModel();
                    modelo.addRow(new Object[]{k.getProducto(), k.getEntrada(), "$" + k.getCostoUnitario()});
                    float total = 0;
                    total = c.getTotal() + (k.getEntrada() * k.getCostoUnitario());
                    c.setTotal(total);
                    this.cv.jLabelTotal.setText("Total: $" + total);
                    /*es necesario????????????????????????????????????????
                for (int i = 0; i < this.cv.jTable.getRowCount(); i++) {
                    for (int j = 0; j < this.cv.jTable.getColumnCount(); j++) {
                        c.setTotal(c.getTotal()+Float.parseFloat(this.cv.jTable.getValueAt(i, j).toString()));
                    }
                }*/

                    k.setInventario(ik.inventario(k.getProducto()));
                    k.setCodigoCompra(c.getCodigo());
                    k.setCodigoVenta(null);
                    k.setInventario(k.getInventario() + k.getEntrada());
                    ik.crear(k);
                    ic.modificar(c);
                    this.cv.jTextFieldPrecio.setText("");
                } else {
                    this.cv.jLabelErrorPrecio.setVisible(true);
                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
//--------------------------Venta-----------------------------------------------------
        if (e.getSource() == this.cv.jButtonVenta) {
            this.cv.jLabelProvClient.setText("Cliente");
            this.cv.jLabelProvClient.setVisible(true);
            this.cv.jComboBoxSelectPC.setVisible(true);
            this.cv.jLabelFecha.setVisible(true);
            this.cv.jDateChooser1.setVisible(true);
            this.cv.jButtonSelectCV.setText("Vender");
            this.cv.jButtonSelectCV.setVisible(true);
            this.cv.jLabelErroPvCl.setVisible(false);
            this.cv.jLabelErrorFecha.setVisible(false);
            
            this.cv.jButtonAddCV.setVisible(false);
            this.cv.jButtonQR.setVisible(false);
            this.cv.jComboBoxProducto.setVisible(false);
            this.cv.jLabelCant.setVisible(false);
            this.cv.jLabelErrorCant.setVisible(false);
            this.cv.jLabelErrorPrecio.setVisible(false);
            this.cv.jLabelProd.setVisible(false);
            this.cv.jLabelprecio.setVisible(false);
            this.cv.jLabelTotal.setVisible(false);
            this.cv.jSpinnerInv.setVisible(false);
            this.cv.jLabel$.setVisible(false);
            this.cv.jTextFieldPrecio.setVisible(false);
            try {
                JTextField jdate = (JTextField) this.cv.jDateChooser1.getDateEditor().getUiComponent();
                jdate.setEditable(false);
                ICliente icl = new ClientImpl();
                ArrayList<Cliente> clist = icl.listarTodo();
                this.cv.jComboBoxSelectPC.removeAllItems();
                if (clist.isEmpty()) {
                    JOptionPane.showMessageDialog(this.cv, "No existen Clientes");
                }

                this.cv.jComboBoxSelectPC.addItem("Clientes");
                for (int i = 0; i < clist.size(); i++) {
                    this.cv.jComboBoxSelectPC.addItem(clist.get(i).getCedula());
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        if (e.getSource() == this.cv.jButtonSelectCV && this.cv.jButtonSelectCV.getText().equals("Vender")) {
            JTextField jdate = (JTextField) this.cv.jDateChooser1.getDateEditor().getUiComponent();
            if (!jdate.getText().equals("") && !this.cv.jComboBoxSelectPC.getSelectedItem().toString().equals("Clientes")) {
                this.cv.jButtonAddCV.setText("Vender");
                this.cv.jButtonAddCV.setVisible(true);
                this.cv.jButtonQR.setVisible(false);
                this.cv.jComboBoxProducto.setVisible(true);
                this.cv.jLabelProd.setVisible(true);
                this.cv.jLabelprecio.setVisible(true);
                this.cv.jLabel$.setVisible(true);
                this.cv.jTable.setVisible(true);
                this.cv.jTextFieldPrecio.setVisible(true);
                this.cv.jLabelProvClient.setVisible(false);
                this.cv.jComboBoxSelectPC.setVisible(false);
                this.cv.jLabelFecha.setVisible(false);
                this.cv.jDateChooser1.setVisible(false);
                this.cv.jButtonSelectCV.setVisible(false);
                this.cv.jLabelCant.setVisible(true);
                this.cv.jSpinnerInv.setVisible(true);
                DefaultTableModel modelo = (DefaultTableModel) this.cv.jTable.getModel();
                modelo.setRowCount(0);
                try {
                    IVenta iv = new VentaImpl();
                    Venta v = new Venta();
                    v.setFecha(jdate.getText());
                    v.setCliente(this.cv.jComboBoxSelectPC.getSelectedItem().toString());
                    iv.crear(v);
                    ProductImpl ipr = new ProductImpl();
                    ArrayList<Producto> pl = ipr.listarTodo();
                    this.cv.jComboBoxProducto.removeAllItems();
                    if (pl.isEmpty()) {
                        JOptionPane.showMessageDialog(this.cv, "NO existen Productos");
                    }

                    for (int i = 0; i < pl.size(); i++) {
                        this.cv.jComboBoxProducto.addItem(pl.get(i).getCodigo() + ": " + pl.get(i).getNombre());
                    }
                    JTextField jspinner = ((JSpinner.DefaultEditor) this.cv.jSpinnerInv.getEditor()).getTextField();
                    jspinner.setEditable(false);
                    this.cv.jLabelErrorFecha.setVisible(false);
                    this.cv.jLabelErroPvCl.setVisible(false);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                if (jdate.getText().equals("")) {
                    this.cv.jLabelErrorFecha.setVisible(true);
                }
                if (this.cv.jComboBoxSelectPC.getSelectedItem().toString().equals("Clientes")) {
                    this.cv.jLabelErroPvCl.setVisible(true);
                }
            }
        }
        if (e.getSource() == this.cv.jButtonAddCV && this.cv.jButtonAddCV.getText().equals("Vender")) {
            try {
                VentaImpl iv = new VentaImpl();
                IKardex ik = new KardexImpl();
                Codigo cod = new Codigo();
                ControlDatos cd = new ControlDatos();
                Kardex k = new Kardex();
                Venta v = iv.ventaActual();
                k.setProducto(cod.getCode(this.cv.jComboBoxProducto.getSelectedItem().toString()));
                k.setFecha(v.getFecha());
                k.setEntrada(0);
                k.setSalida(Integer.parseInt(this.cv.jSpinnerInv.getValue().toString()));
                k.setInventario(ik.inventario(k.getProducto()));
                if (cd.decimal(this.cv.jTextFieldPrecio.getText()) && k.getSalida() <= k.getInventario()
                        && !this.cv.jTextFieldPrecio.getText().trim().equals("")) {
                    this.cv.jLabelTotal.setVisible(true);
                    this.cv.jLabelErrorPrecio.setVisible(false);
                    this.cv.jLabelErrorCant.setVisible(false);
                    k.setCostoUnitario(Float.parseFloat(this.cv.jTextFieldPrecio.getText()));
                    DefaultTableModel modelo = (DefaultTableModel) this.cv.jTable.getModel();
                    modelo.addRow(new Object[]{k.getProducto(), k.getSalida(), "$ " + k.getCostoUnitario()});
                    float total = 0;
                    total = v.getTotal() + (k.getSalida() * k.getCostoUnitario());
                    v.setTotal(total);
                    this.cv.jLabelTotal.setText("Total: $" + total);
                    /*es necesario????????????????????????????????????????
                for (int i = 0; i < this.cv.jTable.getRowCount(); i++) {
                    for (int j = 0; j < this.cv.jTable.getColumnCount(); j++) {
                        c.setTotal(c.getTotal()+Float.parseFloat(this.cv.jTable.getValueAt(i, j).toString()));
                    }
                }*/
                    k.setCodigoVenta(v.getCodigo());
                    k.setCodigoCompra(null);
                    k.setInventario(k.getInventario() - Integer.parseInt(this.cv.jSpinnerInv.getValue().toString()));
                    ik.crear(k);
                    iv.modificar(v);
                    this.cv.jButtonQR.setVisible(true);
                    this.cv.jLabelQr.setVisible(false);
                    this.cv.jTextFieldPrecio.setText("");
                } else {
                    if (!cd.decimal(this.cv.jTextFieldPrecio.getText())) {
                        this.cv.jLabelErrorPrecio.setVisible(true);
                    }
                    if (k.getSalida() >= k.getInventario()) {
                        this.cv.jLabelErrorCant.setVisible(true);
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        /*if (e.getSource() == this.cv.jComboBoxProducto && !this.cv.jComboBoxProducto.getSelectedItem().toString().equals("Productos")) {
            this.cv.jLabelCant.setVisible(false);
            this.cv.jSpinnerInv.setVisible(false);
            try {
                IKardex ik = new KardexImpl();
                Codigo cod = new Codigo();
                int max = ik.inventario(cod.getCode(this.cv.jComboBoxProducto.getSelectedItem().toString()));
                System.out.println("max "+max);
                if (max > 0) {
                    this.cv.jLabelCant.setVisible(true);
                    this.cv.jSpinnerInv.setVisible(true);
                    SpinnerNumberModel numberModel = (SpinnerNumberModel) this.cv.jSpinnerInv.getModel();
                    numberModel.setMaximum(max);//obtener stock
                } else {
                    JOptionPane.showMessageDialog(this.cv, "No hay Inventario del producto");
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }*/
    }
}
