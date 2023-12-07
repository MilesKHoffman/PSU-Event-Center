package Controller;

import View.EventView;

import Model.User;
import Repository.DatabaseHandler;

public class EventViewLogic {

    private EventView view;

    public EventViewLogic(EventView view) {
        this.view = view;
    }

    public void setFollowEventHandler() {
        view.getFollowEvent().setOnAction( actionEvent -> {
            DatabaseHandler.saveEvent(User.getInstance().getUserID(), view.getEvent().getId());
        });
    }
}
