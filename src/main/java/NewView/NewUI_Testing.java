package NewView;

import View.EventEditorView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NewUI_Testing extends Application {

    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {

        NewMapView view = new NewMapView();

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