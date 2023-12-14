package View;

import Controller.MapLogic;
import View.Components.MapComponent;
import javafx.scene.layout.BorderPane;

public class MapView extends ViewClass {

    private MapLogic logic = new MapLogic(this);
    public MapView() {

        super();

        drawMap();
        setScene("MapStyle.css");
    }

    private void drawMap(){
        MapComponent map = new MapComponent();

        BorderPane mapPane = map.getRoot();

        mapPane.setPrefHeight( sceneHeight - headerHeight);
        mapPane.setLayoutY(headerHeight);
        mapPane.setPrefWidth(sceneWidth);

        root.getChildren().add(mapPane);
    }
}
