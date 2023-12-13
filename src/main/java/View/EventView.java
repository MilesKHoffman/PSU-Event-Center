package View;

import Controller.EventCardLogic;
import Controller.Functions;
import Model.Event;
import Model.Map;
import Controller.EventLogic;
import View.Components.EventCard;
import View.Components.MapComponent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class EventView extends ViewClass {

    private EventCard eventClick;
    private Pane eventPop = new Pane();
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

        drawEventPop("TestPop", "THIs IS a test desc", "Erie", "Hardcoded in CLub", LocalDateTime.now());

        setScene("EventStyle.css");
    }


    private void drawSideMap(){
        MapComponent map = new MapComponent();

        BorderPane mapPane = map.getRoot();

        mapPane.setPrefHeight(400);
        mapPane.setPrefWidth(sceneMidWi + 100);
        mapPane.setLayoutY(headerHeight);
        mapPane.setLayoutX(sceneMidWi - 100);

        root.getChildren().add(mapPane);
    }

    public void drawEventPop( String name, String desc, String location, String club, LocalDateTime time){

        Label nameLabel = new Label(name);
        nameLabel.getStyleClass().add("namePop");

        Label locationLabel = new Label(location);
        locationLabel.getStyleClass().add("locationText");

        Label clubLabel = new Label(club);
        clubLabel.getStyleClass().add("clubText");

        Label timeLabel = new Label( time
                .format(DateTimeFormatter.ofPattern("EEEE, MMMM d 'at' h:mma")));
        timeLabel.getStyleClass().add("timeText");

        Label descLabel = new Label(desc);
        descLabel.getStyleClass().add("descPop");

        Pane pane = new Pane(nameLabel, locationLabel, clubLabel, timeLabel, descLabel);

        TilePane container = new TilePane();
        container.getStyleClass().add("containerEvent");

        ScrollPane scroll = new ScrollPane(container);
        scroll.getStyleClass().add("popScroll");
        scroll.setLayoutX(sceneMidWi - 100);
        scroll.setLayoutY(headerHeight + 400);

        root.getChildren().add(scroll);
    }

    public void drawEvents(ArrayList<Event> events, String label) {
        Functions function = new Functions();

        //upcoming events
        Label eventLabel = new Label(label);
        TilePane eventTiles = new TilePane();
        for( Event e : events ){
            eventClick = new EventCard(e.getName(), e.getDesc(), "add location func",
                    "need club func", true, LocalDateTime.now() );
            logic.setEventClickHandler(e);
            eventTiles.getChildren().add( eventClick );
        }
        function.setCollectionInputStyle(eventTiles, new Button(), new String[]{"center"});
        ScrollPane eventScroll = new ScrollPane( eventTiles );

        VBox eventVb = new VBox( eventLabel, eventScroll);
        eventVb.setPrefWidth(sceneMidWi - 100);
        eventVb.getStyleClass().add("eventVert");

        function.setCollectionInputStyle(eventVb, new Label(), new String[]{"fontHeader1"});

        root.getChildren().add(eventVb);
    }

    public EventCard getEventClick() {
        return eventClick;
    }
}
