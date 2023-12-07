package Model;

import View.ViewClass;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.File;
import java.util.function.Consumer;

import com.google.gson.Gson;


public class Map extends ViewClass {

    private static Map map = new Map();
    private String name, location;
    private double latitude, longitude;
    private WebView webView;
    private static WebEngine webEngine;

    private VBox mapVBox;

    private Map() {
        String dir = System.getProperty("user.dir");
        File url = new File( dir + "/src/main/java/zTesting/demo.html" );

        // Create a WebView
        webView = new WebView();
        webEngine = webView.getEngine();

        // Load HTML file into WebEngine (maps: https://www.google.com/maps)
        webEngine.load(url.toURI().toString());

        mapVBox = new VBox();
        mapVBox.getChildren().add(webView);
    }

    public static void setMapCenter(double latitude, double longitude) {
        webEngine.executeScript("document.dispatchEvent(new CustomEvent('setMapCenter', {detail: {latitude: "
                + latitude + ", longitude: " + longitude + "}}));");
    }

    // Java method to handle map clicks
    public static void mapClick(Consumer<int[]> callback) {
        webEngine.executeScript(
                "document.getElementById('map_canvas').addEventListener('click', function(event) {" +
                        "var latLng = event.latLng;" +
                        "var latitude = latLng.lat();" +
                        "var longitude = latLng.lng();" +
                        "document.dispatchEvent(new CustomEvent('mapClick', { detail: { latitude: latitude, longitude: longitude } }));" +
                        "});"
        );

        webEngine.setOnStatusChanged(event -> {
            if ("mapClick".equals(event.getData())) {
                String jsonString = (String) webEngine.executeScript(
                        "JSON.stringify(window.mapClickData);"
                );

                Gson gson = new Gson();
                int[] coords = gson.fromJson(jsonString, int[].class);

                Platform.runLater(() -> callback.accept(coords));
            }
        });
    }

    public static Map getInstance() {
        return map;
    }

    public VBox getMapVBox() {
        return mapVBox;
    }
}
