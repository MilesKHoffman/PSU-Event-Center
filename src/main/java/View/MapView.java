package View;

import Controller.LogicInter;
import Controller.MapLogic;
import Model.Map;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.File;

public class MapView extends ViewClass{

    private MapLogic logic = new MapLogic(this);
    public MapView() {

        super();

        drawMap();
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
}
