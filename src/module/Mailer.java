package module;
import java.util.Date;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mailer {
public static void send(String to,String subject,String msg){

final String user="f6502ad2c660f2cd5d3cbee3ed263ce6";//change accordingly
final String pass="63ee59c5a1ec0548c775edc8ff1a36df";

//1st step) Get the session object	
Properties props = new Properties();
props.put("mail.smtp.host", "in-v3.mailjet.com");//change accordingly
props.put("mail.smtp.auth", "true");
props.put("mail.smtp.port", "587");
props.put("mail.smtp.starttls.enable", "true");

Session session = Session.getDefaultInstance(props,
 new javax.mail.Authenticator() {
  protected PasswordAuthentication getPasswordAuthentication() {
   return new PasswordAuthentication(user,pass);
   }
});
//2nd step)compose message
try {
 MimeMessage message = new MimeMessage(session);
 message.setFrom(new InternetAddress("application@erios.ml"));
 message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
 message.setSubject(subject);
 message.setText(msg);
 message.setSentDate(new Date());
 
 //3rd step)send message
 Transport.send(message);

 System.out.println("Done");

 } catch (MessagingException e) {
	throw new RuntimeException(e);
 }
	
}
}
