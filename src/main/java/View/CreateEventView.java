package View;

import Model.Map;
import Controller.CreateEventLogic;
import View.Components.MapComponent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class CreateEventView extends ViewClass {

    private CreateEventLogic logic = new CreateEventLogic(this);

    private TextField eventName, eventDateTime;
    private TextArea eventDesc;
    private Button submitButton;

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

        eventName = new TextField();
        eventName.setPromptText("Event Name");
        eventName.getStyleClass().add("eventName");
        Label eventLabel = new Label("Event Label:");

        eventDesc = new TextArea();
        eventDesc.setPromptText("Event Description");
        eventDesc.getStyleClass().add("eventDesc");
        Label descLabel = new Label("Event Description:");

        eventDateTime = new TextField();
        eventDateTime.setPromptText("Event Date and Time");
        eventDateTime.getStyleClass().add("eventTime");
        Label timeLabel = new Label("Event Date and Time:");

        submitButton = new Button("Create Event");

        Pane leftPane = new Pane(eventName, eventLabel, eventDesc, descLabel, eventDateTime, timeLabel);
        leftPane.getStyleClass().add("leftPane");

        VBox vbox = new VBox(leftPane);
        vbox.setPrefWidth(sceneMidWi);
        vbox.setLayoutY(sceneMidHi);

        root.getChildren().add(vbox);
    }
}
