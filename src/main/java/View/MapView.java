package View;

import Controller.MapLogic;
import Repository.DatabaseHandler;
import View.Components.MapComponent;
import javafx.scene.layout.BorderPane;

public class MapView extends ViewClass {

    private MapLogic logic = new MapLogic(this);
    public MapView() {

        super();

        drawMap();
        setScene("MapStyle.css");
    }
// test
    private void drawMap(){
        MapComponent map = new MapComponent();
        //logic.setMap(map);


        BorderPane mapPane = map.getRoot();

        mapPane.setPrefHeight( sceneHeight - headerHeight);
        mapPane.setLayoutY(headerHeight);
        mapPane.setPrefWidth(sceneWidth);
        map.addAllMarkers(DatabaseHandler.getAllEvents());
        root.getChildren().add(mapPane);
    }
}
