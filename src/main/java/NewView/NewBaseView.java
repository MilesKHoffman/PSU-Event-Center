package NewView;

import Controller.*;
import NewController.NewViewController;
import View.LoginView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NewBaseView extends Application {

    private Stage stage;
    NewViewController viewC;

    @Override
    public void start(Stage primaryStage) throws Exception {

        viewC = new NewViewController( new NewLoginView() );
        viewC.setBaseView( this );

        stage = new Stage();
        viewC.showView();
        stage.setTitle("SWENG 411 Proj");
        stage.show();
    }

    public void setStageScene( Scene scene ){
        stage.setScene(scene);
    }

    public static void main( String[] args){
        Application.launch(args);
    }
}