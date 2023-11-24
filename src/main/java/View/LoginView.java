package View;

import Controller.Functions;
import Controller.LoginLogic;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LoginView extends ViewClass{

    /* Here are the parent variables/methods:

    protected Scene scene;
    protected Group root;

    public Scene getScene();
    public Group getRoot();
    protected setScene();

     */


    private TextField usernameField, passwordField;
    private Button loginButton, createUserButton;

    private LoginLogic logic = new LoginLogic(this);

    public LoginView(){

        super();

        setVars();
        setScene("LoginStyle.css");
    }

    protected void setVars(){

        Functions function = new Functions();

        usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.getStyleClass().addAll("textField");

        passwordField = new TextField();
        passwordField.setPromptText("Password");
        passwordField.getStyleClass().addAll("textField");

        loginButton = new Button("Login");
        createUserButton = new Button("New User");

        HBox hBox = new HBox(loginButton, createUserButton);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(usernameField,passwordField, hBox);
        vBox.getStyleClass().addAll("vbox");

        function.alignObjectCenter( sceneMidWi, sceneMidHi, vBox);

        root.getChildren().add(vBox);
        root.getStyleClass().add("root");
    }
}
