package NewView;

import Controller.MapLogic;
import Model.Map;
import NewController.NewMapLogic;
import View.ViewClass;
import javafx.scene.layout.VBox;

public class NewMapView extends ViewClass {

    private NewMapLogic logic = new NewMapLogic(this);
    public NewMapView() {

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
