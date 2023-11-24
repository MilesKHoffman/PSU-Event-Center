package zTesting;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.File;

public class MapFunctionsTest extends Application {

    private Timeline locationUpdateTimeline;

    @Override
    public void start(Stage stage) {

        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        File url = new File("C:\\1. School\\SWENG  411\\DemoFX\\demoFX\\src\\main\\" +
                "java\\TestFiles\\demo.html");

        webEngine.load( url.toURI().toString() );

        // create map type buttons
        final ToggleGroup mapTypeGroup = new ToggleGroup();
        final ToggleButton road = new ToggleButton("Road");
        road.setSelected(true);
        road.setToggleGroup(mapTypeGroup);
        final ToggleButton satellite = new ToggleButton("Satellite");
        satellite.setToggleGroup(mapTypeGroup);
        final ToggleButton hybrid = new ToggleButton("Hybrid");
        hybrid.setToggleGroup(mapTypeGroup);
        final ToggleButton terrain = new ToggleButton("Terrain");
        terrain.setToggleGroup(mapTypeGroup);
        mapTypeGroup.selectedToggleProperty().addListener(
                new ChangeListener<Toggle>() {
                    public void changed(
                            ObservableValue<? extends Toggle> observableValue,
                            Toggle toggle, Toggle toggle1) {
                        if (road.isSelected()) {
                            webEngine.executeScript("document.setMapTypeRoad()");
                        } else if (satellite.isSelected()) {
                            webEngine.executeScript("document.setMapTypeSatellite()");
                        } else if (hybrid.isSelected()) {
                            webEngine.executeScript("document.setMapTypeHybrid()");
                        } else if (terrain.isSelected()) {
                            webEngine.executeScript("document.setMapTypeTerrain()");
                        }
                    }
                });
        // add map source toggles
        ToggleGroup mapSourceGroup = new ToggleGroup();
        final ToggleButton google = new ToggleButton("Google");
        google.setSelected(true);
        google.setToggleGroup(mapSourceGroup);
        final ToggleButton yahoo = new ToggleButton("Yahoo");
        yahoo.setToggleGroup(mapSourceGroup);
        final ToggleButton bing = new ToggleButton("Bing");
        bing.setToggleGroup(mapSourceGroup);
        // listen to selected source
        mapSourceGroup.selectedToggleProperty().addListener(
                new ChangeListener<Toggle>() {
                    public void changed(
                            ObservableValue<? extends Toggle> observableValue,
                            Toggle toggle, Toggle toggle1) {
                        terrain.setDisable(true);
                        if (google.isSelected()) {
                            terrain.setDisable(false);
                            webEngine.load(
                                    getClass().getResource("googlemap.html").toString());
                        } else if (yahoo.isSelected()) {
                            webEngine.load(
                                    getClass().getResource("bingmap.html").toString());
                        } else if (bing.isSelected()) {
                            webEngine.load(
                                    getClass().getResource("yahoomap.html").toString());
                        }
                        mapTypeGroup.selectToggle(road);
                    }
                });

        Button zoomIn = new Button("Zoom In");
        zoomIn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                webEngine.executeScript("document.zoomIn()");
            }
        });
        Button zoomOut = new Button("Zoom Out");
        zoomOut.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                webEngine.executeScript("document.zoomOut()");
            }
        });
        // create toolbar
        ToolBar toolBar = new ToolBar();
        toolBar.getStyleClass().add("map-toolbar");
        toolBar.getItems().addAll(
                road, satellite, hybrid, terrain,
                createSpacer(),
                google, yahoo, bing,
                createSpacer(),
                new Label("Location:"), zoomIn, zoomOut);
        // create root
        BorderPane root = new BorderPane();
        root.getStyleClass().add("map");
        root.setCenter(webView);
        root.setTop(toolBar);
        // create scene
        stage.setTitle("Web Map");
        Scene scene = new Scene(root,1000,700, Color.web("#666970"));
        stage.setScene(scene);
        scene.getStylesheets().add("/webmap/WebMap.css");
        // show stage
        stage.show();
    }

    private Node createSpacer() {
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
    }

    static { // use system proxy settings when standalone application
        System.setProperty("java.net.useSystemProxies", "true");
    }

    public static void main(String[] args){
        Application.launch(args);
    }

}
