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

public class viewallbookcontroller implements Initializable{
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
       query="SELECT * FROM BOOK";
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
    void deletebook(ActionEvent event) throws SQLException, IOException{
      Book c=null;
      try{
      c=books.getSelectionModel().getSelectedItem();
      
      String query;
      Statement stmt;
      database handler=new database();
      query="DELETE FROM BOOK WHERE BOOKNAME = '"+c.getBookname()+"' and "+"BOOKAUTHOR = '"+c.getBookauthor()
              +"' and BOOKPUBLICATION = '"+c.getPublisher()+"' and BOOKID = '"+c.getBookid()+"'";
      stmt=handler.connectbook.createStatement();
      stmt.executeUpdate(query);
      Alert alert=new Alert(Alert.AlertType.INFORMATION);
      alert.setContentText("Book removed successfully");
      Parent dashboard= FXMLLoader.load(getClass().getResource("dashboardadmin.fxml"));
            Stage s=(Stage) ((Node) event.getSource()).getScene().getWindow();
            s.setScene(new Scene(dashboard));
            s.show();
            alert.showAndWait();
      
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
   