package Controller;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

/*
This class is meant to simplify the process of redundant code throughout the project. -MH
 */
public class Functions {

    // Sets the position by the center of the obj -MH
    public void alignObjectCenter( double x, double y, Node obj ){

        Platform.runLater( () -> {
            obj.setLayoutX( x - (obj.getBoundsInLocal().getWidth() / 2) );
            obj.setLayoutY( y - obj.getBoundsInLocal().getWidth() / 2 );
        });
    }
}
