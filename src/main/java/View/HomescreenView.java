package View;

import Controller.Functions;
import Controller.HomescreenLogic;
import Controller.ViewController;
import Model.Event;
import View.Components.EventCard;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class HomescreenView extends ViewClass {

     /* Here are the parent variables/methods:

    protected Scene scene;
    protected Group root;

    public Scene getScene();
    public Group getRoot();
    protected setScene( String );

     */

    private HomescreenLogic logic = new HomescreenLogic( this ); // Connects to the logic -MH
    public HomescreenView() {

        super();

        setVars();
        setScene("HomeStyle.css");
    }

    private void setVars(){

        Functions function = new Functions();

        Label upcomingLabel = new Label("UPCOMING EVENTS");

        ArrayList<Event> allEvents = logic.getAllEvents();
        ArrayList<Event> userEvents = logic.getUserEvents();

        TilePane upcomingTilesP = new TilePane();
        for( Event e : allEvents ){

            EventCard card = new EventCard( e.getName(), e.getDesc(), e.getLocation(),
                    e.getClub(), false, e.getDateTime() );

            logic.addCardEvent(card);
            upcomingTilesP.getChildren().add(card);
        }
        function.setCollectionInputStyle(upcomingTilesP, new Button(), new String[]{"center"});
        ScrollPane upcomingScroll = new ScrollPane( upcomingTilesP );

        VBox upcomingVbox = new VBox( upcomingLabel, upcomingScroll);
        upcomingVbox.setPrefWidth(400);
        upcomingVbox.getStyleClass().add("splitVBox");

        Label myEventsLabel = new Label("MY EVENTS");

        TilePane myEventsTileP = new TilePane();
        for( Event e : userEvents ){

            EventCard card = new EventCard( e.getName(), e.getDesc(), e.getLocation(),
                    e.getClub(), true, e.getDateTime() );

            logic.addCardEvent(card);
            myEventsTileP.getChildren().add(card);
        }
        ScrollPane myEventScroll = new ScrollPane( myEventsTileP );


        VBox myEventsVbox = new VBox(myEventsLabel, myEventScroll);
        myEventsVbox.setPrefWidth(400);
        myEventsVbox.getStyleClass().add("splitVBox");

        HBox split = new HBox(upcomingVbox, myEventsVbox);
        split.getStyleClass().add("container");
        split.setMaxWidth(sceneWidth);
        split.setMaxHeight(sceneHeight - headerHeight);
        split.setLayoutY(headerHeight);
        function.setObjectCenterX(sceneMidWi, split);

        function.setCollectionInputStyle(split, new Label(), new String[]{"fontHeader1"});

        root.getChildren().add(split);
    }
}
