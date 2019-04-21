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

public class viewallmemberscontroller implements Initializable{
    @FXML
    private TableView<Member> members;
    @FXML
    private TableColumn<Member,String> tusername;

    @FXML
    private TableColumn<Member,String> tname;

    @FXML
    private TableColumn<Member,String> tdepartment;

    @FXML
    private TableColumn<Member,String> trno;
    

   
    
    ObservableList<Member> list=FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url,ResourceBundle rb) {
        initializecolumns();
        loaddata();
    }
   
    private void initializecolumns(){
        tusername.setCellValueFactory(new PropertyValueFactory<>("username"));
        tname.setCellValueFactory(new PropertyValueFactory<>("name"));
        tdepartment.setCellValueFactory(new PropertyValueFactory<>("department"));
        trno.setCellValueFactory(new PropertyValueFactory<>("rollno"));
    }
   
    private void loaddata() {
        try{
       database handler=new database();
       String query;
       query="SELECT * FROM USER";
       PreparedStatement prp=handler.connectuser.prepareStatement(query);
       ResultSet rs=prp.executeQuery();
       while(rs.next()){
           String username=rs.getString("username");
           String name=rs.getString("name");
           String department=rs.getString("department");
           String rno=rs.getString("rollno");
           list.add(new Member(username,name,department,rno));
       }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        members.setEditable(true);
        
       members.setItems(list);
      
    }
     @FXML
    void deletemember(ActionEvent event) throws SQLException,IOException{
    Member c=null;
      try{
      c=members.getSelectionModel().getSelectedItem();
   
     
      String query;
      Statement stmt;
      database handler=new database();
      query="DELETE FROM USER WHERE USERNAME = '"+c.getUsername()+"' and "+"ROLLNO = '"+c.getRollno()
              +"'";
      stmt=handler.connectuser.createStatement();
      stmt.executeUpdate(query);
      Alert alert=new Alert(Alert.AlertType.INFORMATION);
      alert.setContentText("Member removed successfully");
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
    void logout(ActionEvent event) throws IOException{
        Parent login= FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage s=(Stage) ((Node) event.getSource()).getScene().getWindow();
        s.setScene(new Scene(login));
        s.show();
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
    
    public static class Member {
       private final SimpleStringProperty username;
       private final SimpleStringProperty name;
       private final SimpleStringProperty department;
       private final SimpleStringProperty rollno;
       Member(String username,String name,String department,String rollno){
           this.username=new SimpleStringProperty(username);
           this.name=new SimpleStringProperty(name);
           this.department=new SimpleStringProperty(department);
           this.rollno=new SimpleStringProperty(rollno);
        
      
       }
       public String getUsername(){
           return username.get();
       }
       public String getName(){
           return name.get();
       }
       public String getDepartment(){
           return department.get();
       }
       public String getRollno(){
           return rollno.get();
       }
      
    }
    
}
   