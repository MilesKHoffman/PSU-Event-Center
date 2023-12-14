package Controller;

import Model.Event;
import Model.Map;
import Model.User;
import View.EventView;
import Repository.DatabaseHandler;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class EventLogic {

    private EventView view;

    public EventLogic(EventView view ){ this.view = view; }


    public ArrayList<Event> getAllEvents(){
        return DatabaseHandler.getAllEvents();
    }

    public ArrayList<Event> getUserEvents() {
        return DatabaseHandler.getUserEvents(User.getInstance().getUserID());
    }

    public VBox getMapVBox() {
        return Map.getInstance().getMapVBox();
    }

    public void setEventClickHandler(Event e) {
        /*
        bring up a larger tile of the events description below the map.
         */
    }
}
