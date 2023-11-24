package View;

import Controller.LogicInter;
import Controller.LoginLogic;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;

public class LoginView extends ViewClass{

    /* Here are the parent variables/methods:

    protected Scene scene;
    protected Group root;

    public Scene getScene();
    public Group getRoot();
    protected setScene();

     */

    private LoginLogic logic = new LoginLogic(this);

    public LoginView(){

        Circle circle = new Circle(100,100,100 );
        circle.getStyleClass().add("circle");

        root = new Group( circle );

        setScene();
    }
}
