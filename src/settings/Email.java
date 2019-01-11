package settings;

import java.io.*;
import java.net.InetAddress;
import java.util.Properties;
import java.util.Date;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;

import javax.mail.internet.*;

import com.sun.mail.smtp.*;

import contacts.ContactsD;




public class Email {

		
		
	   String senderEmailID = null;
	   String senderPassword = null;
	   String emailSMTPserver = null;
	   String emailServerPort = null;
	   String receiverEmailID = null;
	   static String emailSubject = "";
	   static String emailBody = "";
	   
	     public Email(String receiverEmailID,String Subject,
	     String Body, byte[] bs){
	    	 ContactsD n = new ContactsD();
	        n.getSEmail();
	        this.senderEmailID=n.getEm();
	        this.senderPassword=n.getPas();
	        this.emailSMTPserver=n.getSser();
	        this.emailServerPort=n.getSpor();
	     // Receiver Email Address
	     this.receiverEmailID=receiverEmailID; 
	     // Subject
	     Email.emailSubject=Subject;
	     // Body
	     Email.emailBody=Body;
	     Properties props = new Properties();
	     props.put("mail.smtp.user",senderEmailID);
	     props.put("mail.smtp.host", emailSMTPserver);
	     props.put("mail.smtp.port", emailServerPort);
	     props.put("mail.smtp.starttls.enable", "true");
	     props.put("mail.smtp.auth", "true");
	     props.put("mail.smtp.socketFactory.port", emailServerPort);
	     props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	     props.put("mail.smtp.socketFactory.fallback", "false");
	     SecurityManager security = System.getSecurityManager();
	     try{  
	     Authenticator auth = new SMTPAuthenticator();
	     Session session = Session.getInstance(props, auth);
	     
	     
	     MimeMessage msg = new MimeMessage(session);
	     msg.setText(emailBody);
	     msg.setSubject(emailSubject);
	     msg.setFrom(new InternetAddress(senderEmailID));
	     msg.addRecipient(Message.RecipientType.TO,
	     new InternetAddress(receiverEmailID));
	     
	     
	     
	   BodyPart messageBodyPart1 = new MimeBodyPart();     
         messageBodyPart1.setText(emailBody);  
	     
	     MimeBodyPart messageBodyPart2 = new MimeBodyPart();      
       
	     
	     messageBodyPart2.setDataHandler(new DataHandler(
                 new DataSource() {
                	  
          public String getContentType() {
              return "application/pdf";
          }

          public InputStream getInputStream() throws IOException {
              return new ByteArrayInputStream(bs);
          }

          public String getName() {
              return "myfile.pdf";
          }

          public OutputStream getOutputStream() throws IOException {
              return null;
          }}));

	     
         Multipart multipart = new MimeMultipart();    
        multipart.addBodyPart(messageBodyPart1);     
         multipart.addBodyPart(messageBodyPart2); 
	     
	     
         msg.setContent(multipart ); 
	     
	     
	     
	     Transport.send(msg);
	     System.out.println("Message send Successfully:)"); }
	     
	     catch (Exception mex){
	     mex.printStackTrace();}
	     
	     
	     }
	     public class SMTPAuthenticator extends javax.mail.Authenticator
	     {
	     public PasswordAuthentication getPasswordAuthentication()
	     {
	     return new PasswordAuthentication(senderEmailID, senderPassword);
	     }
	     }
	        
	
	
	
	
}
