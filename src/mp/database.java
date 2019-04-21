/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static javafx.application.Application.launch;

/**
 *
 * @author Lenovo
 */
public class database {
    private static final String user="root";
    private static final String password="";
    private static final String connectionlogin="jdbc:mysql://localhost:3306/login";
    private static final String connectionuser="jdbc:mysql://localhost:3306/user";
    private static final String connectionbook="jdbc:mysql://localhost:3306/book";
    private static final String connectionissuedbooks="jdbc:mysql://localhost:3306/issuedbooks";
    private static final String connectionsuggestedbooks="jdbc:mysql://localhost:3306/suggestedbook";
    private static final String connectiondigital="jdbc:mysql://localhost:3306/digital";
    Connection connectlogin;
    Connection connectuser;
    Connection connectbook;
    Connection connectissuedbooks;
    Connection connectsuggestedbooks;
    Connection connectdigital;
    
    database handler;
    database(){
         connectlogin=null;
         connectuser=null;
         connectbook=null;
         connectissuedbooks=null;
         connectsuggestedbooks=null;
         connectdigital=null;
        try{
           connectlogin=DriverManager.getConnection(connectionlogin,user,password);
           connectuser=DriverManager.getConnection(connectionuser,user,password);
           connectbook=DriverManager.getConnection(connectionbook,user,password);
           connectissuedbooks=DriverManager.getConnection(connectionissuedbooks,user,password);
           connectsuggestedbooks=DriverManager.getConnection(connectionsuggestedbooks,user,password);
           connectdigital=DriverManager.getConnection(connectiondigital,user,password);
           System.out.println("connected");
        
        
        }
        catch(SQLException e){
            System.err.println(e);
        }
        
    }
}
