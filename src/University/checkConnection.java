package University; 

import java.sql.Connection;
import java.sql.DriverManager; 
import java.sql.ResultSet; 
import java.sql.Statement;

// import javafx.scene.SubScene; 

public class checkConnection {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/db";
        String user = "root"; 
        String password = ""; 

        try {
            Connection conn = DriverManager.getConnection(url, user, password); 

            if (conn != null && conn.isValid(2)) {
                System.out.println("connected") ; 
            }

            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery("SELECT 1"); 

            if (rs.next()) {
                System.out.println("works"); 
            }

            
        } catch(Exception e) {
            System.out.println("failed"); 
            e.printStackTrace(); 
        }
        
    }
}








// import java.sql.Connection;
// import java.sql.DriverManager;

// public class DBConnection {
//     public static Connection connect() {
//         try {
//             String url = "jdbc:mysql://localhost:3306/notepad_db";
//             String user = "root";
//             String password = "";

//             Connection conn = DriverManager.getConnection(url, user, password);
//             System.out.println("Connected to database!");

//             return conn;
//         } catch (Exception e) {
//             e.printStackTrace();
//             return null;
//         }
//     }
// }