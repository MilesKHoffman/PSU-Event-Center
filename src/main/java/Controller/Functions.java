package Controller;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.Region;

/*
This class is meant to simplify the process of redundant code throughout the project. -MH
 */
public class Functions {

    // Sets the position by the center of the obj -MH
    public void setObjectCenter(double x, double y, Node obj ){

        Platform.runLater( () -> {
            obj.setLayoutX( x - (obj.getBoundsInLocal().getWidth() / 2) );
            obj.setLayoutY( y - obj.getBoundsInLocal().getWidth() / 2 );
        });
    }

    public void setObjectCenterX( double x, Node obj ){

        Platform.runLater( () -> {
            obj.setLayoutX( x - (obj.getBoundsInLocal().getWidth() / 2) );
        });
    }

    // Sets all nodes of selected type in a group to the style inputted. -MH
    public void setCollectionInputStyle(Region group, Node input, String[] style ){

        for( String css : style ){

            group.getChildrenUnmodifiable().forEach( node -> {

                if( node.getClass().isInstance(input) ){
                    node.getStyleClass().addAll(css);
                }

                if (node instanceof Region) {
                    setCollectionInputStyle((Region) node, input, new String[]{css} );
                }
            });
        }
    }


    /*
    switch( input ) {

            case "Label":
                group.getChildrenUnmodifiable().forEach( Label -> {

                    Label.getStyleClass().addAll(style);
                });

            case "Button":
                group.getChildrenUnmodifiable().forEach( Button -> {

                    Button.getStyleClass().addAll(style);
                });

            case "TextField":
                group.getChildrenUnmodifiable().forEach( TextField -> {

                    TextField.getStyleClass().addAll(style);
                });

            default:
                System.out.println("ERROR...setCollectionInputStyle argument 'input' not recognized...");
                System.exit(-1);
        }
     */
}
