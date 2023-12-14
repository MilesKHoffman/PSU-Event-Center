package Controller;

import Model.Map;
import Repository.DatabaseHandler;
import View.Components.MapComponent;
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

    public void setMap(MapComponent map ) {

        map.addAllMarkers(DatabaseHandler.getAllEvents());
    }

}
