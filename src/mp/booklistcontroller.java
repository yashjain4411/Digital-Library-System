package mp;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import static mp.Controller.currentuser;

public class booklistcontroller implements Initializable{
    @FXML
    private TableView<Book> books;
    @FXML
    private TableColumn<Book,String> tbookname;

    @FXML
    private TableColumn<Book,String> tbookauthor;

    @FXML
    private TableColumn<Book,String> tbookid;

    @FXML
    private TableColumn<Book,String> tbookavailability;
    @FXML
    private TableColumn<Book,String> tbookpublisher;

   
    
    ObservableList<Book> list=FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url,ResourceBundle rb) {
        initializecolumns();
        loaddata();
    }
   
    private void initializecolumns(){
        tbookname.setCellValueFactory(new PropertyValueFactory<>("bookname"));
        tbookid.setCellValueFactory(new PropertyValueFactory<>("bookid"));
        tbookauthor.setCellValueFactory(new PropertyValueFactory<>("bookauthor"));
        tbookavailability.setCellValueFactory(new PropertyValueFactory<>("bookavailability"));
        tbookpublisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
    }
   
    private void loaddata() {
        try{
       database handler=new database();
       String query;
       query="SELECT * FROM BOOK WHERE "+mp.Controller.searchfilter+" = "+"'"+mp.searchbookscontroller.search+"'";
       PreparedStatement prp=handler.connectbook.prepareStatement(query);
       ResultSet rs=prp.executeQuery();
       while(rs.next()){
           String title=rs.getString("bookname");
           String id=rs.getString("bookid");
           String author=rs.getString("bookauthor");
           String availability=rs.getString("Availability");
           String publisher=rs.getString("bookpublication");
           System.out.println(publisher);
           list.add(new Book(title,author,publisher,id,availability));
       }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        books.setEditable(true);
        
       books.setItems(list);
      
    }
    @FXML
    void logout(ActionEvent event) throws IOException{
        Parent login= FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage s=(Stage) ((Node) event.getSource()).getScene().getWindow();
        s.setScene(new Scene(login));
        s.show();
    }
     @FXML
    void issuebook(ActionEvent event) throws  IOException,SQLException{
      Book c=null;
      try{
      c=books.getSelectionModel().getSelectedItem();
     
      if(c.getBookavailability().equals("YES")){
      String query;
      Statement stmt;
      database handler=new database();
      query="UPDATE BOOK SET AVAILABILITY = 'NO' WHERE BOOKNAME = '"+c.getBookname()+"' and "+"BOOKAUTHOR = '"+c.getBookauthor()
              +"' and BOOKPUBLICATION = '"+c.getPublisher()+"' and BOOKID = '"+c.getBookid()+"'";
      stmt=handler.connectbook.createStatement();
      stmt.executeUpdate(query);
      Date d=new Date();
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
      String issuedate = simpleDateFormat.format(d);
      Calendar cal=new GregorianCalendar();
      cal.setTime(d);
      cal.add(Calendar.DATE, 15);
      Date d1=cal.getTime();
      String returndate=simpleDateFormat.format(d1);
      query="INSERT INTO ISSUEDBOOKS VALUES("
                +"'"+c.getBookname()+"',"
                +"'"+c.getBookauthor()+"',"
                +"'"+c.getPublisher()+"',"  
                +"'"+c.getBookid()+"',"
                +"'"+mp.Controller.currentuser+"',"
                +"'"+issuedate+"',"
                +"'"+returndate+"',"
                +"'"+mp.Controller.currentuserrno+"',"
                +"'"+returndate+"'"
                +")";
            stmt=handler.connectissuedbooks.createStatement();
            stmt.executeUpdate(query);
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Issued successfully");
            
            Parent dashboard= FXMLLoader.load(getClass().getResource("dashboard.fxml"));
            Stage s=(Stage) ((Node) event.getSource()).getScene().getWindow();
            s.setScene(new Scene(dashboard));
            s.show();
            alert.showAndWait();
            try{
            String host ="smtp.gmail.com" ;
            String user = "digitallibrary98@gmail.com";
            String pass = "miniproject1234";
            String to =mp.Controller.currentuserrno+"@nirmauni.ac.in";
            String from = "digitallibrary98@gmail.com";
            String subject = "book returned";
            String messageText = "The book "+c.getBookname()+" by "+c.getBookauthor()+" has been issued to you today.";
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
      
      
      else{
          Alert alert=new Alert(Alert.AlertType.ERROR);
          alert.setContentText("BOOK NOT AVAILABLE");
          alert.showAndWait();
      }
       }
      catch(NullPointerException e){
          Alert alert=new Alert(Alert.AlertType.ERROR);
          alert.setContentText("NO ITEM SELECTED");
          alert.showAndWait();
      }
    }
    @FXML
    void gotodashboard(ActionEvent event) throws IOException,SQLException{
        Parent dashboard;
        System.out.println("hiii0");
        if(mp.Controller.currentuser.equals("admin"))
        dashboard= FXMLLoader.load(getClass().getResource("dashboardadmin.fxml"));
        else
        dashboard= FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        Stage s=(Stage) ((Node) event.getSource()).getScene().getWindow();
        s.setScene(new Scene(dashboard));
        s.show();
    }
    
    public static class Book {
       private final SimpleStringProperty bookname;
       private final SimpleStringProperty bookauthor;
       private final SimpleStringProperty bookid;
       private final SimpleStringProperty bookavailability;
       private final SimpleStringProperty publisher;
       Book(String bookname,String bookauthor,String publisher,String bookid,String bookavailability){
           this.bookauthor=new SimpleStringProperty(bookauthor);
           this.bookavailability=new SimpleStringProperty(bookavailability);
           this.bookid=new SimpleStringProperty(bookid);
           this.publisher=new SimpleStringProperty(publisher);
           this.bookname=new SimpleStringProperty(bookname);
      
       }
       public String getBookname(){
           return bookname.get();
       }
       public String getBookauthor(){
           return bookauthor.get();
       }
       public String getBookid(){
           return bookid.get();
       }
       public String getBookavailability(){
           return bookavailability.get();
       }
       public String getPublisher(){
           return publisher.get();
       }
    }
    
}
   