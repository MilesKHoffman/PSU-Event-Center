module com.example.sweng411projjavafx {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.sweng411projjavafx to javafx.fxml;
    exports com.example.sweng411projjavafx;
}