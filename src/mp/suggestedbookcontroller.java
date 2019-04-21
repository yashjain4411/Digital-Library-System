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

public class suggestedbookcontroller implements Initializable{
    @FXML
    private TableView<Book> books;
    @FXML
    private TableColumn<Book,String> tbookname;

    @FXML
    private TableColumn<Book,String> tbookauthor;


    @FXML
    private TableColumn<Book,String> tbookpublisher;

      
    @FXML
    private TableColumn<Book,String> tbookedition;
    
    ObservableList<Book> list=FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url,ResourceBundle rb) {
        initializecolumns();
        loaddata();
    }
   
    private void initializecolumns(){
        tbookname.setCellValueFactory(new PropertyValueFactory<>("bookname"));
        tbookauthor.setCellValueFactory(new PropertyValueFactory<>("bookauthor"));
        tbookpublisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        tbookedition.setCellValueFactory(new PropertyValueFactory<>("bookedition"));
    }
   
    private void loaddata() {
        try{
       database handler=new database();
       String query;
       query="SELECT * FROM SUGGESTEDBOOK";
       PreparedStatement prp=handler.connectsuggestedbooks.prepareStatement(query);
       ResultSet rs=prp.executeQuery();
       while(rs.next()){
           String title=rs.getString("bookname");
           String author=rs.getString("bookauthor");
           String publisher=rs.getString("bookpublication");
           String edition=rs.getString("bookedition");
           list.add(new Book(title,author,publisher,edition));
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
    void gotodashboard(ActionEvent event) throws IOException,SQLException{
        Parent dashboard;
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
       private final SimpleStringProperty publisher;
       private final SimpleStringProperty bookedition;
       Book(String bookname,String bookauthor,String publisher,String bookedition){
           this.bookauthor=new SimpleStringProperty(bookauthor);
           
           this.publisher=new SimpleStringProperty(publisher);
           this.bookname=new SimpleStringProperty(bookname);
           this.bookedition=new SimpleStringProperty(bookedition);
       }
       public String getBookname(){
           return bookname.get();
       }
       public String getBookauthor(){
           return bookauthor.get();
       }
      
       public String getPublisher(){
           return publisher.get();
       }
       public String getBookedition(){
           return bookedition.get();
       }
    }
    
}
   