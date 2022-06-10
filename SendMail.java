
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
    
    public  void  send(String to, String subject, String txt){
        
       String host = "smtp.gmail.com";
       String from = "matthew.in.ncu@g.ncu.edu.tw";

      Properties properties = System.getProperties();
      properties.put("mail.smtp.host", host);
      properties.put("mail.smtp.port", "465");
      properties.put("mail.smtp.ssl.enable", "true");
      properties.put("mail.smtp.auth", "true");
      //properties.put("mail.debug", "true");

      Session session = Session.getInstance(properties, new javax.mail.Authenticator(){
        protected PasswordAuthentication getPasswordAuthentication() {
          return new PasswordAuthentication("matthew.in.ncu@g.ncu.edu.tw", "eviqoybgifttjrov");
        }
      });

      try {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setContent(txt, "text/html;charset = UTF-8");
        message.setSubject(subject);
        

        Transport.send(message);
      } catch (MessagingException mex) {
        mex.printStackTrace();
      }
   }
    
    
}
