package View;

import Controller.LogicInter;
import javafx.scene.Group;
import javafx.scene.Scene;

public class ViewClass {

    protected Scene scene;
    protected Group root;

    public Scene getScene() {
        return scene;
    }
    public Group getRoot(){ return root; }

    protected void setScene(){
        scene = new Scene(root, 800, 600);
       // scene.getStylesheets().add( getClass().getResource("SceneView.css").toExternalForm() );
    }
}
