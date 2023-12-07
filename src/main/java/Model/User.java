package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class User {

    private static User user = new User();
    private int userID;
    private String username, password;
    private ArrayList<Event> eventList;
    private ArrayList<Announcement> annList;
    private boolean admin;

    private User() {
        this.username = "";
        this.password = "";
        eventList = new ArrayList<Event>();

        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDate localDate = localDateTime.toLocalDate();
        LocalTime localTime = localDateTime.toLocalTime();

        admin = false;
    }

    public static User getInstance() {
        return user;
    }

    public void login(int userID, String username, int admin) {
        this.userID = userID;
        this.username = username;
        this.admin = admin == 1;
    }

    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setEventList(ArrayList<Event> eventList) {
        this.eventList = eventList;
    }

    public ArrayList<Event> getEventList() {
        return eventList;
    }

    public ArrayList<Announcement> getAnnList() {
        return annList;
    }

    public boolean isAdmin() {
        return admin;
    }
}
