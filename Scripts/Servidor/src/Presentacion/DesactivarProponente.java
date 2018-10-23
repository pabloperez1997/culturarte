/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import java.awt.event.MouseAdapter;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import logica.Clases.DtUsuario;
import logica.Fabrica;

/**
 *
 * @author Martin
 */
public class DesactivarProponente extends javax.swing.JInternalFrame {

    private List<DtUsuario> listP;

    /**
     * Creates new form DesactivarProponente
     */
    public DesactivarProponente() {
        initComponents();

        List<DtUsuario> listProp = Fabrica.getInstance().getIControladorUsuario().ListarProponentes2();
        this.listP = listProp;
        DefaultTableModel modelo = (DefaultTableModel) jTableProponentes.getModel();
        modelo.setRowCount(0);

        for (int i = 0; i < listProp.size(); i++) {

            DtUsuario prop = (DtUsuario) listProp.get(i);
            Object[] datos = {prop.getNombre(), prop.getApellido(), prop.getNickName()};

            modelo.addRow(datos);
        }
        jTableProponentes.addMouseListener(new MouseAdapter() {
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableProponentes = new javax.swing.JTable();
        jButtonDesactivar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jTextFieldBuscar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldNick = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setTitle("DESACTIVAR PROPONENTE");

        jTableProponentes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nombre", "Apellido", "NickName"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTableProponentes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableProponentesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableProponentes);

        jButtonDesactivar.setText("Desactivar");
        jButtonDesactivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDesactivarActionPerformed(evt);
            }
        });

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jTextFieldBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldBuscarKeyReleased(evt);
            }
        });

        jLabel1.setText("Buscar:");

        jTextFieldNick.setEditable(false);

        jLabel2.setText("NickName:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonDesactivar)
                .addGap(58, 58, 58)
                .addComponent(jButtonCancelar)
                .addGap(58, 58, 58))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(40, 40, 40)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGap(156, 156, 156)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextFieldBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldNick, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNick, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonDesactivar)
                    .addComponent(jButtonCancelar))
                .addGap(43, 43, 43))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTableProponentesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableProponentesMouseClicked
        int row = jTableProponentes.rowAtPoint(evt.getPoint());
        jTextFieldNick.setText(jTableProponentes.getValueAt(row, 2).toString());

    }//GEN-LAST:event_jTableProponentesMouseClicked

    private void jButtonDesactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDesactivarActionPerformed

        try {
            boolean ok = Fabrica.getInstance().getIControladorUsuario().DesactivarProponente(jTextFieldNick.getText());
            if (ok) {
                javax.swing.JOptionPane.showMessageDialog(null, "El proponente fue desactivado");
                jTextFieldNick.setText("");
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
            jTextFieldNick.setText("");
        }

        List<DtUsuario> listProp = Fabrica.getInstance().getIControladorUsuario().ListarProponentes2();
        this.listP = listProp;
        DefaultTableModel modelo = (DefaultTableModel) jTableProponentes.getModel();
        modelo.setRowCount(0);

        for (int i = 0; i < listProp.size(); i++) {

            DtUsuario prop = (DtUsuario) listProp.get(i);
            Object[] datos = {prop.getNombre(), prop.getApellido(), prop.getNickName()};

            modelo.addRow(datos);
        }
    }//GEN-LAST:event_jButtonDesactivarActionPerformed

    private void jTextFieldBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscarKeyReleased
        DefaultTableModel model = (DefaultTableModel) jTableProponentes.getModel();

        model.setRowCount(0);

        for (int i = 0; i < jTableProponentes.getRowCount(); i++) {
            model.removeRow(i);
            i -= 1;
        }

        model = (DefaultTableModel) jTableProponentes.getModel();
        model.setRowCount(0);

        for (int i = 0; i < this.listP.size(); i++) {
            DtUsuario prop = (DtUsuario) this.listP.get(i);
            if ((!jTextFieldBuscar.getText().isEmpty()) && prop.getNickName().contains(jTextFieldBuscar.getText())) {
                Object[] dat = {prop.getNombre(), prop.getApellido(), prop.getNickName()};
                model.addRow(dat);
            } else if (jTextFieldBuscar.getText().isEmpty()) {
                Object[] dat = {prop.getNombre(), prop.getApellido(), prop.getNickName()};
                model.addRow(dat);
            }
        }
    }//GEN-LAST:event_jTextFieldBuscarKeyReleased

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonDesactivar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableProponentes;
    private javax.swing.JTextField jTextFieldBuscar;
    private javax.swing.JTextField jTextFieldNick;
    // End of variables declaration//GEN-END:variables
}
