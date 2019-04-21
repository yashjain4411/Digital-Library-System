/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static mp.Controller.temporary;

/**
 *
 * @author Lenovo
 */
public class admindashboardcontroller {
    @FXML
    void addbooks(ActionEvent event) throws IOException{
        Parent addbook= FXMLLoader.load(getClass().getResource("addbooks.fxml"));
        temporary=(Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage s=new Stage();
        s.setScene(new Scene(addbook));
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
   void searchbooks(ActionEvent event) throws IOException{
       
        Parent search= FXMLLoader.load(getClass().getResource("searchbooks.fxml"));
        temporary=(Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage s=new Stage();
        s.setScene(new Scene(search));
        s.show();
    }
    @FXML
    void adddigitalresource(ActionEvent event) throws IOException{
         Parent addbook= FXMLLoader.load(getClass().getResource("adddigitalresources.fxml"));
        temporary=(Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage s=new Stage();
        s.setScene(new Scene(addbook));
        s.show();
    }
    @FXML
    void viewalldigital(ActionEvent event) throws IOException{
        Parent books= FXMLLoader.load(getClass().getResource("viewalldigital.fxml"));
        Stage s=(Stage) ((Node) event.getSource()).getScene().getWindow();
        s.setScene(new Scene(books));
        s.show();
    }
    @FXML
    void viewallbooks(ActionEvent event) throws IOException {
        Parent books= FXMLLoader.load(getClass().getResource("viewallbooks.fxml"));
        Stage s=(Stage) ((Node) event.getSource()).getScene().getWindow();
        s.setScene(new Scene(books));
        s.show();
    }
    

    @FXML
    void viewdigitalresources(ActionEvent event) {

    }

    @FXML
    void viewmembers(ActionEvent event) throws IOException {
        Parent books= FXMLLoader.load(getClass().getResource("viewallmembers.fxml"));
        Stage s=(Stage) ((Node) event.getSource()).getScene().getWindow();
        s.setScene(new Scene(books));
        s.show();
    }

    @FXML
    void viewsuggestions(ActionEvent event) throws IOException {
         Parent books= FXMLLoader.load(getClass().getResource("viewsuggestedbooks.fxml"));
        Stage s=(Stage) ((Node) event.getSource()).getScene().getWindow();
        s.setScene(new Scene(books));
        s.show();
    }
}
