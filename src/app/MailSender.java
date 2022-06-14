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

/**
 * Třída implementující knihovnu javax.mail pro posílání emailů
 */
public class MailSender {
    private String from = "sefrhelpdeskjava@centrum.cz";
    private final String host = "smtp.centrum.cz";
    Properties properties;
    TimeDate td = new TimeDate();

    /**
     * konstruktor pro nastavení smtp serveru
     */
    public MailSender() {
        properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
    }

    /**
     * metoda přejímá informace o ticketu a posílá email adminovi
     * @param _from
     * @param _password
     * @param _to
     * @param _subject
     * @param _message
     * @return boolean
     */
    public boolean Send(String _from, String _password, String _to, String _subject, String _message){
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(_from, _password);

            }

        });

        // Used to debug SMTP issues
        //session.setDebug(true);
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

            System.out.println("posílám...");
            // Send message
            Transport.send(message);
            if(Reply(_from, _subject)){
                return true;
            }
            else{
                return false;
            }
        } catch (MessagingException mex) {
            //mex.printStackTrace();
            return false;
        }

    }

    /**
     * stejná metoda jako send, jen tato posílá potvrzovací mail uživateli
     * @param _to
     * @param _subject
     * @return boolean
     */
    public boolean Reply(String _to, String _subject){
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, "Semestralka2022");
            }

        });

        // Used to debug SMTP issues
        //  session.setDebug(true);
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

            // Send message
            Transport.send(message);
            return true;
        } catch (MessagingException mex) {
            //mex.printStackTrace();
            return false;
        }
    }
}