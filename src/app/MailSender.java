package app;

import utils.TimeDate;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {
    private String from = "sefrhelpdeskjava@gmail.com";
    private final String host = "smtp.gmail.com";
    Properties properties;
    TimeDate td = new TimeDate();
    public MailSender() {
        properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
    }
    public void Send(String _from, String _password, String _to, String _subject, String _message){
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(_from, _password);

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);
        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(_from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(_to));

            // Set Subject: header field
            message.setSubject(_subject);
            StringBuilder string = new StringBuilder();
            string.append(_message);
            string.append("\n");
            string.append(td.getTime());
            // Now set the actual message
            message.setText(string.toString());

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
            Reply(_from, _subject);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }

    public void Reply(String _to, String _subject){
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, "semestralka2022");
            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);
        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(_to));

            // Set Subject: header field
            message.setSubject("Oznámení o ticketu");

            // Now set the actual message
            message.setText("Úspěšně jste poslal ticket '" + _subject + "' ve dne " + td.getTime());

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}