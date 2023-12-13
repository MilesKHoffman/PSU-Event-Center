package Controller;


import View.CreateUserView;
import View.HomescreenView;
import View.LoginView;
import Repository.DatabaseHandler;

public class LoginLogic extends LogicInter {

    private LoginView view;

    public LoginLogic(LoginView view ){
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
                new ViewController( new HomescreenView() ).showView();
            }
        });
    }

    //create user button
    public void setCreateUserHandler() {
        view.getCreateUserButton().setOnAction( actionEvent -> {
            new ViewController( new CreateUserView() ).showView();
        });
    }
}
