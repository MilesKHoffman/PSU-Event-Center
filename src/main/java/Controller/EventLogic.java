package Controller;

import Model.Event;
import Model.Map;
import Model.User;
import View.Components.EventCard;
import View.Components.MapComponent;
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

    public void setEventClickHandler(EventCard e) {
        e.setOnMouseClicked(event -> {
            view.drawEventPop(e.getName(), e.getDescription(), e.getLocation(), e.getClub(), e.getDateTime());
        });
    }

    public void setMapMarkers(boolean allEvents, MapComponent map){

        if( allEvents){
            map.addAllMarkers( DatabaseHandler.getAllEvents() );
        }
        else{
            map.addAllMarkers( DatabaseHandler.getUserEvents( User.getInstance().getUserID() ) );
        }

    }
}
