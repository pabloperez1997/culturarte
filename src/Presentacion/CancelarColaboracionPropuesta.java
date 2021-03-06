/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logica.Clases.DtColaboraciones;
import logica.Clases.DtNickTitProp;
import logica.Fabrica;
import logica.Interfaces.IPropCat;

/**
 *
 * @author Lucas
 */
public class CancelarColaboracionPropuesta extends javax.swing.JInternalFrame {

    /**
     * Creates new form CancelarColaboracionPropuesta
     */
    private IPropCat ICP;
    private int seleccion;
    private String titulo;

    public CancelarColaboracionPropuesta() {
        Fabrica fab = Fabrica.getInstance();
        this.ICP = fab.getControladorPropCat();
        initComponents();
        jPanel1.setVisible(false);
        this.llenatTabla();
        jButtonEliminar.setVisible(false);
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
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableColaboraciones = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePropuestas = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jTextPropuestas = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabelMisColaboraciones = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButtonEliminar = new javax.swing.JButton();

        setTitle("Cancelar colaboracion");

        jTableColaboraciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Monto", "Fecha Realizacion", "Colaborador"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableColaboraciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableColaboracionesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableColaboraciones);
        if (jTableColaboraciones.getColumnModel().getColumnCount() > 0) {
            jTableColaboraciones.getColumnModel().getColumn(0).setResizable(false);
            jTableColaboraciones.getColumnModel().getColumn(1).setResizable(false);
            jTableColaboraciones.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        jTablePropuestas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Titulo", "Categoria"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTablePropuestas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablePropuestasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTablePropuestas);
        if (jTablePropuestas.getColumnModel().getColumnCount() > 0) {
            jTablePropuestas.getColumnModel().getColumn(0).setResizable(false);
            jTablePropuestas.getColumnModel().getColumn(1).setResizable(false);
        }

        jLabel2.setText("Buscador");

        jTextPropuestas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextPropuestasActionPerformed(evt);
            }
        });
        jTextPropuestas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextPropuestasKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextPropuestasKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextPropuestas, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextPropuestas)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButton1.setText("Salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabelMisColaboraciones.setFont(new java.awt.Font("Consolas", 0, 24)); // NOI18N
        jLabelMisColaboraciones.setText("Colaboradociones");

        jLabel1.setFont(new java.awt.Font("Consolas", 0, 24)); // NOI18N
        jLabel1.setText("Propuestas");

        jButtonEliminar.setText("Eliminar");
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelMisColaboraciones)
                                .addGap(60, 60, 60))
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMisColaboraciones)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButtonEliminar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void llenatTabla() {
        List<DtNickTitProp> propuestas;
        propuestas = ICP.listarPropuestas();
        DefaultTableModel modelo = (DefaultTableModel) jTablePropuestas.getModel();
        modelo.setRowCount(0);
        Iterator it = propuestas.iterator();
        while (it.hasNext()) {
            DtNickTitProp prop = (DtNickTitProp) it.next();
            Object[] dat = {prop.getTituloP(), prop.getProponente()};
            modelo.addRow(dat);
        }

    }
    private void jTablePropuestasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePropuestasMouseClicked
        try {
            DefaultTableModel modelo = (DefaultTableModel) jTableColaboraciones.getModel();
            modelo.setRowCount(0);
            int seleccion = jTablePropuestas.rowAtPoint(evt.getPoint());
            String titulo = (String) jTablePropuestas.getValueAt(seleccion, 0);
            this.titulo = titulo;
            List<DtColaboraciones> colaboraciones = ICP.listarColaboraciones(titulo);
            jPanel1.setVisible(true);
            for (int i = 0; i < colaboraciones.size(); i++) {
                SimpleDateFormat d = new SimpleDateFormat("dd-MMM-yyyy");
                Date date = colaboraciones.get(i).getFechaRealiz().getTime();
                String fecha = d.format(date);
                Object[] dat = {colaboraciones.get(i).getMontoC(), fecha, colaboraciones.get(i).getUColaborador()};
                modelo.addRow(dat);
            }
            this.seleccion=seleccion;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_jTablePropuestasMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextPropuestasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextPropuestasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextPropuestasActionPerformed

    private void jTextPropuestasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextPropuestasKeyTyped

    }//GEN-LAST:event_jTextPropuestasKeyTyped

    private void jTextPropuestasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextPropuestasKeyReleased
        List<DtNickTitProp> propuestas = ICP.listarPropuestas();
        DefaultTableModel modelo = (DefaultTableModel) jTablePropuestas.getModel();
        modelo.setRowCount(0);
        for (int i = 0; i < propuestas.size(); i++) {
            DtNickTitProp p = propuestas.get(i);
            if (p.getTituloP().contains(jTextPropuestas.getText())) {
                Object[] dat = {p.getTituloP(), p.getProponente()};
                modelo.addRow(dat);
            }
        }
    }//GEN-LAST:event_jTextPropuestasKeyReleased

    private void jTableColaboracionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableColaboracionesMouseClicked
        jButtonEliminar.setVisible(true);
        this.seleccion = jTableColaboraciones.rowAtPoint(evt.getPoint());
    }//GEN-LAST:event_jTableColaboracionesMouseClicked

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        try{
        String nickColaborador = (String) jTableColaboraciones.getValueAt(this.seleccion, 2);
        float monto = (float) jTableColaboraciones.getValueAt(this.seleccion, 0);
        if (ICP.eliminarColaboracion(this.titulo, nickColaborador)) {
            JOptionPane.showMessageDialog(null, "Colaboracion eliminada con exito");
            this.jButtonEliminar.setVisible(false);
            
            DefaultTableModel modelo = (DefaultTableModel) jTableColaboraciones.getModel();
            modelo.setRowCount(0);
            String titulo = (String) jTablePropuestas.getValueAt(seleccion, 0);
            List<DtColaboraciones> colaboraciones = ICP.listarColaboraciones(titulo);
            for (int i = 0; i < colaboraciones.size(); i++) {
                SimpleDateFormat d = new SimpleDateFormat("dd-MMM-yyyy");
                Date date = colaboraciones.get(i).getFechaRealiz().getTime();
                String fecha = d.format(date);
                Object[] dat = {colaboraciones.get(i).getMontoC(), fecha, colaboraciones.get(i).getUColaborador()};
                modelo.addRow(dat);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error al eliminar esta colaboracion", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
        }catch(Exception ex){
            
        }
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelMisColaboraciones;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableColaboraciones;
    private javax.swing.JTable jTablePropuestas;
    private javax.swing.JTextField jTextPropuestas;
    // End of variables declaration//GEN-END:variables
}
