package View;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class EventCard extends Pane {

    private String name;
    private String description;
    private String location;

    private int width = 10;
    private int height = 150;

    public EventCard(String name, String description, String location) {
        this.name = name;
        this.description = description;
        this.location = location;

        setCard();
        drawCard();
        setEvents();
    }

    private void setCard(){
        setWidth(width);
        setHeight(height);
    }

    private void drawCard(){

        // Customize the appearance of your EventCard
        setStyle("-fx-background-color: lightgray; -fx-padding: 10px;");

        // Display information on the card using labels or other nodes
        Label nameLabel = new Label("Name: " + name);
        Label descriptionLabel = new Label("Description: " + description);
        Label locationLabel = new Label("Location: " + location);

        // Add labels to the EventCard
        getChildren().addAll(nameLabel, descriptionLabel, locationLabel);
    }

    private void setEvents(){

        // Set up the click event
        setOnMouseClicked(event -> {
            // Handle the click event here, e.g., trigger some action
            System.out.println("Event card clicked: " + name);
        });
    }
}
