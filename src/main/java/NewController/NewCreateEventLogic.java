package NewController;

import Model.Map;
import NewView.NewCreateEventView;
import View.CreateEventView;
import javafx.scene.layout.VBox;

public class NewCreateEventLogic {

    private NewCreateEventView view;

    public NewCreateEventLogic( NewCreateEventView view) {
        this.view = view;
    }

    public VBox getMapVBox() {
        return Map.getInstance().getMapVBox();
    }

}
