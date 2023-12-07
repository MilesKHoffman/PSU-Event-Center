module com.example.sweng411projjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;
    requires sqlite.jdbc;
    requires com.google.gson;
    requires jdk.jsobject;

    exports zTesting;
    exports Controller;
    exports Model;
    exports View;
}