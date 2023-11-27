package View;

import Controller.CreateUserLogic;
import Controller.Functions;
import Controller.ViewController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/* Here are the parent variables/methods:

    protected Scene scene;
    protected Group root;

    public Scene getScene();
    public Group getRoot();
    protected setScene( String );

     */

public class CreateUserView extends ViewClass{

    private TextField usernameField, passwordField;
    private Button createButton, returnButton;
    private CreateUserLogic logic = new CreateUserLogic( this );

    public CreateUserView() {

        super();

        removeHeader();
        setVars();
        setHandlers();
        setScene("LoginStyle.css");
    }

    private void setVars(){

        Functions function = new Functions();

        usernameField = new TextField();
        usernameField.setPromptText("Username");

        passwordField = new TextField();
        passwordField.setPromptText("Password");

        createButton = new Button("CREATE USER");
        createButton.getStyleClass().addAll("fontButton", "buttonStandard");


        returnButton = new Button("GO BACK");
        returnButton.getStyleClass().addAll("fontButton", "buttonStandard");

        HBox hBox = new HBox(createButton, returnButton);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(usernameField,passwordField, hBox);
        vBox.getStyleClass().addAll("vbox");

        function.setObjectCenterX( sceneMidWi, vBox);

        root.getChildren().add(vBox);
    }

    private void setHandlers(){

        createButton.setOnAction( actionEvent -> {
            new ViewController( new LoginView() ).showView();
        });

        returnButton.setOnAction( actionEvent -> {
            new ViewController( new LoginView() ).showView();
        });
    }
}
