package View;

import Controller.EventViewLogic;
import Controller.Functions;
import Model.Event;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.File;
import java.util.ArrayList;

public class EventView extends ViewClass {

    private EventViewLogic logic = new EventViewLogic( this ); // Connects to the logic -MH
    private HBox split;

    public EventView(Boolean allEvents) {
        super();

        if (allEvents) {
            drawAllEvents(logic.getEventList(), "ALL EVENTS");
        }
        else {
            drawAllEvents(logic.getEventList(), "MY EVENTS");
        }
        drawSideMap();
        root.getChildren().add(split);
        setScene("EventStyle.css");
    }

    public void drawAllEvents(ArrayList<Event> events, String label) {
        Functions function = new Functions();

        //upcoming events
        Label eventLabel = new Label(label);
        TilePane eventTiles = new TilePane();
        for( Event e : events ){
            eventTiles.getChildren().add( new Button(e.getName()));
        }
        function.setCollectionInputStyle(eventTiles, new Button(), new String[]{"center"});
        ScrollPane eventScroll = new ScrollPane( eventTiles );

        VBox eventVert = new VBox( eventLabel, eventScroll);
        eventVert.setPrefWidth(sceneMidWi);
        eventVert.getStyleClass().add("eventVert");

        //hbox for separating events
        split = new HBox(eventVert);
        split.getStyleClass().add("container");
        split.setMaxWidth(sceneWidth);
        split.setMaxHeight(sceneHeight - headerHeight);
        split.setLayoutY(headerHeight);

        function.setCollectionInputStyle(split, new Label(), new String[]{"fontHeader1"});
    }

    public void drawSideMap() {
        String dir = System.getProperty("user.dir");
        File url = new File( dir + "/src/main/java/zTesting/demo.html" );

        // Create a WebView
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        // Load HTML file into WebEngine (maps: https://www.google.com/maps)
        webEngine.load(url.toURI().toString());

        // Create a scene and add the WebView to it
        webView.setPrefSize(sceneMidWi,sceneHeight - headerHeight);

        VBox mapVBox = new VBox();
        mapVBox.getChildren().add(webView);
        mapVBox.setLayoutX(sceneMidWi);
        mapVBox.setLayoutY(headerHeight);

        split.getChildren().add(mapVBox);
    }
}
