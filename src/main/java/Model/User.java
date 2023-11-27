package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class User {

    private static User user = new User();
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
        eventList.add(new Event("Event1", "blah blah", localDateTime));
        eventList.add(new Event("Event2", "blah blah", localDateTime));
        eventList.add(new Event("Event3", "blah blah", localDateTime));
        eventList.add(new Event("Event4", "blah blah", localDateTime));
        annList = new ArrayList<Announcement>();
        admin = false;
    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static User getUser() {
        return user;
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
