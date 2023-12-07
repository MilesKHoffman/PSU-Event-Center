package Controller;

import Model.Map;
import View.MapView;
import javafx.scene.layout.VBox;

public class MapLogic {

    private MapView mapView;

    public MapLogic(MapView mapView) {
        this.mapView = mapView;
    }

    public VBox getMapVBox() {
        return Map.getInstance().getMapVBox();
    }

}
