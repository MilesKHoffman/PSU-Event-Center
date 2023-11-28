package View;

import Controller.LogicInter;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.File;

public class MapView extends ViewClass{
    public MapView() {

        super();

        drawMap();
        setScene("MapStyle.css");

    }
    public void drawMap() {
        String dir = System.getProperty("user.dir");
        File url = new File( dir + "/src/main/java/zTesting/demo.html" );

        // Create a WebView
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        // Load HTML file into WebEngine (maps: https://www.google.com/maps)
        webEngine.load(url.toURI().toString());

        // Create a scene and add the WebView to it
        webView.setPrefSize(sceneWidth,sceneHeight - headerHeight);

        VBox mapVBox = new VBox();
        mapVBox.getChildren().add(webView);
        mapVBox.setLayoutY(headerHeight);

        root.getChildren().add(mapVBox);
    }
}
