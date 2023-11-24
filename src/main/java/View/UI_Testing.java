package View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UI_Testing extends Application {

    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {


        HomescreenView homeView = new HomescreenView();

        stage = new Stage();
        setStageScene( homeView.getScene() );
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