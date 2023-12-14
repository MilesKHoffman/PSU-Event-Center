package Controller;

import Model.Location;
import Model.Map;
import Repository.DatabaseHandler;
import View.CreateEventView;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class CreateEventLogic {

    private CreateEventView view;

    public CreateEventLogic(CreateEventView view) {
        this.view = view;
    }

    public VBox getMapVBox() {
        return Map.getInstance().getMapVBox();
    }

    public ComboBox<String> createLocationCombo(){

       // ArrayList<Location> locations = DatabaseHandler.

        return null;
    }

}
