package View;

import Controller.EventViewLogic;
import Model.Event;
import Model.Map;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class EventView extends ViewClass {

    private Event event;
    private Button followEvent;
    private EventViewLogic logic = new EventViewLogic(this);


    public EventView(Event event) {
        super();

        this.event = event;
        drawEvent();
        drawMap();
        setScene("EventStyle.css");
    }
    public void drawEvent() {
        Label title = new Label(event.getName());
        Label datetime = new Label(event.getDateTime().toString());
        Label desc = new Label(event.getDesc());

        followEvent = new Button("Follow Event");
        logic.setFollowEventHandler();

        VBox vbox = new VBox(title, datetime, desc, followEvent);
        vbox.setLayoutY(sceneMidHi);

        root.getChildren().add(vbox);
    }

    public void drawMap() {
        VBox mapVBox = Map.getInstance().getMapVBox();
        Map.setMapCenter(event.getLatitude(), event.getLongitude());

        // set map size and layout
        mapVBox.setPrefSize(sceneMidWi,sceneHeight - headerHeight);
        mapVBox.setLayoutX(sceneMidWi);
        mapVBox.setLayoutY(headerHeight);

        root.getChildren().add(mapVBox);
    }

    public Event getEvent() {
        return event;
    }

    public Button getFollowEvent() {
        return followEvent;
    }
}
