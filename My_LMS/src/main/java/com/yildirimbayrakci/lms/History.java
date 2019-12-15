/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yildirimbayrakci.lms;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;
import com.yildirimbayrakci.entity.BorrowHistory;
import com.yildirimbayrakci.util.DateUtils;
import com.yildirimbayrakci.util.Search;
import com.yildirimbayrakci.util.TableColumnAdjuster;
import com.yildirimbayrakci.util.MyPdfWriter;
import java.text.ParseException;
import java.time.DayOfWeek;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author YILDIRIM
 */
public class History extends javax.swing.JInternalFrame {

    /**
     * Creates new form Report
     */
    public History() {
        initComponents();
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bif = (BasicInternalFrameUI) this.getUI();
        bif.setNorthPane(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_report = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        getPdf = new javax.swing.JButton();
        jTextField_search = new javax.swing.JTextField();
        jTextField_pickDate = new javax.swing.JTextField();
        jTextField_pickDate1 = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setPreferredSize(new java.awt.Dimension(930, 510));

        jPanel1.setBackground(new java.awt.Color(222, 222, 222));

        jTable_report.setModel(new javax.swing.table.DefaultTableModel(
            null,
            new String [] {
                "İşlem Id","Kitap Id","Başlık","Kullanıcı Id","Ödünç Tarihi","Bitiş Tarihi","Teslim Tarihi"
            }
        ));
        jTable_report.setName("Geçmiş"); // NOI18N
        jTable_report.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable_report);

        jButton1.setText("Kitap ID ile Ara");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        getPdf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/pdf.png"))); // NOI18N
        getPdf.setText("PDF Oluştur");
        getPdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getPdfActionPerformed(evt);
            }
        });

        jTextField_pickDate.setEnabled(false);
        jTextField_pickDate.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField_pickDateFocusGained(evt);
            }
        });

        jTextField_pickDate1.setEnabled(false);
        jTextField_pickDate1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField_pickDate1FocusGained(evt);
            }
        });

        jCheckBox1.setBackground(new java.awt.Color(222, 222, 222));
        jCheckBox1.setText("Ödünç Tarih Aralığı ");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(204, 0, 0));

        jLabel2.setText("Mesaj: ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 838, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(getPdf)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextField_search, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(jCheckBox1)
                                .addGap(28, 28, 28)
                                .addComponent(jTextField_pickDate, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField_pickDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 627, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jTextField_pickDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_pickDate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(getPdf)
                .addContainerGap(191, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel dtm = (DefaultTableModel) jTable_report.getModel();
        dtm.setRowCount(0);
        jLabel1.setText("Kayıtlar getirildi.");

        Integer bookId = null;
        if (jTextField_search.getText() != null && !jTextField_search.getText().equals("")) {
            bookId = Integer.parseInt(jTextField_search.getText());
        }

        Date beginDate = null;
        Date endDate = null;

        if (jCheckBox1.isSelected()) {
            try {
                beginDate = DateUtils.parseDate(jTextField_pickDate.getText());
                endDate = DateUtils.parseDate(jTextField_pickDate1.getText());
                if(beginDate.after(endDate)){
                    jLabel1.setText("Hatalı tarih aralığı seçtiniz.");
                    beginDate = null;
                    endDate = null;
                }
            } catch (ParseException ex) {
                jLabel1.setText("Tarih format hatası. Lütfen tarih aralığı giriniz.");
            }
        }

        List<BorrowHistory> history = Search.searchBorrowHistory(bookId, beginDate, endDate);
        for (BorrowHistory bh : history) {

            dtm.addRow(new Object[]{bh.getBorrowId(), bh.getBook().getBookId(), bh.getBook().getTitle(),
                bh.getAccount().getAccountId(), DateUtils.formatDate(bh.getBorrowDate()),
                DateUtils.formatDate(bh.getDueDate()), DateUtils.formatDate(bh.getReturnDate())});
        }

        jTable_report.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        TableColumnAdjuster tca = new TableColumnAdjuster(jTable_report);
        tca.adjustColumns();

    }//GEN-LAST:event_jButton1ActionPerformed

    public void pickDate(JTextField jf) {

        DatePickerSettings dps = new DatePickerSettings(new Locale("tr", "TR"));
        dps.setVisibleTodayButton(true);
        dps.setFirstDayOfWeek(DayOfWeek.MONDAY);
        dps.setFormatForDatesCommonEra("dd/MM/yyyy");
        dps.setFormatForDatesBeforeCommonEra("dd/MM/uuuu");
        
        DatePicker dp = new DatePicker(dps);
        dp.addDateChangeListener((DateChangeEvent event) -> {
            jf.setText(DateUtils.formatDate(java.sql.Date.valueOf(event.getNewDate())));
        });
        dp.setDateToToday();
        jf.add(dp);
        
        dp.openPopup();
    }

    private void getPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getPdfActionPerformed

        new MyPdfWriter().write(jTable_report);
    }//GEN-LAST:event_getPdfActionPerformed

    private void jTextField_pickDateFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_pickDateFocusGained
        pickDate(jTextField_pickDate);
    }//GEN-LAST:event_jTextField_pickDateFocusGained

    private void jTextField_pickDate1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_pickDate1FocusGained
        // TODO add your handling code here:
        pickDate(jTextField_pickDate1);
    }//GEN-LAST:event_jTextField_pickDate1FocusGained

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
        if (jCheckBox1.isSelected()) {
            jTextField_pickDate.setEnabled(true);
            jTextField_pickDate1.setEnabled(true);
        } else {
            jTextField_pickDate.setEnabled(false);
            jTextField_pickDate1.setEnabled(false);
        }

    }//GEN-LAST:event_jCheckBox1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton getPdf;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable_report;
    private javax.swing.JTextField jTextField_pickDate;
    private javax.swing.JTextField jTextField_pickDate1;
    private javax.swing.JTextField jTextField_search;
    // End of variables declaration//GEN-END:variables
}