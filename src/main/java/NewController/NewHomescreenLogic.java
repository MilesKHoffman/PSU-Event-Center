package NewController;

import Model.Event;
import Model.Map;
import Model.User;
import NewView.NewHomescreenView;
import Repository.DatabaseHandler;
import View.EventView;
import View.HomescreenView;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class NewHomescreenLogic {

    NewHomescreenView homeView;

    public NewHomescreenLogic( NewHomescreenView view ){
        this.homeView = view;
    }
}
