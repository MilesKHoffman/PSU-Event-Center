package NewView;

import Model.Map;
import NewController.NewCreateEventLogic;
import NewController.NewMapLogic;
import View.ViewClass;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class NewCreateEventView extends NewViewClass {

    private NewCreateEventLogic logic = new NewCreateEventLogic(this);

    private TextField eventName, eventDateTime;
    private TextArea eventDesc;
    private Button submitButton;

    public NewCreateEventView() {
        super();

        drawScreen();
        drawMap();
        setScene("CreateEventStyle.css");
    }

    public void drawMap() {
        VBox mapVBox = logic.getMapVBox();
        //Map.mapClick();
        Map.mapClick(coords -> {
            // Handle the result here
            System.out.println("Latitude: " + coords[0] + ", Longitude: " + coords[1]);
            // Update your UI or perform other actions
        });

        Map.setMapCenter(42.119212, -79.982995);

        // set map size and layout
        mapVBox.setPrefSize(sceneMidWi,sceneHeight - headerHeight);
        mapVBox.setLayoutX(sceneMidWi);
        mapVBox.setLayoutY(headerHeight);

        root.getChildren().add(mapVBox);
    }
    public void drawScreen() {

        eventName = new TextField();
        eventName.setPromptText("Event Name");

        eventDesc = new TextArea();
        eventDesc.setPromptText("Event Description");

        eventDateTime = new TextField();
        eventDateTime.setPromptText("Event Date and Time");

        submitButton = new Button("Create Event");

        VBox vbox = new VBox(eventName, eventDesc, eventDateTime);
        vbox.setPrefWidth(sceneMidWi);
        vbox.setLayoutY(sceneMidHi);

        root.getChildren().add(vbox);

    }
}
