package University;

import java.io.*;
import java.sql.*;

public class Teacher implements Serializable{
    public int ID; 
    public String name; 
    public String dept; 

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getDept() {
        return dept;
    }
    
    
    public void addTeacher(Connection conn) {
        String sql = "INSERT INTO teacher VALUES (?, ?, ?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql); 

            pstmt.setInt(1, ID); 
            pstmt.setString(2, name); 
            pstmt.setString(3, dept); 

            pstmt.executeUpdate(); 

            System.out.println("teacher saved to database"); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    public void showTeacher(Statement stmt) {
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM teacher");

            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + " " +
                    rs.getString("name") + " " +
                    rs.getString("department")
                );
            }
            
        } catch (Exception e) {
            System.out.println(e); 
        }
    
    }

    public static ResultSet getTeachers(Connection conn) {
        try {
            String sql = "SELECT * FROM teacher"; 

            PreparedStatement pstmt = conn.prepareStatement(sql); 
            return pstmt.executeQuery(); 
            
        } catch (Exception e) {
            e.printStackTrace();
            return null; 
        }
    }
}
