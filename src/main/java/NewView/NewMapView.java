package NewView;

import Controller.MapLogic;
import Model.Map;
import NewController.NewMapLogic;
import View.ViewClass;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class NewMapView extends NewViewClass {

    private NewMapLogic logic = new NewMapLogic(this);
    public NewMapView() {

        super();

        milesMap();
        setScene("MapStyle.css");

    }
    public void drawMap() {

        VBox mapVBox = logic.getMapVBox();
        Map.setMapCenter(42.119212, -79.982995);

        mapVBox.setPrefSize(sceneWidth,sceneHeight - headerHeight);
        mapVBox.setLayoutX(0);
        mapVBox.setLayoutY(headerHeight);

        root.getChildren().add(mapVBox);
    }

    private void milesMap(){
        MilesMap map = new MilesMap();

        BorderPane mapPane = map.getRoot();

        mapPane.setPrefHeight( sceneHeight - headerHeight);
        mapPane.setLayoutY(headerHeight);

        root.getChildren().add(mapPane);
    }
}
