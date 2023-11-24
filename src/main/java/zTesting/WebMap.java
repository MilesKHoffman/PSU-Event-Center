package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.File;

public class WebMap extends Application {

    @Override
    public void start(Stage stage) {

        File url = new File("C:\\1. School\\SWENG  411\\DemoFX\\demoFX\\src\\main\\" +
                "java\\TestFiles\\demo.html");

        // Create a WebView
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        // Load HTML file into WebEngine (maps: https://www.google.com/maps)
        webEngine.load(url.toURI().toString());

        // Create a scene and add the WebView to it
        Scene scene = new Scene(webView, 800, 600);

        // Set up the primary stage
        stage.setTitle("WebView File Demo");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}