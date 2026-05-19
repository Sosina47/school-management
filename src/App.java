import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.sql.*;

import University.Students;
import University.Teacher;

public class App extends Application {

    TableView<Students> table = new TableView<>();

    ObservableList<Students> data =
            FXCollections.observableArrayList();

    ObservableList<Teacher> teacherData =
        FXCollections.observableArrayList();    

    @Override
    public void start(Stage stage) {

        // INPUT FIELDS
        TextField idField = new TextField();
        idField.setPromptText("ID");

        TextField nameField = new TextField();
        nameField.setPromptText("Name");

        TextField deptField = new TextField();
        deptField.setPromptText("Department");

        TextField sectionField = new TextField();
        sectionField.setPromptText("Section");

        TextField yearField = new TextField();
        yearField.setPromptText("Year");

        Button addButton = new Button("Add Student");

        // TABLE COLUMNS
        TableColumn<Students, Integer> idCol =
                new TableColumn<>("ID");

        idCol.setCellValueFactory(
                new PropertyValueFactory<>("ID"));

        TableColumn<Students, String> nameCol =
                new TableColumn<>("Name");

        nameCol.setCellValueFactory(
                new PropertyValueFactory<>("name"));

        TableColumn<Students, String> deptCol =
                new TableColumn<>("Department");

        deptCol.setCellValueFactory(
                new PropertyValueFactory<>("dept"));

        TableColumn<Students, Character> sectionCol =
                new TableColumn<>("Section");

        sectionCol.setCellValueFactory(
                new PropertyValueFactory<>("section"));

        TableColumn<Students, Integer> yearCol =
                new TableColumn<>("Year");

        yearCol.setCellValueFactory(
                new PropertyValueFactory<>("year"));

        table.getColumns().addAll(
                idCol,
                nameCol,
                deptCol,
                sectionCol,
                yearCol
        );

        table.setItems(data);

        // load students from database
        try {

            String url = "jdbc:mysql://localhost:3306/db";
            String user = "root";
            String password = "";

            Connection conn =
                    DriverManager.getConnection(
                            url,
                            user,
                            password
                    );

            ResultSet rs = Students.getStudents(conn);

            while (rs.next()) {

                Students s = new Students();

                s.ID = rs.getInt("id");
                s.name = rs.getString("name");
                s.dept = rs.getString("department");
                s.section =
                        rs.getString("section").charAt(0);

                s.year = rs.getInt("year");

                data.add(s);
            }

            conn.close();

        } catch (Exception ex) {

            ex.printStackTrace();
        }   

        // load teachers from database 
        try {
                String url = "jdbc:mysql://localhost:3306/db"; 
                String user = "root"; 
                String password = ""; 

                Connection conn = DriverManager.getConnection(url, user, password); 
                ResultSet rs = Teacher.getTeachers(conn); 

                while (rs.next()) {
                        Teacher t = new Teacher(); 
                        t.ID = rs.getInt("id"); 
                        t.name = rs.getString("name"); 
                        t.dept = rs.getString("department"); 
                        teacherData.add(t); 
                }
                conn.close(); 
        } catch (Exception e) {
                e.printStackTrace();
        }


        addButton.setOnAction(e -> {

            try {

                Students s = new Students();

                s.ID = Integer.parseInt(idField.getText());
                s.name = nameField.getText();
                s.dept = deptField.getText();
                s.section = sectionField.getText().charAt(0);
                s.year = Integer.parseInt(yearField.getText());

                String url = "jdbc:mysql://localhost:3306/db";
                String user = "root";
                String password = "";

                Class.forName("com.mysql.cj.jdbc.Driver");

                Connection conn =
                        DriverManager.getConnection(
                                url,
                                user,
                                password
                        );

                s.addStudent(conn);
                data.add(s);
                conn.close();

                idField.clear();
                nameField.clear();
                deptField.clear();
                sectionField.clear();
                yearField.clear();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });        

        VBox studentLayout = new VBox(10); 
        studentLayout.setPadding(new Insets(10));

        studentLayout.getChildren().addAll(
                idField, 
                nameField, 
                deptField, 
                sectionField, 
                addButton, 
                table
        ); 

        Tab studenTab = new Tab("Students");
        studenTab.setContent(studentLayout);
        studenTab.setClosable(false);
        
        VBox teacherLayout = new VBox(10);

        teacherLayout.setPadding(new Insets(10));

        TextField teacherIDField =
                new TextField();

        teacherIDField.setPromptText("Teacher ID");

        TextField teacherNameField =
                new TextField();

        teacherNameField.setPromptText("Teacher Name");

        TextField teacherDeptField =
                new TextField();

        teacherDeptField.setPromptText("Department");

        Button addTeacherButton =
                new Button("Add Teacher");

        TableView<Teacher> teacherTable =
                new TableView<>();


        TableColumn<Teacher, Integer> teacherIDCol =
                new TableColumn<>("ID");

        teacherIDCol.setCellValueFactory(
                new PropertyValueFactory<>("ID")
        );

        TableColumn<Teacher, String> teacherNameCol =
                new TableColumn<>("Name");

        teacherNameCol.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );

        TableColumn<Teacher, String> teacherDeptCol =
                new TableColumn<>("Department");

        teacherDeptCol.setCellValueFactory(
                new PropertyValueFactory<>("dept")
        );

        teacherTable.getColumns().addAll(
                teacherIDCol,
                teacherNameCol,
                teacherDeptCol
        );

        teacherTable.setItems(teacherData);

        addTeacherButton.setOnAction(e -> {
                try {
                        Teacher t = new Teacher();

                        t.ID = Integer.parseInt (teacherIDField.getText());
                        t.name = teacherNameField.getText();
                        t.dept =teacherDeptField.getText();

                        String url ="jdbc:mysql://localhost:3306/db";
                        String user = "root";
                        String password = "";

                        Connection conn =
                                DriverManager.getConnection(
                                        url,
                                        user,
                                        password
                                );

                        t.addTeacher(conn);
                        teacherData.add(t);
                        conn.close();

                        teacherIDField.clear();
                        teacherNameField.clear();
                        teacherDeptField.clear();

                } catch (Exception ex) {
                        ex.printStackTrace();
                }
        });

        Tab teacherTab = new Tab("Teachers"); 
        teacherTab.setContent(teacherLayout);

        teacherTab.setClosable(false);       
        
        TabPane tabPane = new TabPane(); 
        tabPane.getTabs().addAll(
                studenTab, 
                teacherTab
        ); 

        teacherLayout.getChildren().addAll(
                teacherIDField,
                teacherNameField,
                teacherDeptField,
                addTeacherButton,
                teacherTable
        );
        
        Scene scene = new Scene(tabPane, 700, 500); 

        stage.setTitle("Student Management");

        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}