package Model;

import View.ViewClass;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.File;

public class Map extends ViewClass {

    private static Map map = new Map();
    private String name, location;
    private double latitude, longitude;

    private Map() {

    }

    public static Map getMap() {
        return map;
    }
}
