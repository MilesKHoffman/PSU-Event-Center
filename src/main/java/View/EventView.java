package View;

import Controller.Functions;
import Model.Event;
import Model.Map;
import Controller.EventLogic;
import View.Components.MapComponent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;

import java.util.ArrayList;

public class EventView extends ViewClass {

    private Button eventClick;
    private EventLogic logic = new EventLogic( this ); // Connects to the logic -MH
    public EventView(Boolean allEvents) {

        super();

        if (allEvents) {
            drawEvents(logic.getAllEvents(), "ALL EVENTS" );
        }
        else {
            drawEvents( logic.getAllEvents(), "MY EVENTS");
        }

        drawSideMap();
        setScene("NewEventStyle.css");
    }

    /*
    public void drawSideMap() {
        VBox mapVBox = logic.getMapVBox();
        Map.setMapCenter(42.119212, -79.982995);

        // set map size and layout
        mapVBox.setPrefSize(sceneMidWi,sceneHeight - headerHeight);
        mapVBox.setLayoutX(sceneMidWi);
        mapVBox.setLayoutY(headerHeight);

        root.getChildren().add(mapVBox);
    }

     */

    private void drawSideMap(){
        MapComponent map = new MapComponent();

        BorderPane mapPane = map.getRoot();

        mapPane.setPrefHeight( sceneHeight - headerHeight);
        mapPane.setPrefWidth(sceneMidWi);
        mapPane.setLayoutY(headerHeight);
        mapPane.setLayoutX(sceneMidWi);

        root.getChildren().add(mapPane);
    }

    public void drawEvents(ArrayList<Event> events, String label) {
        Functions function = new Functions();

        //upcoming events
        Label eventLabel = new Label(label);
        TilePane eventTiles = new TilePane();
        for( Event e : events ){
            eventClick = new Button(e.getName());
            logic.setEventClickHandler(e);
            eventTiles.getChildren().add( eventClick );
        }
        function.setCollectionInputStyle(eventTiles, new Button(), new String[]{"center"});
        ScrollPane eventScroll = new ScrollPane( eventTiles );

        VBox eventVb = new VBox( eventLabel, eventScroll);
        eventVb.setPrefWidth(sceneMidWi);
        eventVb.getStyleClass().add("eventVert");

        function.setCollectionInputStyle(eventVb, new Label(), new String[]{"fontHeader1"});

        root.getChildren().add(eventVb);
    }

    public Button getEventClick() {
        return eventClick;
    }
}
