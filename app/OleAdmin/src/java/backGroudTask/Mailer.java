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
    public static boolean send(String from,String password,String to,String sub,String msg){  
        
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
}  
