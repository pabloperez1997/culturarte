/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import java.awt.Image;
import java.util.Calendar;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logica.Clases.DtinfoColaborador;
import logica.Fabrica;
import logica.Interfaces.IControladorUsuario;

/**
 *
 * @author gabri
 */
public class ConsultarPerfilColaborador extends javax.swing.JInternalFrame {

    private IControladorUsuario ICU;

    public ConsultarPerfilColaborador() {
        initComponents();
        this.ICU = Fabrica.getInstance().getIControladorUsuario();
        List<DtinfoColaborador> listaC = ICU.ListarColaboradores();
        DefaultTableModel modelo = (DefaultTableModel) TablaC.getModel();
        modelo.setRowCount(0);
        for (int i = 0; i < listaC.size(); i++) {
            DtinfoColaborador dtc = (DtinfoColaborador) listaC.get(i);
            Object[] dat = {dtc.getNickname(), dtc.getNombre(), dtc.getApellido()};
            modelo.addRow(dat);
        }
//ImageIcon img=new ImageIcon()
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
        NombreCol = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaC = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        Textnick = new javax.swing.JTextField();
        textnom = new javax.swing.JTextField();
        textape = new javax.swing.JTextField();
        textcorreo = new javax.swing.JTextField();
        textfecha = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        foto = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consultar perfil de colaborador");
        setPreferredSize(new java.awt.Dimension(580, 580));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setAlignmentX(600.0F);
        jPanel1.setMinimumSize(new java.awt.Dimension(600, 650));
        jPanel1.setPreferredSize(new java.awt.Dimension(600, 600));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        NombreCol.setToolTipText("");
        NombreCol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NombreColActionPerformed(evt);
            }
        });
        NombreCol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                NombreColKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NombreColKeyTyped(evt);
            }
        });
        jPanel1.add(NombreCol, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 280, -1));

        TablaC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "NickName", "Nombre", "Apellido"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaCMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TablaC);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 350, 120));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Datos del colaborador");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 240, -1, -1));

        jButton1.setText("Salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 470, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Buscar:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, -1, -1));

        Textnick.setEditable(false);
        Textnick.setToolTipText("");
        Textnick.setBorder(null);
        Textnick.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextnickActionPerformed(evt);
            }
        });
        jPanel1.add(Textnick, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 270, 200, -1));

        textnom.setEditable(false);
        textnom.setBorder(null);
        jPanel1.add(textnom, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 300, 200, -1));

        textape.setEditable(false);
        textape.setBorder(null);
        jPanel1.add(textape, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 330, 200, -1));

        textcorreo.setEditable(false);
        textcorreo.setBorder(null);
        jPanel1.add(textcorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 360, 200, -1));

        textfecha.setEditable(false);
        textfecha.setBorder(null);
        jPanel1.add(textfecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 390, 200, -1));

        jLabel5.setText("Nickname:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 270, -1, -1));

        jLabel6.setText("Nombre:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 300, -1, -1));

        jLabel7.setText("Apellido:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 330, -1, -1));

        jLabel8.setText("Correo:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 360, -1, -1));

        jLabel9.setText("Fecha de nacimiento:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 390, -1, -1));

        jButton2.setText("Ver colaboraciones");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 430, 140, -1));
        jPanel1.add(foto, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 270, 140, 140));

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NombreColActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NombreColActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NombreColActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        ICU.resetearColaborador();
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void TablaCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaCMouseClicked
        // TODO add your handling code here:

        Textnick.setText("");
        textape.setText("");
        textcorreo.setText("");
        textnom.setText("");
        textfecha.setText("");
        DtinfoColaborador dtc = ICU.verPerfil((String) TablaC.getValueAt(TablaC.getSelectedRow(), 0));
        Calendar c = dtc.getFechaN();
        int dia = c.get(Calendar.DAY_OF_MONTH);
        int mes = c.get(Calendar.MONTH) + 1;
        int anio = c.get(Calendar.YEAR);
        Textnick.setText(dtc.getNickname());
        textape.setText(dtc.getApellido());
        textcorreo.setText(dtc.getCorreo());
        textnom.setText(dtc.getNombre());
        textfecha.setText(dia + "/" + mes + "/" + anio);
        String fotos = System.getProperty("user.dir") + "\\fPerfiles\\" + dtc.getImagen();
        ImageIcon fot = new ImageIcon(fotos);
        Icon icono = new ImageIcon(fot.getImage().getScaledInstance(foto.getWidth(), foto.getHeight(), Image.SCALE_DEFAULT));
        foto.setIcon(icono);


    }//GEN-LAST:event_TablaCMouseClicked

    private void NombreColKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NombreColKeyReleased
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        String d = "" + c;
        if (Character.isLetter(c) || d.equals("\b")) {
            String nick = NombreCol.getText();
            List<DtinfoColaborador> listaC = ICU.BuscarColaborador(nick);
            DefaultTableModel modelo = (DefaultTableModel) TablaC.getModel();
            modelo.setRowCount(0);
            for (int i = 0; i < listaC.size(); i++) {
                DtinfoColaborador dtc = (DtinfoColaborador) listaC.get(i);
                Object[] dat = {dtc.getNickname(), dtc.getNombre(), dtc.getApellido()};
                modelo.addRow(dat);
            }
        } else {
            evt.consume();
        }
    }//GEN-LAST:event_NombreColKeyReleased

    private void NombreColKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NombreColKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        String d = "" + c;
        if (Character.isLetter(c) || d.equals("\b")) {

        } else {
            evt.consume();
        }
    }//GEN-LAST:event_NombreColKeyTyped

    private void TextnickActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextnickActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextnickActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if (ICU.getDtColaborador().getNickname().equals("")) {
            JOptionPane.showMessageDialog(null, "Porfavor seleccione un colaborador");
            return;
        }

        try {
            ConsultarPerfilColaborador2 cpc = new ConsultarPerfilColaborador2();
            inicio.Escritorio.add(cpc);
            this.setVisible(false);
            cpc.toFront();
            cpc.setVisible(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(ConsultarPerfilColaborador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsultarPerfilColaborador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsultarPerfilColaborador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultarPerfilColaborador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConsultarPerfilColaborador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField NombreCol;
    private javax.swing.JTable TablaC;
    private javax.swing.JTextField Textnick;
    private javax.swing.JLabel foto;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField textape;
    private javax.swing.JTextField textcorreo;
    private javax.swing.JTextField textfecha;
    private javax.swing.JTextField textnom;
    // End of variables declaration//GEN-END:variables
}
