/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista;

/**
 *
 * @author mateo
 */
public class PanelOpciones extends javax.swing.JPanel {

    /**
     * Creates new form Opciones
     */
    public PanelOpciones() {
        initComponents();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jButtonClient = new javax.swing.JButton();
        jButtonVerFactura = new javax.swing.JButton();
        jButtonProve = new javax.swing.JButton();
        jButtonKardex = new javax.swing.JButton();
        jButtonCompraV = new javax.swing.JButton();
        jButtonProd = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabelIMG = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1000, 720));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setForeground(new java.awt.Color(0, 0, 0));

        jButtonClient.setBackground(new java.awt.Color(0, 0, 0));
        jButtonClient.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 24)); // NOI18N
        jButtonClient.setForeground(new java.awt.Color(255, 255, 255));
        jButtonClient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/zIMG/account-multiple.png"))); // NOI18N
        jButtonClient.setText("Clientes");
        jButtonClient.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(0, 0, 0)));
        jButtonClient.setBorderPainted(false);
        jButtonClient.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonClient.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonClient.setIconTextGap(10);
        jButtonClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClientActionPerformed(evt);
            }
        });

        jButtonVerFactura.setBackground(new java.awt.Color(0, 0, 0));
        jButtonVerFactura.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 24)); // NOI18N
        jButtonVerFactura.setForeground(new java.awt.Color(255, 255, 255));
        jButtonVerFactura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/zIMG/calendar-plus.png"))); // NOI18N
        jButtonVerFactura.setText("Facturas");
        jButtonVerFactura.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(0, 0, 0)));
        jButtonVerFactura.setBorderPainted(false);
        jButtonVerFactura.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonVerFactura.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonVerFactura.setIconTextGap(10);
        jButtonVerFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerFacturaActionPerformed(evt);
            }
        });

        jButtonProve.setBackground(new java.awt.Color(0, 0, 0));
        jButtonProve.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 24)); // NOI18N
        jButtonProve.setForeground(new java.awt.Color(255, 255, 255));
        jButtonProve.setIcon(new javax.swing.ImageIcon(getClass().getResource("/zIMG/proveedor.png"))); // NOI18N
        jButtonProve.setText("Proveedor");
        jButtonProve.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(0, 0, 0)));
        jButtonProve.setBorderPainted(false);
        jButtonProve.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonProve.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonProve.setIconTextGap(10);
        jButtonProve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProveActionPerformed(evt);
            }
        });

        jButtonKardex.setBackground(new java.awt.Color(0, 0, 0));
        jButtonKardex.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 24)); // NOI18N
        jButtonKardex.setForeground(new java.awt.Color(255, 255, 255));
        jButtonKardex.setIcon(new javax.swing.ImageIcon(getClass().getResource("/zIMG/calendar-multiple-check.png"))); // NOI18N
        jButtonKardex.setText("Kardex");
        jButtonKardex.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(0, 0, 0)));
        jButtonKardex.setBorderPainted(false);
        jButtonKardex.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonKardex.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonKardex.setIconTextGap(10);
        jButtonKardex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKardexActionPerformed(evt);
            }
        });

        jButtonCompraV.setBackground(new java.awt.Color(0, 0, 0));
        jButtonCompraV.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 24)); // NOI18N
        jButtonCompraV.setForeground(new java.awt.Color(255, 255, 255));
        jButtonCompraV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/zIMG/carrito.png"))); // NOI18N
        jButtonCompraV.setText("Compra y Venta");
        jButtonCompraV.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(0, 0, 0)));
        jButtonCompraV.setBorderPainted(false);
        jButtonCompraV.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonCompraV.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonCompraV.setIconTextGap(10);
        jButtonCompraV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCompraVActionPerformed(evt);
            }
        });

        jButtonProd.setBackground(new java.awt.Color(0, 0, 0));
        jButtonProd.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 24)); // NOI18N
        jButtonProd.setForeground(new java.awt.Color(255, 255, 255));
        jButtonProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/zIMG/Productos.png"))); // NOI18N
        jButtonProd.setText("Productos");
        jButtonProd.setBorder(null);
        jButtonProd.setBorderPainted(false);
        jButtonProd.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonProd.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonProd.setIconTextGap(10);
        jButtonProd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonProdMouseEntered(evt);
            }
        });
        jButtonProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProdActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/zIMG/Toyota1.png"))); // NOI18N
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonCompraV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(112, 112, 112))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButtonClient, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButtonVerFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButtonProve, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButtonKardex, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButtonProd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonProd, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCompraV, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonClient, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonProve, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonVerFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonKardex, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(268, Short.MAX_VALUE))
        );

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 720));

        jLabelIMG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/zIMG/twpp.jpg"))); // NOI18N
        jLabelIMG.setMaximumSize(new java.awt.Dimension(10000, 10000));
        jLabelIMG.setMinimumSize(new java.awt.Dimension(10, 10));
        jLabelIMG.setPreferredSize(new java.awt.Dimension(10, 10));
        add(jLabelIMG, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 920, 720));
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClientActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonClientActionPerformed

    private void jButtonVerFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerFacturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonVerFacturaActionPerformed

    private void jButtonProveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonProveActionPerformed

    private void jButtonKardexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonKardexActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonKardexActionPerformed

    private void jButtonCompraVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCompraVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonCompraVActionPerformed

    private void jButtonProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonProdActionPerformed

    private void jButtonProdMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonProdMouseEntered
        
    }//GEN-LAST:event_jButtonProdMouseEntered


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton jButtonClient;
    public javax.swing.JButton jButtonCompraV;
    public javax.swing.JButton jButtonKardex;
    public javax.swing.JButton jButtonProd;
    public javax.swing.JButton jButtonProve;
    public javax.swing.JButton jButtonVerFactura;
    private javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabelIMG;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
