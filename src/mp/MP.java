/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp;
import java.sql.*;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Lenovo
 */
public class MP extends Application {
    
    public static void main(String[] args) {
        database d=new database();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException{
        Parent login= FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setScene(new Scene(login));
        primaryStage.show();
    }
    
}
