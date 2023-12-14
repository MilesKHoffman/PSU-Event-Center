package View;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UI_Testing extends Application {

    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {

        MapView view = new MapView();

        stage = new Stage();
        setStageScene( view.getScene() );
        stage.setTitle("SWENG 411 Proj");
        stage.show();

        stage.setOnCloseRequest( event -> {
            Platform.exit();
            System.out.println("Platform exit");
        });
    }

    public void setStageScene( Scene scene ){
        stage.setScene(scene);
    }

    public static void main( String[] args){
        Application.launch(args);
    }
}