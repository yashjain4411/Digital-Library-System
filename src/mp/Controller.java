package mp;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.*;
import javafx.event.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.*;
import java.net.*;
import java.util.*;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.Alert;
import javafx.scene.control.*;
import javafx.collections.*;
public class Controller implements Initializable{
    static Stage temporary=null;
    @FXML
    private JFXTextField username;
    static String currentuser;
    static String currentusername;
    static String currentuserrno;
    static int currentuserfine;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXTextField digitalresources;
     @FXML
    private JFXTextField addSubject;

    @FXML
    private JFXTextField addUrl;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField id;

    @FXML
    private JFXTextField issuedbooks;

    @FXML
    private JFXTextField fine;
  
    @FXML
    private JFXTextField sbooktitle;

    @FXML
    private JFXTextField sbookauthor;

    @FXML
    private JFXTextField sbookpub;

    @FXML
    private JFXTextField sbookedition;

  

    @FXML
    static protected String searchfilter;
    
    @FXML
    ResultSet resultset;
    @FXML
    Statement stmt,stmtuser,stmtbook,stmtdigital;
    
    @FXML
    static database handler;
    @FXML
    private JFXTextField signupname;

    @FXML
    private JFXTextField newusername;

    @FXML
    private JFXTextField department;

    @FXML
    private JFXTextField rno;

    @FXML
    private JFXTextField newpassword;

    @FXML
    private JFXTextField newconfirmpassword;

    @FXML
    private JFXTextField addbookid;

    @FXML
    private JFXTextField addbookname;

    @FXML
    private JFXTextField addbookauthor;

    @FXML
    private JFXTextField addbookpub;

    @FXML
    private JFXTextField addbookedition;

    


