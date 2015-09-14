package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by YogenRai on 12/20/2014.
 */
public class DatabaseConnection {
    Connection conn=null;
    String userName="root";
    String password="";
    String url="jdbc:mysql://localhost:3306/library_db";
    public Connection getDatabaseConnection(){
        try {
            conn= DriverManager.getConnection(url,userName,password);
            System.out.println("Connected.");
            return conn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
