package View;

import Controller.Functions;
import Controller.LogicInter;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class ViewClass {

    protected Scene scene;
    protected Pane root;
    protected final double sceneWidth = 800;
    protected final double sceneHeight = 600;
    protected final double sceneMidWi = sceneWidth / 2.0;
    protected final double sceneMidHi = sceneHeight / 2.0;


    public ViewClass(){
        root = new Pane();
    }

    public Scene getScene() {
        return scene;
    }
    public Pane getRoot(){ return root; }

    // Sets the scene with the root and size. Adds css file to scene. -MH
    protected void setScene( String styleSheet ){

        scene = new Scene(root, sceneWidth, sceneHeight);

        // Adds a css file to the scene.
        URL css = getClass().getResource("/StyleSheets/" + styleSheet );
        scene.getStylesheets().add( css.toExternalForm() );

        root.getStyleClass().addAll("root", "var");
    }

    protected void setHeader(){

        /*
        Sets the header with MyEvents, AllEvents, Home, Announcements.
         */

        Button myEventButton = new Button("My Events");
        Button allEventButton = new Button("All Events");
        Button homeButton = new Button("Home");
        Button announceButton = new Button("Announcements");

        HBox hbox = new HBox(myEventButton, allEventButton, homeButton, announceButton);
        hbox.setPrefWidth(sceneWidth);
        hbox.getStyleClass().add("headerBackground");
        hbox.setAlignment(Pos.TOP_CENTER);

        Functions function = new Functions();
        function.setCollectionButtonStyle(hbox, "buttonLightStandard");


        VBox vbox = new VBox(hbox);


        root.getChildren().add(vbox);
    }
}
