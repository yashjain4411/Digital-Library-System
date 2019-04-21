/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author Lenovo
 */
public class viewalldigitalcontroller implements Initializable{
     @FXML
    private TableView<Digitalresource> digitalresources;

    @FXML
    private TableColumn<Digitalresource,String> tsubject;

    @FXML
    private TableColumn<Digitalresource,String> turl;

    

    ObservableList<viewalldigitalcontroller.Digitalresource> list=FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url,ResourceBundle rb) {
        initializecolumns();
        loaddata();
    }
   
    private void initializecolumns(){
        tsubject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        turl.setCellValueFactory(new PropertyValueFactory<>("url"));
    }
   
    private void loaddata() {
        try{
       database handler=new database();
       String query;
       query="SELECT * FROM DIGITAL";
       PreparedStatement prp=handler.connectdigital.prepareStatement(query);
       ResultSet rs=prp.executeQuery();
       while(rs.next()){
           String subject=rs.getString("subject");
           String url=rs.getString("link");
           list.add(new viewalldigitalcontroller.Digitalresource(subject,url));
       }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        digitalresources.setEditable(true);
        
       digitalresources.setItems(list);
      
    }
    @FXML
    void deleteresource(ActionEvent event) throws SQLException,IOException{
     Digitalresource c=null;
      try{
      c=digitalresources.getSelectionModel().getSelectedItem();
      
      String query;
      Statement stmt;
      database handler=new database();
      query="DELETE FROM DIGITAL WHERE SUBJECT = '"+c.getSubject()+"' and "+"LINK = '"+c.getUrl()+"'";
      stmt=handler.connectdigital.createStatement();
      stmt.executeUpdate(query);
      Alert alert=new Alert(Alert.AlertType.INFORMATION);
      alert.setContentText("Resource removed successfully");
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


    @FXML
    void logout(ActionEvent event) throws IOException{
        Parent login= FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage s=(Stage) ((Node) event.getSource()).getScene().getWindow();
        s.setScene(new Scene(login));
        s.show();
    }
    public static class Digitalresource {
       private final SimpleStringProperty subject;
       private final SimpleStringProperty url;
      
       Digitalresource(String subject,String url){
           this.subject=new SimpleStringProperty(subject);
           this.url=new SimpleStringProperty(url);
      
       }
       public String getSubject(){
           return subject.get();
       }
       public String getUrl(){
           return url.get();
       }
       
    }
}
