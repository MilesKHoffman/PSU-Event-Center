package NewController;

import NewView.NewCreateUserView;
import NewView.NewLoginView;
import Repository.DatabaseHandler;
import View.CreateUserView;
import View.LoginView;

public class NewCreateUserLogic {

    private NewCreateUserView view;

    public NewCreateUserLogic( NewCreateUserView view ){
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
                if (DatabaseHandler.createUser(username, password, username.equals("admin"))) {
                    System.out.println("User created: " + username + " " + password);
                }
                else {
                    System.out.println("User creation failed");
                }
            }

            new NewViewController( new NewLoginView() ).showView();
        });
    }

    //return button
    public void setReturnHandler() {
        view.getReturnButton().setOnAction( actionEvent -> {
            new NewViewController( new NewLoginView() ).showView();
        });
    }
}
