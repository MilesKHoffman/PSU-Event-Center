package Controller;

import Model.Location;
import Model.Map;
import Repository.DatabaseHandler;
import View.CreateEventView;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class CreateEventLogic {

    private CreateEventView view;

    public CreateEventLogic(CreateEventView view) {
        this.view = view;
    }

    public VBox getMapVBox() {
        return Map.getInstance().getMapVBox();
    }

    public ComboBox<String> createLocationCombo(){

       List<Location> locations = DatabaseHandler.getLocations();

       ComboBox<String> locationCombo = new ComboBox<>();

       for( Location loc : locations ){
           locationCombo.getItems().add(loc.getName());
       }

        return locationCombo;
    }

}
