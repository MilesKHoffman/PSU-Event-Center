package View;

import Controller.HomescreenLogic;
import Controller.LogicInter;
import javafx.scene.control.Button;

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

        setHeader();
        setVars();
        setScene("HomeStyle.css");
    }

    private void setVars(){


    }
}
