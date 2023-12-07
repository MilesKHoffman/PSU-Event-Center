package View;

import Controller.Functions;
import Controller.HomescreenLogic;
import Controller.LogicInter;
import Controller.ViewController;
import Model.Event;
import Model.Map;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.File;
import java.util.ArrayList;

public class HomescreenView extends ViewClass{

    private Button eventClick;
    private HomescreenLogic logic = new HomescreenLogic( this ); // Connects to the logic -MH
    public HomescreenView(String s) {

        super();

        if (s.equals("allEvents")) {
            drawAllEvents(logic.getAllEvents());
        }
        else if (s.equals("myEvents")) {
            drawMyEvents(logic.getMyEvents());
        }

        drawSideMap();
        setScene("HomeStyle.css");
    }
    public void drawSideMap() {
        VBox mapVBox = logic.getMapVBox();
        Map.setMapCenter(42.119212, -79.982995);

        // set map size and layout
        mapVBox.setPrefSize(sceneMidWi,sceneHeight - headerHeight);
        mapVBox.setLayoutX(sceneMidWi);
        mapVBox.setLayoutY(headerHeight);

        root.getChildren().add(mapVBox);
    }
    public void drawAllEvents(ArrayList<Event> events) {
        Functions function = new Functions();

        //upcoming events
        Label upcomingLabel = new Label("UPCOMING EVENTS");
        TilePane upcomingTilesP = new TilePane();
        for( Event e : events ){
            eventClick = new Button(e.getName());
            logic.setEventClickHandler(e);
            upcomingTilesP.getChildren().add( eventClick );
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
    //cheese stinks
    public void drawMyEvents(ArrayList<Event> events) {
        Functions function = new Functions();

        //my events
        Label myEventsLabel = new Label("MY EVENTS");
        TilePane myEventsTileP = new TilePane();
        for( Event e : events){
            eventClick = new Button(e.getName());
            logic.setEventClickHandler(e);
            myEventsTileP.getChildren().add( eventClick );
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

    public Button getEventClick() {
        return eventClick;
    }
}
