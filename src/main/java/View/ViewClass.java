package View;

import Controller.LogicInter;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

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
        URL css = getClass().getResource("/StyleSheets/" + styleSheet);
        scene.getStylesheets().add( css.toExternalForm() );
    }
}
