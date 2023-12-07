package NewController;

import Model.Event;
import Model.Map;
import NewView.NewEventView;
import NewView.NewHomescreenView;
import Repository.DatabaseHandler;
import View.EventView;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class NewEventLogic {

    private NewEventView view;

    public NewEventLogic(NewEventView view ){ this.view = view; }

    public ArrayList<Event> getAllEvents(){
        return DatabaseHandler.getAllEvents();
    }

    public VBox getMapVBox() {
        return Map.getInstance().getMapVBox();
    }

    public void setEventClickHandler(Event e) {
        /*
        bring up the event's larger display on the same screen.
         */
    }
}
