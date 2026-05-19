package University;
import java.sql.Connection; 
import java.sql.DriverManager; 

public class DB {
    static String url = "jdbc:mysql://localhost:3306/db"; 
    static String user = "root"; 
    static String password = ""; 

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(url, user, password); 
    }
}
