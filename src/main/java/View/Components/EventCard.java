package View.Components;

import Controller.EventCardLogic;
import Controller.ViewController;
import Model.User;
import Repository.DatabaseHandler;
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

    private int id;
    private String name;
    private String description;
    private String location;
    private String club;
    private boolean isFollowed;
    private Button followButton, deleteButton;
    private LocalDateTime dateTime;
    private EventCardLogic logic = new EventCardLogic(this);

    private int width = 350;
    private int height = 110;

    public EventCard(int id, String name, String description, String location, String club, boolean isFollowed, LocalDateTime time) {
        this.id = id;
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

        if( logic.checkAdmin() ){
            deleteButton = new Button("DELETE");
            deleteButton.getStyleClass().addAll("deleteButton", "buttonClearStandard");
            getChildren().addAll(nameLabel, timeLabel, locationLabel, clubLabel, followButton, deleteButton);
        }
        else {
            getChildren().addAll(nameLabel, timeLabel, locationLabel, clubLabel, followButton);
        }
    }

    private void setEvents(){

        followButton.setOnMouseClicked( mouseEvent -> {
            followClicked();
        });

        if (deleteButton != null) {
            deleteButton.setOnMouseClicked(mouseEvent -> {
                if (DatabaseHandler.deleteEvent(this.id)) {
                    System.out.println("del successful");
                } else {
                    System.out.println("failed");
                }
            });
        }
    }

    private void followClicked() {
        isFollowed = !isFollowed;
        followButton.setText(isFollowed ? "UNFOLLOW" : "FOLLOW");
        if (isFollowed) {
            DatabaseHandler.saveEvent(User.getInstance().getUserID(), this.id);
        }
        else {
            DatabaseHandler.unfollowEvent(User.getInstance().getUserID(), this.id);
        }
    }

    public boolean getIsFollowed(){ return isFollowed; }
    public String getName(){ return name; }

    public String getLocation() {
        return location;
    }

    public String getClub() {
        return club;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
