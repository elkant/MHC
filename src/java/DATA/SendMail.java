/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA;


import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import sender.dbConn;

public class SendMail {
public String filenames,cu,dates,computername,senderofmail;
        
 String full_date;
    public SendMail(){
    }  
 public boolean Sendattachment (String date,String path,String comp,String senderofemail,String filname)throws MessagingException {
     String toAddress="";
     
     dbConn conn= new dbConn();
    
     
          try {
              
          
              
            conn.rs=conn.st.executeQuery("select mail from mail where mailid='1'");
            
            while(conn.rs.next()){
            
                
            toAddress=conn.rs.getString(1);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
        }
     
     
        filenames=path; 
        full_date=date;
        computername=comp;
        senderofmail=senderofemail;
        String textBody="Hi ,\n Attached is MNCH data back up as at "+full_date+" "+senderofmail+".\n*******This is a system autogenerated message*****";
        
        if(!toAddress.equals("")){
        toAddress+=",";
        }
        toAddress+="ekmanukaka5@gmail.com,jkuria@aphiarift.org";
        String host = "smtp.gmail.com";
        String Password ="plusaphia";
        String from = "aphiabackup@gmail.com";
        // toAddress = "aphiapluschwsattendance@gmail.com";  filled above...
        String filename = filenames;
        // Get system properties
        Properties props = System.getProperties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtps.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        //props.put("mail.smtp.starttls.enable", "true");
        
        Session session = Session.getInstance(props, null);

        MimeMessage message = new MimeMessage(session);

        message.setFrom(new InternetAddress(from));

        message.setRecipients(Message.RecipientType.TO, toAddress);

        message.setSubject("MNCH SQL DATA BACK_UP From : "+computername);

        BodyPart messageBodyPart = new MimeBodyPart();

        messageBodyPart.setText(textBody);

        Multipart multipart = new MimeMultipart();

        multipart.addBodyPart(messageBodyPart);

        messageBodyPart = new MimeBodyPart();

        DataSource source = new FileDataSource(filename);

        messageBodyPart.setDataHandler(new DataHandler(source));

        messageBodyPart.setFileName(filname+"MNCH.sql");
        multipart.addBodyPart(messageBodyPart);

        message.setContent(multipart);

        try {
            javax.mail.Transport tr = session.getTransport("smtps");
            tr.connect(host, from, Password);
            tr.sendMessage(message, message.getAllRecipients());
            System.out.println("Mail Sent Successfully");
            tr.close();
            
            return true;

        } catch (SendFailedException sfe) {

            System.out.println(sfe);
            return false;
        }
    }

//    public static void main(String args[]) {
////        try {
////            SendMail sm = new SendMail();
////        } catch (MessagingException ex) {
////            Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
////        }
//    }
}