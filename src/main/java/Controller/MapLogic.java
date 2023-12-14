package Controller;

import Repository.DatabaseHandler;
import View.Components.MapComponent;
import View.MapView;

public class MapLogic {

    private MapView mapView;

    public MapLogic(MapView mapView) {
        this.mapView = mapView;
    }

    public void setMap(MapComponent map ) {

        map.addAllMarkers(DatabaseHandler.getAllEvents());
    }

}
