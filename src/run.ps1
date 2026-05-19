# javac -cp ".;..\lib\mysql-connector-j-9.7.0.jar" App.java

# java -cp ".;..\lib\mysql-connector-j-9.7.0.jar" App

javac --module-path "C:/Users/hp/Downloads/openjfx-26.0.1_windows-x64_bin-sdk/javafx-sdk-26.0.1/lib" --add-modules javafx.controls,javafx.fxml -classpath ".;..\lib\mysql-connector-j-9.7.0.jar" App.java University/*.java

java --module-path "C:/Users/hp/Downloads/openjfx-26.0.1_windows-x64_bin-sdk/javafx-sdk-26.0.1/lib" --add-modules javafx.controls,javafx.fxml -classpath ".;..\lib\mysql-connector-j-9.7.0.jar" App