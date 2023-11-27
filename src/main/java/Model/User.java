package Model;

import java.util.ArrayList;

public class User {

    private String username, password;
    private ArrayList<Event> eventList;
    private ArrayList<Announcement> annList;
    private boolean admin;

    public User() {
        this.username = "";
        this.password = "";
        eventList = new ArrayList<Event>();
        annList = new ArrayList<Announcement>();
    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
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
