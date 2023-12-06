module com.example.sweng411projjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;

    exports zTesting;
    exports Controller;
    exports Model;
    exports View;
}