package Controller;

import Model.Map;
import View.CreateEventView;
import javafx.scene.layout.VBox;

public class CreateEventLogic {

    private CreateEventView view;

    public CreateEventLogic(CreateEventView view) {
        this.view = view;
    }

    public VBox getMapVBox() {
        return Map.getInstance().getMapVBox();
    }

}
