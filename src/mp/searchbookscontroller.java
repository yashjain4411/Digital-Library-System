package mp;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.event.*;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import static mp.Controller.temporary;

public class searchbookscontroller implements Initializable{
    @FXML
    private JFXTextField Search;
    static String search;
    @FXML
    ObservableList<String> choices=FXCollections.observableArrayList("Title","Author","ID");
    @FXML
    private ChoiceBox choice;
  
     
    
    
    
    @Override
    public void initialize(URL url,ResourceBundle rb) {
        choice.setValue("Title");
        choice.setItems(choices);
    }
   

    
    @FXML
    void cancel(ActionEvent event) {
     Stage temp=(Stage) ((Node) event.getSource()).getScene().getWindow();
     temp.close();
    }
    
    
    
    @FXML
    void search(ActionEvent event) throws IOException{
        search=Search.getText();
        System.out.println("hi");
        if(choice.getValue().toString()=="Title")
            mp.Controller.searchfilter="bookname";
        else if(choice.getValue().toString()=="Author")
            mp.Controller.searchfilter="bookauthor";
        else
            mp.Controller.searchfilter="bookid";
        
        System.out.println( mp.Controller.searchfilter);
        if(Search.getText().isEmpty()){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Enter search details");
            alert.showAndWait();
        }
        else{
        Parent view= FXMLLoader.load(getClass().getResource("viewbooks.fxml"));
        Stage s=(Stage) ((Node) event.getSource()).getScene().getWindow();
        s.close();
        temporary.setScene(new Scene(view));
        }
    }
   

    
    
}
