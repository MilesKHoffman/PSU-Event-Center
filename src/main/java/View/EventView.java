package View;

import Controller.EventCardLogic;
import Controller.Functions;
import Model.Event;
import Model.Map;
import Controller.EventLogic;
import View.Components.EventCard;
import View.Components.MapComponent;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import javax.xml.transform.Templates;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class EventView extends ViewClass {

    private EventCard eventClick;
    private Pane eventPop = new Pane();
    private boolean allEvents;
    private ScrollPane scroll;
    private EventLogic logic = new EventLogic( this ); // Connects to the logic -MH
    public EventView(Boolean allEvents) {

        super();

        this.allEvents = allEvents;

        if (allEvents) {
            drawEvents(logic.getAllEvents(), "ALL EVENTS" );
        }
        else {
            drawEvents( logic.getUserEvents(), "MY EVENTS");
        }


        drawSideMap();

        setScene("EventStyle.css");
    }


    private void drawSideMap(){
        MapComponent map = new MapComponent();
        logic.setMapMarkers(allEvents, map);

        BorderPane mapPane = map.getRoot();

        mapPane.setPrefHeight(400);
        mapPane.setPrefWidth(sceneMidWi + 100);
        mapPane.setLayoutY(headerHeight);
        mapPane.setLayoutX(sceneMidWi - 100);

        root.getChildren().add(mapPane);
    }

    public void drawEventPop( String name, String desc, String location, String club, LocalDateTime time){

        root.getChildren().remove(scroll);

        Label nameLabel = new Label(name);
        nameLabel.getStyleClass().add("namePop");

        Label locationLabel = new Label(location);
        locationLabel.getStyleClass().add("locationPop");

        Label clubLabel = new Label(club);
        clubLabel.getStyleClass().add("clubPop");

        Label timeLabel = new Label( time
                .format(DateTimeFormatter.ofPattern("EEEE, MMMM d 'at' h:mma")));
        timeLabel.getStyleClass().add("timePop");

        Label descLabel = new Label(desc);
        descLabel.setWrapText(true);
        descLabel.setMaxWidth(500);
        descLabel.getStyleClass().add("descPop");

        Pane pane = new Pane(nameLabel, locationLabel, clubLabel, timeLabel);
        pane.getStyleClass().add("popPane");

        VBox container = new VBox(pane, descLabel);
        container.getStyleClass().add("containerEvent");

        Platform.runLater(() -> {
            container.setPrefHeight( container.getHeight() + 250);
        });

        scroll = new ScrollPane(container);
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
            eventClick = new EventCard(e.getId(), e.getName(), e.getDesc(), e.getLocation(),
                    e.getClub(), true, e.getDateTime() );
            logic.setEventClickHandler(eventClick);
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
