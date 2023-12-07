package NewController;

import Model.Event;
import Model.Map;
import Model.User;
import NewView.NewHomescreenView;
import Repository.DatabaseHandler;
import View.EventView;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class NewHomescreenLogic {

    NewHomescreenView homeView;

    public NewHomescreenLogic(NewHomescreenView view ){
        this.homeView = view;
    }


    public void setEventClickHandler(Event e) {
        homeView.getEventClick().setOnAction( actionEvent -> {
            new NewViewController( new EventView(e) ).showView();
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
