package NewController;

import Model.Map;
import NewView.NewMapView;
import View.MapView;
import javafx.scene.layout.VBox;

public class NewMapLogic {

    private NewMapView mapView;

    public NewMapLogic( NewMapView mapView) {
        this.mapView = mapView;
    }

    public VBox getMapVBox() {
        return Map.getInstance().getMapVBox();
    }

}
