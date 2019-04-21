/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Lenovo
 */
public class due {
    public static void main(String args[])throws SQLException, ParseException{
        database handler=new database();
        String query1="SELECT * FROM ISSUEDBOOKS";
        PreparedStatement prp=handler.connectissuedbooks.prepareStatement(query1);
        ResultSet rs=prp.executeQuery();
        
        while(rs.next()){
            Statement stmt;
            String updatedate=rs.getString("updatedate");
            Date date2=new Date();
            Date date1=new Date();
           
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            date1=simpleDateFormat.parse(updatedate);
            String newupdatedate=simpleDateFormat.format(date2);
            long diff = date2.getTime() - date1.getTime();
            long diffDays = diff / (24 * 60 * 60 * 1000);
            if(diffDays>0){
            System.out.println("hi");
            String query2;
            query2="SELECT * FROM USER WHERE "+"USERNAME"+" = "+"'"+rs.getString("user")+"'";
            PreparedStatement prp2=handler.connectuser.prepareStatement(query2);
            ResultSet rs2=prp2.executeQuery();
            
            System.out.println("hi");
            int fine=0;
            if(rs2.next()){
            fine=rs2.getInt(6);
            }
            String query3="UPDATE USER SET FINE = '"+(fine+diffDays*2)+"' WHERE ROLLNO = '"+rs.getString("rno")+"'";
            stmt=handler.connectuser.createStatement();
            stmt.executeUpdate(query3);
            query3="UPDATE ISSUEDBOOKS SET UPDATEDATE = '"+(newupdatedate)+"' WHERE  BOOKID= '"+rs.getString("bookid")+"'";
            stmt=handler.connectissuedbooks.createStatement();
            stmt.executeUpdate(query3);
            
            try{
            String host ="smtp.gmail.com" ;
            String user = "digitallibrary98@gmail.com";
            String pass = "miniproject1234";
          
            String to =rs.getString("rno")+"@nirmauni.ac.in";
            String from = "digitallibrary98@gmail.com";
            String subject = "book return overdue";
            String messageText = "You have 1 item overdue \n "+rs.getString("booktitle")+" by "+rs.getString("bookauthor");
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject); msg.setSentDate(new Date());
            msg.setText(messageText);

           Transport transport=mailSession.getTransport("smtp");
           transport.connect(host, user, pass);
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
           System.out.println("message send successfully");
          }catch(Exception ex)
           {
            System.out.println(ex);
           }
            
        }
            else if(diffDays==0){
                try{
            String host ="smtp.gmail.com" ;
            String user = "digitallibrary98@gmail.com";
            String pass = "miniproject1234";
            String to =rs.getString("rno")+"@nirmauni.ac.in";
             System.out.println(to);
            String from = "digitallibrary98@gmail.com";
            String subject = "book return due";
            String messageText = "You have 1 item due \n "+rs.getString("booktitle")+" by "+rs.getString("bookauthor");
            boolean sessionDebug = false;

            Properties props = System.getProperties();
            
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject); msg.setSentDate(new Date());
            msg.setText(messageText);

           Transport transport=mailSession.getTransport("smtp");
           transport.connect(host, user, pass);
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
           System.out.println("message send successfully");
          }catch(Exception ex)
           {
            System.out.println(ex);
           }
            }
            else if(diffDays==-2){
                try{
            String host ="smtp.gmail.com" ;
            String user = "digitallibrary98@gmail.com";
            String pass = "miniproject1234";
            String to =rs.getString("rno")+"@nirmauni.ac.in";
             System.out.println(to);
            String from = "digitallibrary98@gmail.com";
            String subject = "Due reminder";
            String messageText = "You have 1 item due \n "+rs.getString("booktitle")+" by "+rs.getString("bookauthor")+" in 2 days";
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");
            

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject); msg.setSentDate(new Date());
            msg.setText(messageText);

           Transport transport=mailSession.getTransport("smtp");
           transport.connect(host, user, pass);
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
           System.out.println("message send successfully");
          }catch(Exception ex)
           {
            System.out.println(ex);
           }
            }
        }
    }
}
