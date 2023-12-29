package Controller;

import Model.Location;
import Repository.DatabaseHandler;
import View.CreateEventView;
import javafx.scene.control.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CreateEventLogic {

    private CreateEventView view;

    List<Location> locations;

    public CreateEventLogic(CreateEventView view) {
        this.view = view;
    }

    public void setHandler(Button submitButton) {
        submitButton.setOnAction( actionEvent -> {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("hh:mm a");
            LocalTime localTime = LocalTime.parse(view.getTimeComboBox().getValue(), inputFormatter);

            LocalDateTime localDateTime = view.getEventDate().getValue().atTime(localTime);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = localDateTime.format(formatter);

            Location loc = locations.stream()
                    .filter( location -> view.getLocationCombo().getValue().equals(location.getName()))
                    .findFirst().orElse(null);

            DatabaseHandler.createEvent(view.getEventName().getText(), view.getEventDesc().getText()
                    , formattedDateTime, view.getLocationCombo().getValue()
                    , loc.getLatitude(), loc.getLongitude(), "Club");
        });
    }

    public ComboBox<String> createLocationCombo(){

       locations = DatabaseHandler.getLocations();

       ComboBox<String> locationCombo = new ComboBox<>();

       for( Location loc : locations ){
           locationCombo.getItems().add(loc.getName());
       }

        return locationCombo;
    }

}
