package UI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.ResultSet;

import University.DB;
import University.Teacher;

public class TeacherPane {

    public static VBox getPane() {

        ObservableList<Teacher> teacherData =
                FXCollections.observableArrayList();

        // INPUT FIELDS

        TextField teacherIDField =new TextField();
        teacherIDField.setPromptText("Teacher ID");

        TextField teacherNameField = new TextField();
        teacherNameField.setPromptText("Teacher Name");

        TextField teacherDeptField = new TextField();
        teacherDeptField.setPromptText("Department");

        Button addTeacherButton = new Button("Add Teacher");

        // TABLE
        TableView<Teacher> teacherTable = new TableView<>();

        TableColumn<Teacher, Integer> teacherIDCol = new TableColumn<>("ID");
        teacherIDCol.setCellValueFactory(new PropertyValueFactory<>("ID"));

        TableColumn<Teacher, String> teacherNameCol = new TableColumn<>("Name");

        teacherNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Teacher, String> teacherDeptCol = new TableColumn<>("Department");

        teacherDeptCol.setCellValueFactory(new PropertyValueFactory<>("dept"));

        teacherTable.getColumns().addAll(teacherIDCol, teacherNameCol, teacherDeptCol);

        teacherTable.setItems(teacherData);

        // LOAD TEACHERS
        try {
            Connection conn = DB.getConnection();
            ResultSet rs = Teacher.getTeachers(conn);

            while (rs.next()) {
                Teacher t = new Teacher();
                t.ID = rs.getInt("id");
                t.name = rs.getString("name");

                t.dept = rs.getString("department");
                teacherData.add(t);
            }
            conn.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // BUTTON ACTION
        addTeacherButton.setOnAction(e -> {
            try {
                Teacher t = new Teacher();

                t.ID = Integer.parseInt(teacherIDField.getText());
                t.name = teacherNameField.getText();
                t.dept = teacherDeptField.getText();

                Connection conn = DB.getConnection();
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

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        
        layout.getChildren().addAll(teacherIDField, teacherNameField, teacherDeptField, addTeacherButton, teacherTable
        );

        return layout;
    }
}