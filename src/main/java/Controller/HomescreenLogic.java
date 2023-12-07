package Controller;

import Model.Event;
import Model.Map;
import Model.User;
import View.EventView;
import View.HomescreenView;
import Repository.DatabaseHandler;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class HomescreenLogic {

    HomescreenView homeView;

    public HomescreenLogic( HomescreenView view ){
        this.homeView = view;
    }


    public void setEventClickHandler(Event e) {
        homeView.getEventClick().setOnAction( actionEvent -> {
            new ViewController( new EventView(e) ).showView();
        });
    }

    public ArrayList<Event> getAllEvents(){
        return DatabaseHandler.getAllEvents();
    }

    public ArrayList<Event> getMyEvents() {
        return DatabaseHandler.getUserEvents(User.getInstance().getUserID());
    }

    public VBox getMapVBox() {
        return Map.getInstance().getMapVBox();
    }
}
