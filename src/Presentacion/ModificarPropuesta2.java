/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Persistencia.DBPropuesta;
import Presentacion.inicio;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import logica.Clases.DtinfoPropuesta;
import logica.Fabrica;
import logica.Interfaces.IPropCat;

/**
 *
 * @author gabri
 */
public class ModificarPropuesta2 extends javax.swing.JInternalFrame {

    private IPropCat IPC;
    public DtinfoPropuesta dtp2;

    /**
     * Creates new form ModificarPropuesta2
     *
     * @param dtp
     */
    public ModificarPropuesta2(DtinfoPropuesta dtp) {
        initComponents();
        IPC = Fabrica.getInstance().getControladorPropCat();
        fecha.setMinSelectableDate(new Date());
        dtp2 = dtp;
        Calendar c = dtp2.getFechaReal();
        int dia = c.get(Calendar.DAY_OF_MONTH);
        int mes = c.get(Calendar.MONTH) + 1;
        int anio = c.get(Calendar.YEAR);
        this.TextFechaAnterior.setText(dia + "/" + mes + "/" + anio);
        this.TextLugarAnterior.setText(dtp2.getLugar());
        this.TextMontoTotAnterior.setText(String.valueOf(dtp2.getMonto()));
        this.TextPrecioAnterior.setText(String.valueOf(dtp2.getPrecio()));
        this.jTextAreaDescAnterior.setText(dtp2.getDescripcion());

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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        TextLugarAnterior = new javax.swing.JTextField();
        TextMonto = new javax.swing.JTextField();
        TextPrecio = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TextDesc = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        foto = new rojerusan.RSFotoCircle();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        fecha = new com.toedter.calendar.JDateChooser();
        TextLugar = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        TextMontoTotAnterior = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        TextPrecioAnterior = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        TextFechaAnterior = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaDescAnterior = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(570, 500));

        jPanel1.setMinimumSize(new java.awt.Dimension(570, 500));
        jPanel1.setPreferredSize(new java.awt.Dimension(570, 500));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Ingrese los campos que quiere modificar");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, -1, -1));

