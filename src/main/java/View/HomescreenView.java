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
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.File;
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
    public HomescreenView(String s) {

        super();

        if (s.equals("allEvents")) {
            drawAllEvents(logic.getEventList());
        }
        else if (s.equals("myEvents")) {
            drawMyEvents(logic.getEventList());
        }
        drawSideMap();
        setScene("HomeStyle.css");
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
}
