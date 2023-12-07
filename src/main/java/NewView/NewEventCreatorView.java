package NewView;

import NewController.NewEventEditorLogic;
import View.ViewClass;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class NewEventCreatorView extends ViewClass {

    NewEventEditorLogic logic = new NewEventEditorLogic( this );

    private TextField nameBox, descBox, locationBox, addressBox, clubBox;
    private ComboBox<String> categoryCombo;
    private DatePicker datePicker;


    public NewEventCreatorView() {
        super();

        setVars();
        setScene("EventEditorStyle.css");
    }

    private void setVars(){

        nameBox = new TextField("Hello");

        VBox vbox = new VBox(nameBox);

        root.getChildren().add(vbox);
    }
}
