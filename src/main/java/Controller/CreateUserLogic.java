package Controller;

import View.CreateUserView;
import View.LoginView;
import Repository.DatabaseHandler;

public class CreateUserLogic {

    private CreateUserView view;

    public CreateUserLogic(CreateUserView view ){
        this.view = view;
    }

    //create button
    public void setCreateHandler() {
        view.getCreateButton().setOnAction( actionEvent -> {
            String username = view.getUsernameField().getText();
            String password = view.getPasswordField().getText();
            if (!DatabaseHandler.userExists(username, password)) {
                System.out.println("Inside of .userExists\n");
                //will create admin account if username == admin
                if (DatabaseHandler.createUser(username, password, true)) {
                    System.out.println("User created: " + username + " " + password);
                }
                else {
                    System.out.println("User creation failed");
                }
            }

            new ViewController( new LoginView() ).showView();
        });
    }

    //return button
    public void setReturnHandler() {
        view.getReturnButton().setOnAction( actionEvent -> {
            new ViewController( new LoginView() ).showView();
        });
    }
}
