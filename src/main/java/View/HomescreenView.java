package View;

import Controller.Functions;
import Controller.HomescreenLogic;
import Controller.LogicInter;
import Controller.ViewController;
import Model.Event;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class HomescreenView extends ViewClass{

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

        drawAllEvents(logic.getEventList());
        setScene("HomeStyle.css");
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
        HBox split = new HBox(upcomingVbox, myEventsVbox);
        split.getStyleClass().add("container");
        split.setMaxWidth(sceneWidth);
        split.setMaxHeight(sceneHeight - headerHeight);
        split.setLayoutY(headerHeight);

        function.setCollectionInputStyle(split, new Label(), new String[]{"fontHeader1"});

        root.getChildren().add(split);
    }
}
