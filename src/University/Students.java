package University;

import java.io.*;
import java.sql.*;

public class Students implements Serializable{
    public int ID; 
    public String name; 
    public String dept; 
    public char section; 
    public int year; 

    public int getID() {
    return ID;
    }

    public String getName() {
        return name;
    }

    public String getDept() {
        return dept;
    }

    public char getSection() {
        return section;
    }

    public int getYear() {
        return year;
    }
    

    public void addStudent(Connection conn) {            
        String sql = "INSERT INTO student VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, ID);
            pstmt.setString(2, name);
            pstmt.setString(3, dept);
            pstmt.setString(4, String.valueOf(section));
            pstmt.setInt(5, year);

            pstmt.executeUpdate();

            System.out.println("student saved to database");

        } catch (Exception e) {
            e.printStackTrace();
        }

    } 

    public void showStudent(Statement stmt) {
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM student");

            while (rs.next()) {

                System.out.println(
                    rs.getInt("id") + " " +
                    rs.getString("name") + " " +
                    rs.getString("department") + " " +
                    rs.getString("section") + " " +
                    rs.getInt("year")
                );
            }

        } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static ResultSet getStudents(Connection conn) {

        try {

            String sql = "SELECT * FROM student";

            PreparedStatement pstmt =
                    conn.prepareStatement(sql);

            return pstmt.executeQuery();

        } catch (Exception e) {

            e.printStackTrace();

            return null;
        }
    }
    
}