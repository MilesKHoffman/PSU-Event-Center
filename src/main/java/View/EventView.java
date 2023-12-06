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

    public EventView(String s) {
        super();

        if (s.equals("allEvents")) {
            drawAllEvents(logic.getEventList());
        }
        else if (s.equals("myEvents")) {
            drawMyEvents(logic.getEventList());
        }
        drawSideMap();
        setScene("EventStyle.css");
    }

    public void drawAllEvents(ArrayList<Event> events) {
        Functions function = new Functions();

        //upcoming events
        Label upcomingLabel = new Label("UPCOMING EVENTS");
        TilePane upcomingTilesP = new TilePane();
        for( Event e : events ){
            upcomingTilesP.getChildren().add( new Button(e.getName()));
        }
        function.setCollectionInputStyle(upcomingTilesP, new Button(), new String[]{"center"});
        ScrollPane upcomingScroll = new ScrollPane( upcomingTilesP );

        VBox upcomingVbox = new VBox( upcomingLabel, upcomingScroll);
        upcomingVbox.setPrefWidth(sceneMidWi);
        upcomingVbox.getStyleClass().add("splitVBox");

        //hbox for separating events
        HBox split = new HBox(upcomingVbox);
        split.getStyleClass().add("container");
        split.setMaxWidth(sceneWidth);
        split.setMaxHeight(sceneHeight - headerHeight);
        split.setLayoutY(headerHeight);

        function.setCollectionInputStyle(split, new Label(), new String[]{"fontHeader1"});

        root.getChildren().add(split);
    }

    public void drawMyEvents(ArrayList<Event> events) {
        Functions function = new Functions();

        //my events
        Label myEventsLabel = new Label("MY EVENTS");
        TilePane myEventsTileP = new TilePane();
        for( Event e : events){
            myEventsTileP.getChildren().add( new Button(e.getName() + "\n" + e.getDesc()));
        }
        ScrollPane myEventScroll = new ScrollPane( myEventsTileP );

        VBox myEventsVbox = new VBox(myEventsLabel, myEventScroll);
        myEventsVbox.setPrefWidth(sceneMidWi);
        myEventsVbox.getStyleClass().add("splitVBox");

        //hbox for separating events
        HBox split = new HBox(myEventsVbox);
        split.getStyleClass().add("container");
        split.setMaxWidth(sceneWidth);
        split.setMaxHeight(sceneHeight - headerHeight);
        split.setLayoutY(headerHeight);

        function.setCollectionInputStyle(split, new Label(), new String[]{"fontHeader1"});

        root.getChildren().add(split);
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

        root.getChildren().add(mapVBox);
    }
}
