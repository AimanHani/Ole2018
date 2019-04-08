/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backGroudTask;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author user
 */
public class Mailer {

    public static boolean send(String from, String password, String to, String sub, String msg) {

        boolean status = false;
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject(sub);
            message.setText(msg);

            Transport.send(message);

            System.out.println("Mail sent succesfully!");
            status = true;

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        return status;
    }

    public static boolean sendHtml(String answer, String question, String username, String to) {
        String from = "olegroup18@gmail.com";
        String password = "squadxole";
        boolean status = false;
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject("AskOle Reply");
            message.setContent("Hi " + username + ", "
                    + "<br><br> Thank you for your question on Olé Football.Friends.Fun. "
                    + "<br>We have found an answer to your query<br><br>Question: <b>"
                    + question + "</b><br>Answer: <b>"
                    + answer + "</b><br><br>If you have further enquiries, contact us at olegroup18@gmail.com<br><br> Yours Sincerely,<br> Ole Group",
                    "text/html; charset=UTF-8");

            Transport.send(message);

            System.out.println("Mail sent succesfully!");
            status = true;

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        return status;
    }

}
