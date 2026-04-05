package com;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

public class SmtpTest {
    public static void main(String[] args) {
        // SMTP configuration - UPDATE THESE VALUES
        final String host = "mercurymailsystem.ca";  // e.g., "smtp.mailtrap.io" for testing
        final int port = 587;                  // 587 for TLS, 465 for SSL
        final String username = "mcquaids@mercurymailsystem.ca";  // Your SMTP username
        final String password = "&$hTdnkl94";     // App password (not regular password)
        final String from = "mcquaids@mercurymailsystem.ca";
        final String to = "steven.banks@gmail.com";
        final String subject = "SMTP Test Email";
        final String body = "This is a test email sent from Java. SMTP configuration is correct!";

        // Properties setup [web:1][web:2]
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", String.valueOf(port));
        props.put("mail.debug", "true");  // Enable for detailed logs [web:17]

        // Create session with authenticator
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            // Send email
            Transport.send(message);
            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            System.err.println("Error sending email: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
