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
import University.Students;

public class StudentPane {

    public static VBox getPane() {

        ObservableList<Students> data = FXCollections.observableArrayList();

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

        // TABLE
        TableView<Students> table = new TableView<>();

        TableColumn<Students, Integer> idCol = new TableColumn<>("ID");

        idCol.setCellValueFactory(
                new PropertyValueFactory<>("ID")
        );

        TableColumn<Students, String> nameCol = new TableColumn<>("Name");

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Students, String> deptCol = new TableColumn<>("Department");
        deptCol.setCellValueFactory(new PropertyValueFactory<>("dept"));

        TableColumn<Students, Character> sectionCol =new TableColumn<>("Section");

        sectionCol.setCellValueFactory(new PropertyValueFactory<>("section"));

        TableColumn<Students, Integer> yearCol = new TableColumn<>("Year");
        yearCol.setCellValueFactory(new PropertyValueFactory<>("year"));

        table.getColumns().addAll(idCol, nameCol, deptCol, sectionCol, yearCol);

        table.setItems(data);

        // LOAD STUDENTS

        try {
            Connection conn = DB.getConnection();
            ResultSet rs = Students.getStudents(conn);

            while (rs.next()) {
                Students s = new Students();
                s.ID = rs.getInt("id");
                s.name = rs.getString("name");
                s.dept = rs.getString("department");

                s.section = rs.getString("section").charAt(0);
                s.year = rs.getInt("year");
                data.add(s);
            }

            conn.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // BUTTON ACTION
        addButton.setOnAction(e -> {

            try {
                Students s = new Students();
                s.ID = Integer.parseInt(idField.getText());
                s.name = nameField.getText();
                s.dept = deptField.getText();
                s.section = sectionField.getText().charAt(0);
                s.year = Integer.parseInt(yearField.getText());

                Connection conn = DB.getConnection();
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

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        
        layout.getChildren().addAll(idField, nameField, deptField, sectionField, yearField, addButton, table);

        return layout;
    }
}