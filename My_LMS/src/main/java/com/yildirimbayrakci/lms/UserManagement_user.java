/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yildirimbayrakci.lms;

import com.yildirimbayrakci.entity.Account;
import com.yildirimbayrakci.entity.Book;
import com.yildirimbayrakci.entity.BorrowHistory;
import com.yildirimbayrakci.util.DateUtils;
import com.yildirimbayrakci.util.HibernateUtils;
import com.yildirimbayrakci.util.Search;
import com.yildirimbayrakci.util.TableColumnAdjuster;
import com.yildirimbayrakci.util.MyPdfWriter;
import java.util.List;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author YILDIRIM
 */
public class UserManagement_user extends javax.swing.JInternalFrame {

    /**
     * Creates new form UserManagement
     */
    private Account account;

    public UserManagement_user(Account account) {
        initComponents();
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bif = (BasicInternalFrameUI) getUI();
        bif.setNorthPane(null);
        this.account = account;

        jTextField_account_type.setEnabled(false);
        jTextField_userId.setEnabled(false);
        jTextField_userName.setEnabled(false);
        jTextField_userSurname.setEnabled(false);
        showUserInfo();

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
        jTable_lending = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel_numberOfBooksBorrowed = new javax.swing.JLabel();
        jTextField_userId = new javax.swing.JTextField();
        jTextField_userName = new javax.swing.JTextField();
        jTextField_email = new javax.swing.JTextField();
        jTextField_userSurname = new javax.swing.JTextField();
        jTextField_phone = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jPasswordField_userPassword = new javax.swing.JPasswordField();
        jLabel_messageField = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        jButton_bringBooks = new javax.swing.JButton();
        jCheckBox_bringReturnedBooks = new javax.swing.JCheckBox();
        jButton_createPDF1 = new javax.swing.JButton();
        jTextField_account_type = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        JButton_updateUserInfo = new javax.swing.JButton();
        jPasswordField_newPw = new javax.swing.JPasswordField();
        jPasswordField_newPwConfirm = new javax.swing.JPasswordField();
        JButton_changePw = new javax.swing.JButton();
        jCheckBox_showPassword = new javax.swing.JCheckBox();

        setBackground(new java.awt.Color(222, 222, 222));
        setPreferredSize(new java.awt.Dimension(930, 510));

        jPanel1.setBackground(new java.awt.Color(222, 222, 222));
        jPanel1.setPreferredSize(new java.awt.Dimension(870, 500));

        jTable_lending.setModel(new javax.swing.table.DefaultTableModel(
            null,
            new String [] {
                "İşlem Id","Kitap Id", "Başlık", "Ödünç Tarihi", "Bitiş Tarihi","Teslim Tarihi"
            }
        ));
        jTable_lending.setName("Kullanıcı Kitapları"); // NOI18N
        jTable_lending.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTable_lending);

        jLabel1.setText("Kullanıcı Id");

        jLabel4.setText("Şifre");

        jLabel5.setText("Ad");

        jLabel6.setText("Soyad");

        jLabel7.setText("Email");

        jLabel8.setText("Telefon");

        jLabel_numberOfBooksBorrowed.setText("Ödünç Alınan Kitap Sayısı:");

        jLabel10.setText("Tip");

        jLabel_messageField.setForeground(new java.awt.Color(204, 0, 0));

        jLabel12.setText("Mesaj");