        jLabel2.setText("Lugar anterior:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, -1, -1));

        jLabel3.setText("Monto total:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, -1, -1));

        jLabel4.setText("Precio entrada:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, -1, -1));

        jLabel5.setText("Descripcion:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, -1, -1));

        TextLugarAnterior.setEditable(false);
        TextLugarAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextLugarAnteriorActionPerformed(evt);
            }
        });
        TextLugarAnterior.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextLugarAnteriorKeyTyped(evt);
            }
        });
        jPanel1.add(TextLugarAnterior, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 208, -1));

        TextMonto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextMontoActionPerformed(evt);
            }
        });
        TextMonto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextMontoKeyTyped(evt);
            }
        });
        jPanel1.add(TextMonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, 208, -1));

        TextPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextPrecioKeyTyped(evt);
            }
        });
        jPanel1.add(TextPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, 208, -1));

        TextDesc.setColumns(20);
        TextDesc.setLineWrap(true);
        TextDesc.setRows(5);
        TextDesc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextDescKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(TextDesc);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, 208, 100));

        jLabel6.setText("Fecha:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 260, -1, -1));
        jPanel1.add(foto, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 80, -1, -1));

        jLabel7.setText("Imagen:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 50, -1, -1));

        jButton1.setText("Volver");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 440, -1, -1));

        jButton2.setText("Salir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 440, -1, -1));

        jButton3.setText("Modificar Datos");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 440, -1, -1));

        fecha.setToolTipText("");
        jPanel1.add(fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 260, 210, -1));

        TextLugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextLugarActionPerformed(evt);
            }
        });
        TextLugar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextLugarKeyTyped(evt);
            }
        });
        jPanel1.add(TextLugar, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 208, -1));

        jLabel8.setText("Lugar:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, -1, -1));

        jLabel9.setText("Monto total anterior:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, 20));

        TextMontoTotAnterior.setEditable(false);
        TextMontoTotAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextMontoTotAnteriorActionPerformed(evt);
            }
        });
        TextMontoTotAnterior.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextMontoTotAnteriorKeyTyped(evt);
            }
        });
        jPanel1.add(TextMontoTotAnterior, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 208, -1));

        jLabel10.setText("Precio entrada anterior:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, 20));

        TextPrecioAnterior.setEditable(false);
        TextPrecioAnterior.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextPrecioAnteriorKeyTyped(evt);
            }
        });
        jPanel1.add(TextPrecioAnterior, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, 208, -1));

        jLabel11.setText("Fecha anterior:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, -1, -1));

        TextFechaAnterior.setEditable(false);
        TextFechaAnterior.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextFechaAnteriorKeyTyped(evt);
            }
        });
        jPanel1.add(TextFechaAnterior, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 230, 208, -1));

        jLabel12.setText("Descripcion anterior:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 300, -1, -1));

        jTextAreaDescAnterior.setEditable(false);
        jTextAreaDescAnterior.setColumns(20);
        jTextAreaDescAnterior.setRows(5);
        jScrollPane2.setViewportView(jTextAreaDescAnterior);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 320, 210, 100));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TextMontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextMontoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextMontoActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        ModificarPropuesta mp = new ModificarPropuesta();
        inicio.Escritorio.add(mp);
        this.setVisible(false);
        mp.toFront();
        mp.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String lugar = TextLugarAnterior.getText();
        String desc = TextDesc.getText();
        float monto = 0;
        if (TextMonto.getText().length() > 0) {
            monto = Float.parseFloat(TextMonto.getText());
            if (monto < this.dtp2.getMonto()) {
                JOptionPane.showMessageDialog(null, "Error: monto debe ser superior al existente");
            }
        }
        float precio = 0;
        if (TextPrecio.getText().length() > 0) {
            precio = Float.parseFloat(TextPrecio.getText());
        }

        Calendar fech = fecha.getCalendar();
        if (fech == null) {
            JOptionPane.showMessageDialog(null, "Ingrese una fecha valida");
        }

        String imagen = foto.getRutaImagen();
        DtinfoPropuesta dtp = new DtinfoPropuesta(dtp2.getTitulo(), desc, imagen, null, lugar, fech, precio, monto, null);
        boolean actualizo = IPC.ActualizarDatosPropuesta(dtp);
        if (actualizo) {
            JOptionPane.showMessageDialog(null, "Datos actualizados correctamente");
        } else {
            JOptionPane.showMessageDialog(null, "Error al actualizar");
        }
        TextDesc.setText("");
        TextLugarAnterior.setText("");
        TextMonto.setText("");
        TextPrecio.setText("");
        fecha.setCalendar(null);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void TextLugarAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextLugarAnteriorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextLugarAnteriorActionPerformed

    private void TextLugarAnteriorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextLugarAnteriorKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        String d = "" + c;
        if (Character.isLetter(c) || d.equals("\b") || d.equals(" ")) {

        } else {
            evt.consume();
        }
    }//GEN-LAST:event_TextLugarAnteriorKeyTyped

    private void TextDescKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextDescKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_TextDescKeyTyped

    private void TextMontoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextMontoKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        String d = "" + c;
        if (Character.isDigit(c) || d.equals("\b")) {

        } else {
            evt.consume();
        }
    }//GEN-LAST:event_TextMontoKeyTyped

    private void TextPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextPrecioKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        String d = "" + c;
        if (Character.isDigit(c) || d.equals("\b")) {

        } else {
            evt.consume();
        }
    }//GEN-LAST:event_TextPrecioKeyTyped

    private void TextLugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextLugarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextLugarActionPerformed

    private void TextLugarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextLugarKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_TextLugarKeyTyped

    private void TextMontoTotAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextMontoTotAnteriorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextMontoTotAnteriorActionPerformed

    private void TextMontoTotAnteriorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextMontoTotAnteriorKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_TextMontoTotAnteriorKeyTyped

    private void TextPrecioAnteriorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextPrecioAnteriorKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_TextPrecioAnteriorKeyTyped

    private void TextFechaAnteriorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextFechaAnteriorKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFechaAnteriorKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ModificarPropuesta2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModificarPropuesta2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModificarPropuesta2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModificarPropuesta2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModificarPropuesta2(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea TextDesc;
    private javax.swing.JTextField TextFechaAnterior;
    private javax.swing.JTextField TextLugar;
    private javax.swing.JTextField TextLugarAnterior;
    private javax.swing.JTextField TextMonto;
    private javax.swing.JTextField TextMontoTotAnterior;
    private javax.swing.JTextField TextPrecio;
    private javax.swing.JTextField TextPrecioAnterior;
    private com.toedter.calendar.JDateChooser fecha;
    private rojerusan.RSFotoCircle foto;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextAreaDescAnterior;
    // End of variables declaration//GEN-END:variables
}
