package Controller;

import Model.Event;
import Model.User;
import Repository.DatabaseHandler;
import View.Components.EventCard;
import View.EventView;
import View.HomescreenView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class HomescreenLogic {

    HomescreenView homeView;

    public HomescreenLogic(HomescreenView view ){
        this.homeView = view;
    }

    public void addCardEvent( EventCard card ){
        // Set up the click event
        card.setOnMouseClicked(event -> {
            // Handle the click event here, e.g., trigger some action
            System.out.println("Event card clicked: " + card.getName());

            new ViewController( new EventView(!card.getIsFollowed()) ).showView();
        });
    }

    public ArrayList<Event> getAllEvents(){
        return DatabaseHandler.getAllEvents();
    }

    public ArrayList<Event> getUserEvents() {
        return DatabaseHandler.getUserEvents(User.getInstance().getUserID());
    }
}