        jButton_bringBooks.setText("Kitap Getir");
        jButton_bringBooks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_bringBooksActionPerformed(evt);
            }
        });

        jCheckBox_bringReturnedBooks.setBackground(new java.awt.Color(222, 222, 222));
        jCheckBox_bringReturnedBooks.setText("Teslim edilmiş kitapları getir");

        jButton_createPDF1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/pdf.png"))); // NOI18N
        jButton_createPDF1.setText("PDF Oluştur");
        jButton_createPDF1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_createPDF1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Yeni Şifre");

        jLabel3.setText("Yeni Şifre (Tekrar)");

        JButton_updateUserInfo.setText("Bilgilerimi Güncelle");
        JButton_updateUserInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButton_updateUserInfoActionPerformed(evt);
            }
        });

        JButton_changePw.setText("Şifre Değiştir");
        JButton_changePw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButton_changePwActionPerformed(evt);
            }
        });

        jCheckBox_showPassword.setBackground(new java.awt.Color(222, 222, 222));
        jCheckBox_showPassword.setText(" Şifreyi göster");
        jCheckBox_showPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_showPasswordActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton_bringBooks, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jCheckBox_bringReturnedBooks)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_createPDF1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(43, 43, 43)
                        .addComponent(jLabel_messageField, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel_numberOfBooksBorrowed, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(jTextField_userId, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(jTextField_userName, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField_userSurname, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(74, 74, 74)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(54, 54, 54)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField_email, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField_phone, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField_account_type, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(JButton_updateUserInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(82, 82, 82)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JButton_changePw, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBox_showPassword)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jPasswordField_userPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                                        .addComponent(jPasswordField_newPwConfirm)
                                        .addComponent(jPasswordField_newPw)))))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_bringBooks)
                    .addComponent(jButton_createPDF1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox_bringReturnedBooks))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel_messageField, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel_numberOfBooksBorrowed, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField_userId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jPasswordField_userPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField_phone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField_userName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jPasswordField_newPw, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField_userSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField_account_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jPasswordField_newPwConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox_showPassword)
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JButton_updateUserInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JButton_changePw, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(157, 157, 157))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_createPDF1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_createPDF1ActionPerformed
        // TODO add your handling code here:
        new MyPdfWriter().write(jTable_lending);
    }//GEN-LAST:event_jButton_createPDF1ActionPerformed

    private void jButton_bringBooksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_bringBooksActionPerformed
        updateLendTable(account.getAccountId());
    }//GEN-LAST:event_jButton_bringBooksActionPerformed

    private void JButton_updateUserInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JButton_updateUserInfoActionPerformed
        String email = jTextField_email.getText();
        String phone = jTextField_phone.getText();

        Account temp = HibernateUtils.updateUserContact(account.getAccountId(), email, phone);

        if (temp != null) {
            jLabel_messageField.setText("İletişim bilgileriniz güncellendi");
            account = temp;
            AdminScreen.setAccount(account);
            showUserInfo();
        }

    }//GEN-LAST:event_JButton_updateUserInfoActionPerformed

    private void JButton_changePwActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JButton_changePwActionPerformed
        String password = new String(jPasswordField_userPassword.getPassword());
        String newPassword = new String(jPasswordField_newPw.getPassword());
        String newPasswordConfirm = new String(jPasswordField_newPwConfirm.getPassword());

        password = password.trim();
        newPassword = newPassword.trim();
        newPasswordConfirm = newPasswordConfirm.trim();

        if (password.equals("") || newPassword.equals("") || newPasswordConfirm.equals("")) {
            jLabel_messageField.setText("Lütfen alanları boş bırakmayınız.");
        } else if (!newPassword.equals(newPasswordConfirm)) {
            jLabel_messageField.setText("Yeni şifre ile tekrar alanı eşleşmiyor.");
        } else {
            boolean correctPw = HibernateUtils.authenticateUser(account.getAccountId(), password);
            if (!correctPw) {
                jLabel_messageField.setText("Yanlış hesap şifresi girdiniz.");
            } else {

                HibernateUtils.changePw(account.getAccountId(), newPassword);

                jLabel_messageField.setText("Şifreniz başarıyla değiştirildi.");

                jPasswordField_userPassword.setText("");
                jPasswordField_newPw.setText("");
                jPasswordField_newPwConfirm.setText("");

            }
        }
    }//GEN-LAST:event_JButton_changePwActionPerformed

    private void jCheckBox_showPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_showPasswordActionPerformed
        if (jCheckBox_showPassword.isSelected()) {
            jPasswordField_newPw.setEchoChar((char) 0);
            jPasswordField_newPwConfirm.setEchoChar((char) 0);
            jPasswordField_userPassword.setEchoChar((char) 0);
        } else {
            jPasswordField_newPw.setEchoChar('●');
            jPasswordField_newPwConfirm.setEchoChar('●');
            jPasswordField_userPassword.setEchoChar('●');
        }
    }//GEN-LAST:event_jCheckBox_showPasswordActionPerformed

    private void updateLendTable(String accountId) {
        DefaultTableModel dtm = (DefaultTableModel) jTable_lending.getModel();
        dtm.setRowCount(0);
        List<BorrowHistory> history;
        if (jCheckBox_bringReturnedBooks.isSelected()) {
            history = Search.searchBorrowHistory(accountId, true);
        } else {
            history = Search.searchBorrowHistory(accountId, false);
        }

        Account account = Search.searchUser(accountId);
        Set<Book> reservedBooks = account.getReservedBooks();
       /*
        for(Book b: reservedBooks){
            dtm.addRow(new Object);
        }*/
        
        history.forEach((bh) -> {
            dtm.addRow(new Object[]{bh.getBorrowId(), bh.getBook().getBookId(), bh.getBook().getTitle(),
                DateUtils.formatDate(bh.getBorrowDate()), DateUtils.formatDate(bh.getDueDate()),
                DateUtils.formatDate(bh.getReturnDate())});
        });
        jTable_lending.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        TableColumnAdjuster tca = new TableColumnAdjuster(jTable_lending);
        tca.adjustColumns();

        jLabel_numberOfBooksBorrowed.setText("Ödünç Alınan Kitap Sayısı: " + history.size());

    }

    public void showUserInfo() {
        jTextField_email.setText(account.getEmail());
        jTextField_phone.setText(account.getPhone());
        jTextField_account_type.setText(account.getType().displayName());
        jTextField_userId.setText(account.getAccountId());
        jTextField_userName.setText(account.getFirstName());
        jTextField_userSurname.setText(account.getLastName());

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JButton_changePw;
    private javax.swing.JButton JButton_updateUserInfo;
    private javax.swing.JButton jButton_bringBooks;
    private javax.swing.JButton jButton_createPDF1;
    private javax.swing.JCheckBox jCheckBox_bringReturnedBooks;
    private javax.swing.JCheckBox jCheckBox_showPassword;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel_messageField;
    private javax.swing.JLabel jLabel_numberOfBooksBorrowed;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField_newPw;
    private javax.swing.JPasswordField jPasswordField_newPwConfirm;
    private javax.swing.JPasswordField jPasswordField_userPassword;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable_lending;
    private javax.swing.JTextField jTextField_account_type;
    private javax.swing.JTextField jTextField_email;
    private javax.swing.JTextField jTextField_phone;
    private javax.swing.JTextField jTextField_userId;
    private javax.swing.JTextField jTextField_userName;
    private javax.swing.JTextField jTextField_userSurname;
    // End of variables declaration//GEN-END:variables
}