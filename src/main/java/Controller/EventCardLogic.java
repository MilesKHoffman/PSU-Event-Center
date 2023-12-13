package Controller;

import Model.User;
import View.Components.EventCard;

public class EventCardLogic {

    EventCard card;

    public EventCardLogic( EventCard card ){
        this.card = card;
    }

    public boolean checkAdmin(){

        return User.getInstance().isAdmin();
    }
}
