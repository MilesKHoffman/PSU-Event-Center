package View;

import Controller.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BaseView extends Application {

    private Stage stage;
    ViewController viewC;

    @Override
    public void start(Stage primaryStage) throws Exception {

        viewC = new ViewController( new LoginView() );
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