    @Override
    public void initialize(URL url,ResourceBundle rb){
      handler=new database();
      
    }
    @FXML
    void adddigital(ActionEvent event) throws IOException,SQLException {
     if(addSubject.getText().isEmpty()||addUrl.getText().isEmpty())
     {
         Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill all details");
            alert.showAndWait();
     }
     else{
         try{
            String query="INSERT INTO DIGITAL VALUES("
                +"'"+addSubject.getText()+"',"
                +"'"+addUrl.getText()+"'"
                +")";
            stmtdigital=handler.connectdigital.createStatement();
            stmtdigital.executeUpdate(query);
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Resource added successfully");
            
            alert.showAndWait();
         }
         catch(com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Url already exists");
            
            alert.showAndWait();
           }
    }
    }
    @FXML
    void addbook(ActionEvent event) throws IOException,SQLException {
     if(addbookid.getText().isEmpty()||addbookname.getText().isEmpty()|| addbookauthor.getText().isEmpty() ||addbookpub.getText().isEmpty() || addbookedition.getText().isEmpty())
     {
         Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill all details");
            alert.showAndWait();
     }
     else{
         try{
            String query="INSERT INTO BOOK VALUES("
                +"'"+addbookid.getText()+"',"
                +"'"+addbookname.getText()+"',"
                +"'"+addbookauthor.getText()+"',"  
                +"'"+addbookpub.getText()+"',"  
                +"'"+addbookedition.getText()+"',"
                +"'"+"YES"+"'"
                +")";
            stmtbook=handler.connectbook.createStatement();
            stmtbook.executeUpdate(query);
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Book added successfully");
            
            alert.showAndWait();
         }
         catch(com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Bookid already exists");
            
            alert.showAndWait();
           }
    }
    }
     @FXML
    void gotologin(ActionEvent event) throws IOException{
     Parent login= FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage s=(Stage) ((Node) event.getSource()).getScene().getWindow();
        s.setScene(new Scene(login));
        s.show();
    }
    @FXML
    void signup(ActionEvent event) throws IOException,SQLException {
        
     if(signupname.getText().isEmpty()||newusername.getText().isEmpty()|| rno.getText().isEmpty() ||newpassword.getText().isEmpty() || newconfirmpassword.getText().isEmpty())
     {
         Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill all details");
            alert.showAndWait();
     }
     else{
         if(!(newpassword.getText().equals(newconfirmpassword.getText()))){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Passwords do not match");
            alert.showAndWait();
         }
         else{
             try{
            String query="INSERT INTO USER VALUES("
                +"'"+signupname.getText()+"',"
                +"'"+newusername.getText()+"',"
                +"'"+department.getText()+"',"  
                +"'"+rno.getText()+"',"  
                +"'"+newpassword.getText()+"',"
                +"'"+"0"+"'"
                +")";
            stmtuser=handler.connectuser.createStatement();
            stmtuser.executeUpdate(query);
            resultset=stmtuser.executeQuery("SELECT * FROM user LIMIT 100");
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Successfuly registered");
            Parent login= FXMLLoader.load(getClass().getResource("login.fxml"));
            Stage s=(Stage) ((Node) event.getSource()).getScene().getWindow();
            s.setScene(new Scene(login));
            s.show();
            alert.showAndWait();
             }
             
           catch(com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Username already exists");
            
            alert.showAndWait();
           }
             
         }
     }
         
    }
    
    
    @FXML
    void login(ActionEvent event) throws IOException,SQLException{
        
        if(username.getText().isEmpty() || password.getText().isEmpty()){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill all details");
            alert.showAndWait();
        }
        else{
        
        String query="SELECT NAME,DEPARTMENT,ROLLNO,USERNAME,PASSWORD,FINE FROM USER WHERE USERNAME="
                +"'"+username.getText()+"'";
        PreparedStatement prp=handler.connectuser.prepareStatement(query);
        resultset=prp.executeQuery();
        if(resultset.next()){
        currentuser=username.getText();
        currentusername=resultset.getString("name");
        currentuserrno=resultset.getString("rollno");
        currentuserfine=resultset.getInt("fine");
        System.out.println(currentuser);
        if(password.getText().equals(resultset.getString("password"))){
        Parent dashboard;
        if(currentusername.equals("admin"))
        dashboard= FXMLLoader.load(getClass().getResource("dashboardadmin.fxml"));
        else
        dashboard= FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        
        Stage s=(Stage) ((Node) event.getSource()).getScene().getWindow();
        
        s.setScene(new Scene(dashboard));
        
        s.show();
        }
        
        else{
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("password incorrect");
            
            alert.showAndWait();
        }
        }
        else{
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Username doesn't exist");
            
            alert.showAndWait();
        }
        
        
        }
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
    
    @FXML
    void gotoforgotpassword(ActionEvent event) {
         System.out.print("hello");
    }
    @FXML
    void cancel(ActionEvent event) {
        Stage temp=(Stage) ((Node) event.getSource()).getScene().getWindow();
        temp.close();
    }
    @FXML
    void gotonewuser(ActionEvent event) throws IOException{
        Parent signup= FXMLLoader.load(getClass().getResource("newaccount.fxml"));
        Stage s=(Stage) ((Node) event.getSource()).getScene().getWindow();
        s.setScene(new Scene(signup));
        s.show();
    }
    @FXML
    void logout(ActionEvent event) throws IOException{
        Parent login= FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage s=(Stage) ((Node) event.getSource()).getScene().getWindow();
        s.setScene(new Scene(login));
        s.show();
    }
    @FXML
    void suggest(ActionEvent event) throws IOException,SQLException{
        if(sbooktitle.getText().isEmpty()|| sbookauthor.getText().isEmpty() ||sbookpub.getText().isEmpty() || sbookedition.getText().isEmpty())
     {
         Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill all details");
            alert.showAndWait();
     }
     else{
         
            String query="INSERT INTO SUGGESTEDBOOK VALUES("
                +"'"+sbooktitle.getText()+"',"
                +"'"+sbookauthor.getText()+"',"  
                +"'"+sbookpub.getText()+"',"  
                +"'"+sbookedition.getText()+"'"
                +")";
            stmtbook=handler.connectsuggestedbooks.createStatement();
            stmtbook.executeUpdate(query);
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Book suggested successfully");
            
            alert.showAndWait();
         
         
    }
    }
    
    
   
    
   

}
