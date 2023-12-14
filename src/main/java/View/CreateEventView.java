package View;

import Controller.Functions;
import Controller.CreateEventLogic;
import View.Components.MapComponent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class CreateEventView extends ViewClass {

    private CreateEventLogic logic = new CreateEventLogic(this);

    private TextField eventName;
    private DatePicker eventDate;
    private TextArea eventDesc;
    private Button submitButton;
    private ComboBox<String> locationCombo;

    public CreateEventView() {
        super();

        drawScreen();
        drawMap();
        setScene("CreateEventStyle.css");
    }

    public void drawMap() {

        MapComponent map = new MapComponent();

        BorderPane mapPane = map.getRoot();

        // set map size and layout
        mapPane.setPrefSize(sceneMidWi,sceneHeight - headerHeight);
        mapPane.setLayoutX(sceneMidWi);
        mapPane.setLayoutY(headerHeight);

        root.getChildren().add(mapPane);
    }
    public void drawScreen() {
        Functions functions = new Functions();

        eventName = new TextField();
        eventName.setPromptText("Event Name");
        eventName.getStyleClass().add("eventName");
        Label eventLabel = new Label("Event Name:");
        eventLabel.getStyleClass().addAll("textLabel", "eventLabel");

        eventDesc = new TextArea();
        eventDesc.setPromptText("Event Description");
        eventDesc.getStyleClass().add("eventDesc");
        Label descLabel = new Label("Event Description:");
        descLabel.getStyleClass().addAll("textLabel", "descLabel");

        eventDate = new DatePicker();
        eventDate.setPromptText("Event Date and Time");
        eventDate.getStyleClass().add("eventTime");
        ComboBox<String> timeComboBox = createTimeComboBox();
        timeComboBox.setPromptText("Select Time");
        timeComboBox.getStyleClass().add("eventTimeCombo");
        Label timeLabel = new Label("Event Date and Time:");
        timeLabel.getStyleClass().addAll("textLabel", "timeLabel");

        locationCombo = createLocationCombo();
        locationCombo.getStyleClass().add("locCombo");
        Label locLabel = new Label("Enter Location:");
        locLabel.getStyleClass().add("locLabel");

        submitButton = new Button("Create Event");
        submitButton.getStyleClass().addAll("submitEventButton", "buttonStandard");

        Pane leftPane = new Pane(eventName, eventLabel, eventDesc, descLabel,
                eventDate, timeComboBox, timeLabel, submitButton, locationCombo, locLabel);
        leftPane.getStyleClass().add("leftPane");

        root.getChildren().add(leftPane);

        // test
    }

    private ComboBox<String> createTimeComboBox() {
        List<String> hours = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            String hour = String.format("%02d:00", i % 12 == 0 ? 12 : i % 12);
            hours.add(i < 12 ? hour + " AM" : hour + " PM");
        }

        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll(hours);
        return comboBox;
    }

    private ComboBox<String> createLocationCombo(){
        return logic.createLocationCombo();
    }
}

