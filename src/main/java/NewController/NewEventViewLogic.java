package NewController;

import Model.User;
import Repository.DatabaseHandler;
import View.EventView;

public class NewEventViewLogic {

    private EventView view;

    public NewEventViewLogic(EventView view) {
        this.view = view;
    }

    public void setFollowEventHandler() {
        view.getFollowEvent().setOnAction( actionEvent -> {
            DatabaseHandler.saveEvent(User.getInstance().getUserID(), view.getEvent().getId());
        });
    }
}
