import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import UI.StudentPane;
import UI.TeacherPane;

public class App extends Application {
    @Override
    public void start(Stage stage) {

        Tab studentTab = new Tab("Students");
        studentTab.setContent(StudentPane.getPane());
        studentTab.setClosable(false);

        Tab teacherTab = new Tab("Teachers");
        teacherTab.setContent(TeacherPane.getPane());
        teacherTab.setClosable(false);

        TabPane tabPane = new TabPane();
        tabPane.getTabs().addAll(studentTab, teacherTab);

        Scene scene = new Scene(tabPane, 700, 500);

        stage.setTitle("University Management");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}