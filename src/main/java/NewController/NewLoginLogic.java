package NewController;


import NewView.NewLoginView;
import Repository.DatabaseHandler;
import View.CreateUserView;
import View.HomescreenView;
import View.LoginView;

public class NewLoginLogic extends NewLogicInter {

    private NewLoginView view;

    public NewLoginLogic( NewLoginView view ){
        this.view = view;
    }

    //login button
    public void setLoginHandler() {
        view.getLoginButton().setOnAction( actionEvent -> {
            String username = view.getUsernameField().getText();
            String password = view.getPasswordField().getText();

            //check database for login info
            if (DatabaseHandler.login(username, password) == null) {
                System.out.println("Login Failed");
            }
            else {
                new NewViewController( new HomescreenView("allEvents") ).showView();
            }
        });
    }

    //create user button
    public void setCreateUserHandler() {
        view.getCreateUserButton().setOnAction( actionEvent -> {
            new NewViewController( new CreateUserView() ).showView();
        });
    }
}
