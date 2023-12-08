package NewView;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.File;

public class MilesMap {

    private WebView webView;
    private WebEngine webEngine;

    private BorderPane root = new BorderPane();

    public MilesMap() {

        initializeMap();
    }

    public void initializeMap(){
        String dir = System.getProperty("user.dir");
        String path = dir + "/src/main/java/GoogleMap.html";
        File url = new File( path );

        // Create a WebView
        webView = new WebView();
        webEngine = webView.getEngine();
        webEngine.setJavaScriptEnabled(true);

        Platform.runLater(()  -> {
            webEngine.load( url.toURI().toString() );

            //String mapURL = getClass().getResource("/GoogleMap.html").toExternalForm();
            //webEngine.load(mapURL);
        });


        webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == Worker.State.FAILED) {
                System.err.println("failed to load: " + webEngine.getLoadWorker().getMessage());
            }
        });

        createToolbar();

        // set root
        root.getStyleClass().add("GoogleMap");
        root.setCenter(webView);
    }

    private static Node createSpacer() {
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
    }

    // Creates toolbar and adds it to root.
    private void createToolbar() {
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
                            Platform.runLater( () -> {
                                webEngine.executeScript("document.setMapTypeRoad()");
                            });

                        } else if (satellite.isSelected()) {
                            Platform.runLater( () -> {
                                webEngine.executeScript("document.setMapTypeSatellite()");
                            });
                        } else if (hybrid.isSelected()) {
                            Platform.runLater( () -> {
                                webEngine.executeScript("document.setMapTypeHybrid()");
                            });
                        } else if (terrain.isSelected()) {
                            Platform.runLater( () -> {
                                webEngine.executeScript("document.setMapTypeTerrain()");
                            });
                        }
                    }
                });

        Button returnToBehrend = new Button("Reset");
        returnToBehrend.setOnAction( actionEvent -> {
            webEngine.reload();
        });

        // create toolbar
        ToolBar toolBar = new ToolBar();
        toolBar.getStyleClass().add("map-toolbar");
        toolBar.getItems().addAll(
                road, satellite, hybrid, terrain,
                createSpacer(), returnToBehrend
        );

        root.setTop(toolBar);
    }

    public BorderPane getRoot() {
        return root;
    }
}

