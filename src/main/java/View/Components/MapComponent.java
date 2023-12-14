package View.Components;

import Model.Event;
import Repository.DatabaseHandler;
import javafx.animation.PauseTransition;
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
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MapComponent {

    private WebView webView;
    private WebEngine webEngine;

    private BorderPane root = new BorderPane();

    public MapComponent() {

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

        Button testButton = new Button("testButton");
        testButton.setOnAction( actionEvent -> {
            System.out.println("Test Button hit");

            addAllMarkers(DatabaseHandler.getAllEvents() );
        });

        // create toolbar
        ToolBar toolBar = new ToolBar();
        toolBar.getStyleClass().add("map-toolbar");
        toolBar.getItems().addAll(
                road, satellite, hybrid, terrain,
                createSpacer(), testButton, returnToBehrend
        );

        root.setTop(toolBar);
    }

    public BorderPane getRoot() {
        return root;
    }

    // Sets and zooms into a point on the map.
    public void setLatLng( double lat, double lng){

        String latStr = String.valueOf(lat);
        String lngStr = String.valueOf(lng);

        Platform.runLater( () -> {
            webEngine.executeScript("document.setLatLng(" + latStr + "," + lngStr + ")");
        });
    }

    public void addAllMarkers(ArrayList<Event> eventList ){

        for( Event event : eventList ){

            String latStr = String.valueOf(event.getLatitude());
            String lngStr = String.valueOf(event.getLongitude());
            String name = event.getName();

            System.out.println("lat long: " + latStr + " " + lngStr + " " + name);

            Platform.runLater(() -> {

                PauseTransition pause = new PauseTransition(Duration.seconds(1));
                pause.setOnFinished(e -> {
                    webEngine.executeScript("document.addMarker(" + latStr + "," + lngStr + ",'" + name + "')");
                });
                pause.play();
            });
        }
    }

    private void addMarkerIfDefined(String latStr, String lngStr, String name) {
        if ((boolean) webEngine.executeScript("typeof document.addMarker === 'function'")) {
            webEngine.executeScript("document.addMarker(" + latStr + "," + lngStr + ",'" + name + "')");
        } else {
            System.err.println("addMarker function is still not defined after the delay.");
        }
    }
}

