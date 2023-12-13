package View.Components;

import Controller.ViewController;
import View.EventView;
import View.HomescreenView;
import View.ViewClass;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class EventCard extends Pane {

    private String name;
    private String description;
    private String location;
    private String club;
    private boolean isFollowed;
    private Button followButton;
    private LocalDateTime dateTime;

    private int width = 350;
    private int height = 110;

    public EventCard(String name, String description, String location, String club, boolean isFollowed, LocalDateTime time) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.club = club;
        this.isFollowed = isFollowed;
        this.dateTime = time;

        setCard();
        drawCard();
        setEvents();
    }

    private void setCard(){
        setWidth(width);
        setHeight(height);
        getStyleClass().add("eventCard");
    }

    private void drawCard(){

        // Display information on the card using labels or other nodes
        Label nameLabel = new Label(name);
        nameLabel.getStyleClass().add("nameText");

        Label timeLabel = new Label( dateTime
                .format(DateTimeFormatter.ofPattern("EEEE, MMMM d 'at' h:mma")));
        timeLabel.getStyleClass().add("timeText");

        Label locationLabel = new Label(location);
        locationLabel.getStyleClass().add("locationText");

        Label clubLabel = new Label(club);
        clubLabel.getStyleClass().add("clubText");

        followButton = new Button( isFollowed ? "UNFOLLOW" : "FOLLOW");
        followButton.getStyleClass().addAll("followButton", "buttonClearStandard");

        // Add labels to the EventCard
        getChildren().addAll(nameLabel, timeLabel, locationLabel, clubLabel, followButton);
    }

    private void setEvents(){

        // Set up the click event
        setOnMouseClicked(event -> {
            // Handle the click event here, e.g., trigger some action
            System.out.println("Event card clicked: " + name);

            new ViewController( new EventView(!isFollowed) ).showView();
        });

        followButton.setOnMouseClicked( mouseEvent -> {
            followClicked();
        });
    }

    private void followClicked() {
        isFollowed = !isFollowed;
        followButton.setText(isFollowed ? "UNFOLLOW" : "FOLLOW");
    }
}
