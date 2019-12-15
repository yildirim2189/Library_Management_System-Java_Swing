/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yildirimbayrakci.util;

import org.hazlewood.connor.bottema.emailaddress.EmailAddressValidator;
import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;
import org.simplejavamail.mailer.config.TransportStrategy;

/**
 *
 * @author yildi
 */
public class JavaMail {

    private static final String APP_EMAIL = "lms.kutuphane@gmail.com";
    private static final String APP_EMAIL_PASSWORD = "library123456";
    public static final int INITIAL_PASSWORD = 0;
    public static final int CHANGED_PASSWORD = 1;

    public static void sendEmail(String receiverId,String receiverName, String receiverEmail, String password, int mailType) {

        if (isValidEmail(receiverEmail)) {
            // Set up mailer configuration
            Mailer myMailer = MailerBuilder
                    .withSMTPServer("smtp.gmail.com", 465, APP_EMAIL, APP_EMAIL_PASSWORD)
                    .withTransportStrategy(TransportStrategy.SMTPS)
                    .buildMailer();

            // Set up email configuration
            String subject = "Kütüphane Şifreniz";
            String content = "Sayın " + receiverName + ", \n";
                    
            
            if(mailType == INITIAL_PASSWORD){
                // initial password
                content += "Kütüphane şifreniz başarıyla oluşturuldu. \nKullanıcı ID: " + receiverId + "\n"
                        + "Şifreniz: " + password;
            }
            else{
                // changed password
                content += "Kütüphane şifreniz başarıyla değiştirildi. \nKullanıcı ID: " + receiverId + "\n"
                        + "Yeni şifreniz: " + password;
            }
            
            Email email = EmailBuilder.startingBlank()
                    .from("Kütüphane Bilgi Sistemi", APP_EMAIL)
                    .to(receiverName, receiverEmail)
                    .withSubject(subject)
                    .withPlainText(content)
                    .buildEmail();

            // Send email
            myMailer.sendMail(email);
        }

    }

    public static boolean isValidEmail(String email) {
        return EmailAddressValidator.isValid(email);
    }

}
