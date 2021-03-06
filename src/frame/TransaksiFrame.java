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
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.Transaksi;

/**
 *
 * @author whois
 */
public class TransaksiFrame extends javax.swing.JFrame {

    int gTransaksiCount = 0;
    int gTotalSewa = 0;

    /**
     * Creates new form TransasksiFrame
     */
    public TransaksiFrame() {
        initComponents();
        setLocationRelativeTo(null);
        resetTable();
    }

    public void setHeader() {
        tvJumlahSewa.setText("Jumlah sewa : " + gTransaksiCount);
        tvPendapatan.setText("Total pendapatan kotor : " + gTotalSewa);
    }

    public ArrayList<Transaksi> getTransaksiList() throws ParseException {

        gTransaksiCount = 0;
        gTotalSewa = 0;

        ArrayList<Transaksi> transaksiList = new ArrayList<>();
        Koneksi koneksi = new Koneksi();
        Connection connection = koneksi.getConnection();

        String query = "SELECT s.id AS id, s.ktp as KTP, p.nama as nama, s.nopol as nopol, m.merk as merk, s.harga_sewa as harga_sewa, s.waktu_mulai as waktu_mulai, s.waktu_selesai as waktu_selesai "
                + "FROM sewa s "
                + "LEFT JOIN mobil m USING(nopol) "
                + "LEFT JOIN pelanggan p USING(ktp)";
        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                Transaksi transaksi = new Transaksi(
                        rs.getInt("id"),
                        rs.getString("ktp"),
                        rs.getString("nama"),
                        rs.getString("nopol"),
                        rs.getString("merk"),
                        rs.getInt("harga_sewa"),
                        rs.getString("waktu_mulai"),
                        rs.getString("waktu_selesai"));
                transaksiList.add(transaksi);

                gTransaksiCount += 1;
                gTotalSewa = transaksi.getTotalHarga();
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Gagal melakukan query : " + ex.getMessage());
        }

        setHeader();

        return transaksiList;
    }

    public void selectTransaksi() throws ParseException {
        ArrayList<Transaksi> list = getTransaksiList();
        DefaultTableModel model = (DefaultTableModel) tbTransaksi.getModel();
        Object[] row = new Object[9];

        for (int i = 0; i < list.size(); i++) {

            String hariSewa = list.get(i).getHariSewa() + " hari";

            row[0] = list.get(i).getId();
            row[1] = list.get(i).getNama();
            row[2] = list.get(i).getMerk();
            row[3] = list.get(i).getNopol();
            row[4] = hariSewa;
            row[5] = list.get(i).getTotalHarga();
            row[6] = list.get(i).getWaktuMulai();
            row[7] = list.get(i).getWaktuSelesai();
            row[8] = list.get(i).getKtp();

            model.addRow(row);
        }
    }

    public final void resetTable() {
        DefaultTableModel model = (DefaultTableModel) tbTransaksi.getModel();
        model.setRowCount(0);
        try {
            selectTransaksi();
        } catch (ParseException ex) {

        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btMobil = new javax.swing.JButton();
        btPelanggan = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbTransaksi = new javax.swing.JTable();
        btTambah = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        tvJumlahSewa = new javax.swing.JLabel();
        tvPendapatan = new javax.swing.JLabel();
        btLaporan = new javax.swing.JButton();
        btHapus = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        btMobil.setBackground(new java.awt.Color(255, 255, 153));
        btMobil.setText("Mobil");
        btMobil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMobilActionPerformed(evt);
            }
        });

        btPelanggan.setBackground(new java.awt.Color(153, 255, 153));
        btPelanggan.setText("Pelanggan");
        btPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPelangganActionPerformed(evt);
            }
        });

        tbTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nama", "Mobil", "Nopol", "Lama", "Total", "Tgl Sewa", "Est Kembali", "Ktp"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbTransaksi.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tbTransaksi);
        if (tbTransaksi.getColumnModel().getColumnCount() > 0) {
            tbTransaksi.getColumnModel().getColumn(0).setMaxWidth(50);
            tbTransaksi.getColumnModel().getColumn(8).setMinWidth(0);
            tbTransaksi.getColumnModel().getColumn(8).setPreferredWidth(0);
            tbTransaksi.getColumnModel().getColumn(8).setMaxWidth(0);
        }

        btTambah.setBackground(new java.awt.Color(153, 255, 153));
        btTambah.setText("+ Transaksi");
        btTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTambahActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
        jPanel1.setForeground(new java.awt.Color(51, 153, 255));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Penyewaan Mobil : CV Muchlis.Dev");

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton2.setText("...");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        tvJumlahSewa.setBackground(new java.awt.Color(255, 255, 255));
        tvJumlahSewa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tvJumlahSewa.setForeground(new java.awt.Color(255, 255, 255));
        tvJumlahSewa.setText("Jumlah sewa : 0");

        tvPendapatan.setBackground(new java.awt.Color(255, 255, 255));
        tvPendapatan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tvPendapatan.setForeground(new java.awt.Color(255, 255, 255));
        tvPendapatan.setText("Total pendapatan kotor : 0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tvJumlahSewa, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tvPendapatan, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tvJumlahSewa, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tvPendapatan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        btLaporan.setText("Laporan");

        btHapus.setBackground(new java.awt.Color(255, 102, 102));
        btHapus.setText("Hapus");
        btHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btHapusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btPelanggan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                            .addComponent(btMobil, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btLaporan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btTambah, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btHapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 907, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btMobil, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(btHapus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btLaporan, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btMobilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMobilActionPerformed
        MobilFrame lamanMobil = new MobilFrame();
        lamanMobil.setVisible(true);
    }//GEN-LAST:event_btMobilActionPerformed

    private void btTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTambahActionPerformed
        TambahTransaksiFrame lamanTambahTransaksi = new TambahTransaksiFrame();
        lamanTambahTransaksi.setVisible(true);
    }//GEN-LAST:event_btTambahActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        resetTable();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        resetTable();
    }//GEN-LAST:event_formWindowActivated

    private void btPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPelangganActionPerformed
        PelangganFrame lamanPelanggan = new PelangganFrame();
        lamanPelanggan.setVisible(true);
    }//GEN-LAST:event_btPelangganActionPerformed

    private void btHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btHapusActionPerformed
       int i = tbTransaksi.getSelectedRow();
        int pilihan = JOptionPane.showConfirmDialog(
                null,
                "Yakin mau dihapus ?",
                "Konfirmasi hapus",
                JOptionPane.YES_NO_OPTION
        );
        if (pilihan == 0) {
            if (i >= 0) {
                try {
                    TableModel model = tbTransaksi.getModel();
                    Koneksi koneksi = new Koneksi();
                    Connection conn = koneksi.getConnection();

                    String executeQuery = "DELETE FROM sewa WHERE id = ?";
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
        resetTable();
    }//GEN-LAST:event_btHapusActionPerformed

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
            java.util.logging.Logger.getLogger(TransaksiFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TransaksiFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TransaksiFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TransaksiFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TransaksiFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btHapus;
    private javax.swing.JButton btLaporan;
    private javax.swing.JButton btMobil;
    private javax.swing.JButton btPelanggan;
    private javax.swing.JButton btTambah;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbTransaksi;
    private javax.swing.JLabel tvJumlahSewa;
    private javax.swing.JLabel tvPendapatan;
    // End of variables declaration//GEN-END:variables
}
