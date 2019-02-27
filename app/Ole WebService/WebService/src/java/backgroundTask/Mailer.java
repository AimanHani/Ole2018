/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgroundTask;
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
          props.put("mail.smtp.host", "smtp.gmail.com");    
          props.put("mail.smtp.socketFactory.port", "465");    
          props.put("mail.smtp.socketFactory.class",    
                    "javax.net.ssl.SSLSocketFactory");    
          props.put("mail.smtp.auth", "true");    
          props.put("mail.smtp.port", "465");    
          //get Session   
          Session session = Session.getDefaultInstance(props,    
           new javax.mail.Authenticator() {    
           protected PasswordAuthentication getPasswordAuthentication() {    
           return new PasswordAuthentication(from,password);  
           }    
          });    
         
          try {    
           MimeMessage message = new MimeMessage(session);    
           message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
           message.setSubject(sub);    
           message.setText(msg);     
           Transport.send(message);    
           System.out.println("message sent successfully");
           status = true;
           return status;
          } catch (MessagingException e) {throw new RuntimeException(e);}
          
           
    }  
}  
