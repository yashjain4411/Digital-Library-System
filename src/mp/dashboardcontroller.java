/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;                                                 
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static mp.Controller.temporary;

/**
 *
 * @author Lenovo
 */
public class dashboardcontroller implements Initializable{
    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField rno;

    @FXML
    private JFXTextField issuedbooks;

    @FXML
    private JFXTextField fine;
    @Override
    public void initialize(URL url,ResourceBundle rb){
       name.setText(mp.Controller.currentusername);
       rno.setText(mp.Controller.currentuserrno);
       fine.setText(String.valueOf(mp.Controller.currentuserfine));
    }
    @FXML
    void addbooks(ActionEvent event) throws IOException{
        Parent addbook= FXMLLoader.load(getClass().getResource("addbooks.fxml"));
        temporary=(Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage s=new Stage();
        s.setScene(new Scene(addbook));
        s.show();
    }
    
    @FXML
    void gotologin(ActionEvent event) throws IOException{
     Parent login= FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage s=(Stage) ((Node) event.getSource()).getScene().getWindow();
        s.setScene(new Scene(login));
        s.show();
    }
    
    @FXML
    void gotodashboard(ActionEvent event) throws IOException,SQLException{
        Parent dashboard= FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        Stage s=(Stage) ((Node) event.getSource()).getScene().getWindow();
        s.setScene(new Scene(dashboard));
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
    void reviewbooks(ActionEvent event) throws IOException{
        Parent review= FXMLLoader.load(getClass().getResource("reviews.fxml"));
        Stage s=(Stage) ((Node) event.getSource()).getScene().getWindow();
        s.setScene(new Scene(review));
        s.show();
    }
    @FXML
   void searchbooks(ActionEvent event) throws IOException{
       
        Parent search= FXMLLoader.load(getClass().getResource("searchbooks.fxml"));
        temporary=(Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage s=new Stage();
        s.setScene(new Scene(search));
        s.show();
    }
    @FXML
    void showdigitalresources(ActionEvent event) throws IOException{
        Parent digital= FXMLLoader.load(getClass().getResource("digitalresources.fxml"));
        Stage s=(Stage) ((Node) event.getSource()).getScene().getWindow();
        s.setScene(new Scene(digital));
        s.show();
    }
    
    @FXML
    void suggestbooks(ActionEvent event) throws IOException,NullPointerException {
        Parent suggest= FXMLLoader.load(getClass().getResource("suggestbooks.fxml"));
        Stage s=new Stage();
        s.setScene(new Scene(suggest));
        s.show();
    }
    @FXML
    void viewbooks(ActionEvent event) throws IOException,NullPointerException {
        Parent books= FXMLLoader.load(getClass().getResource("viewissuedbooks.fxml"));
        Stage s=(Stage) ((Node) event.getSource()).getScene().getWindow();
        s.setScene(new Scene(books));
        s.show();
    }
    
}
