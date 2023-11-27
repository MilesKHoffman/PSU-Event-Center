package Controller;

import Model.Event;
import Model.User;
import View.HomescreenView;

import java.util.ArrayList;

public class HomescreenLogic {

    HomescreenView homeView;

    public HomescreenLogic( HomescreenView view ){
        this.homeView = view;
    }

     public ArrayList<Event> getEventList(){
        return User.getUser().getEventList();
     }
}
