/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frame;

import db.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.Mobil;

/**
 *
 * @author whois
 */
public class MobilFrame extends javax.swing.JFrame {

    Mobil mobil;

    /**
     * Creates new form MobilFrame
     */
    public MobilFrame() {
        initComponents();
        setLocationRelativeTo(null);
        resetTable("");
    }

    public ArrayList<Mobil> getMobilList(String keyword) {
        ArrayList<Mobil> mobilList = new ArrayList<>();
        Koneksi koneksi = new Koneksi();
        Connection connection = koneksi.getConnection();

        String query = "SELECT * FROM mobil " + keyword;
        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                mobil = new Mobil(
                        rs.getString("nopol"),
                        rs.getString("merk"),
                        rs.getString("warna"),
                        rs.getInt("tahun"),
                        rs.getInt("harga_sewa"),
                        rs.getBoolean("status"));
                mobilList.add(mobil);
            }
        } catch (SQLException | NullPointerException ex ) {
            System.out.println("Gagal melakukan query : " + ex.getMessage());
        }
        return mobilList;
    }

    public void selectMobil(String keyword) {
        ArrayList<Mobil> list = getMobilList(keyword);
        DefaultTableModel model = (DefaultTableModel) tbMobil.getModel();
        Object[] row = new Object[6];

        for (int i = 0; i < list.size(); i++) {

            String status = "tersedia";
            if (!list.get(i).isStatus()) {
                status = "tidak tersedia";
            }

            row[0] = list.get(i).getNopol();
            row[1] = list.get(i).getMerk();
            row[2] = list.get(i).getWarna();
            row[3] = list.get(i).getTahun();
            row[4] = list.get(i).getHargaSewa();
            row[5] = status;

            model.addRow(row);
        }
    }

    public final void resetTable(String keyword) {
        DefaultTableModel model = (DefaultTableModel) tbMobil.getModel();
        model.setRowCount(0);
        selectMobil(keyword);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbMobil = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        etCari = new javax.swing.JTextField();
        btCari = new javax.swing.JButton();
        btTambah = new javax.swing.JButton();
        btUbah = new javax.swing.JButton();
        btHapus = new javax.swing.JButton();
        btTutup = new javax.swing.JButton();
        btBatal = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        tbMobil.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nopol", "Merk", "Warna", "Tahun", "Harga", "Status"
            }
        ));
        jScrollPane1.setViewportView(tbMobil);
        if (tbMobil.getColumnModel().getColumnCount() > 0) {
            tbMobil.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        jLabel1.setText("Cari Mobil");

        btCari.setText("Cari");
        btCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCariActionPerformed(evt);
            }
        });

        btTambah.setText("Tambah");
        btTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTambahActionPerformed(evt);
            }
        });

        btUbah.setText("Ubah");
        btUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUbahActionPerformed(evt);
            }
        });

        btHapus.setText("Hapus");
        btHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btHapusActionPerformed(evt);
            }
        });

        btTutup.setText("Tutup");
        btTutup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTutupActionPerformed(evt);
            }
        });

        btBatal.setText("Batal");
        btBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBatalActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("MANAJEMEN MOBIL");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btTambah)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btUbah)
                        .addGap(18, 18, 18)
                        .addComponent(btHapus)
                        .addGap(18, 18, 18)
                        .addComponent(btBatal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 322, Short.MAX_VALUE)
                        .addComponent(btTutup))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(etCari, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btCari)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(etCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btCari))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btTambah)
                    .addComponent(btUbah)
                    .addComponent(btHapus)
                    .addComponent(btTutup)
                    .addComponent(btBatal))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btTutupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTutupActionPerformed
        dispose();
    }//GEN-LAST:event_btTutupActionPerformed

    private void btBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBatalActionPerformed
        resetTable("");
    }//GEN-LAST:event_btBatalActionPerformed

    private void btCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCariActionPerformed
        resetTable(" WHERE merk like '%" + etCari.getText() + "%' OR"
                + " nopol like '%" + etCari.getText() + "%'");
    }//GEN-LAST:event_btCariActionPerformed

    private void btHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btHapusActionPerformed
        int i = tbMobil.getSelectedRow();
        int pilihan = JOptionPane.showConfirmDialog(
                null,
                "Yakin mau dihapus ?",
                "Konfirmasi hapus",
                JOptionPane.YES_NO_OPTION
        );
        if (pilihan == 0) {
            if (i >= 0) {
                try {
                    TableModel model = tbMobil.getModel();
                    Koneksi koneksi = new Koneksi();
                    Connection conn = koneksi.getConnection();

                    String executeQuery = "DELETE FROM mobil WHERE nopol = ?";
                    PreparedStatement ps = conn.prepareStatement(executeQuery);
                    ps.setString(1, model.getValueAt(i, 0).toString());
                    ps.executeUpdate();
                } catch (SQLException err) {
                    System.err.println(err);
                }
            } else {
                JOptionPane.showMessageDialog(
                        null, "Pilih data yang ingin dihapus!"
                );
            }
        }
        resetTable("");
    }//GEN-LAST:event_btHapusActionPerformed

    private void btTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTambahActionPerformed
        TambahMobilFrame tambahMobil = new TambahMobilFrame();
        tambahMobil.setVisible(true);
    }//GEN-LAST:event_btTambahActionPerformed

    private void btUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUbahActionPerformed
        int i = tbMobil.getSelectedRow();
        if (i >= 0) {
            TableModel model = tbMobil.getModel();
            mobil = new Mobil();
            mobil.setNopol(model.getValueAt(i, 0).toString());
            mobil.setMerk(model.getValueAt(i, 1).toString());
            mobil.setWarna(model.getValueAt(i, 2).toString());
            mobil.setTahun(Integer.parseInt(model.getValueAt(i, 3).toString()));
            mobil.setHargaSewa(Integer.parseInt(model.getValueAt(i, 4).toString()));
            TambahMobilFrame tambahMobil = new TambahMobilFrame(mobil);
            tambahMobil.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(
                    null, "Pilih data yang ingin diubah!"
            );
        }
        resetTable("");
    }//GEN-LAST:event_btUbahActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        resetTable("");
    }//GEN-LAST:event_formWindowActivated

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
            java.util.logging.Logger.getLogger(MobilFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MobilFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MobilFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MobilFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MobilFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btBatal;
    private javax.swing.JButton btCari;
    private javax.swing.JButton btHapus;
    private javax.swing.JButton btTambah;
    private javax.swing.JButton btTutup;
    private javax.swing.JButton btUbah;
    private javax.swing.JTextField etCari;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbMobil;
    // End of variables declaration//GEN-END:variables
}