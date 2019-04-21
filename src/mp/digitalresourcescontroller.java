package mp;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

public class digitalresourcescontroller implements Initializable{

    @FXML
    private TableView<Digitalresource> digitalresources;

    @FXML
    private TableColumn<Digitalresource,String> tsubject;

    @FXML
    private TableColumn<Digitalresource,String> turl;

    

    ObservableList<digitalresourcescontroller.Digitalresource> list=FXCollections.observableArrayList();
    
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
           list.add(new digitalresourcescontroller.Digitalresource(subject,url));
       }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        digitalresources.setEditable(true);
        
       digitalresources.setItems(list);
      
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
    void logout(ActionEvent event) throws IOException{
        Parent login= FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage s=(Stage) ((Node) event.getSource()).getScene().getWindow();
        s.setScene(new Scene(login));
        s.show();
    }
     @FXML
    void view(ActionEvent event) {
     Digitalresource c=null;
      try{
      c=digitalresources.getSelectionModel().getSelectedItem();
      openWebpage(c.getUrl());
      
      }
      catch(NullPointerException e){
          Alert alert=new Alert(Alert.AlertType.ERROR);
          alert.setContentText("NO ITEM SELECTED");
          alert.showAndWait();
      }
    }
    public static void openWebpage(String urlString) {
    try {
        Desktop.getDesktop().browse(new URL(urlString).toURI());
    } catch (Exception e) {
        e.printStackTrace();
    }
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
