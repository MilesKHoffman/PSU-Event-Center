package Controller;

import Model.Event;
import Model.User;
import View.EventView;
import View.HomescreenView;

import java.util.ArrayList;

public class EventViewLogic {

    EventView eventView;

    public EventViewLogic( EventView view ){
        this.eventView = view;
    }

    public ArrayList<Event> getEventList(){
        return User.getUser().getEventList();
    }
}
