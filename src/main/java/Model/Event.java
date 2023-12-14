package Model;

import java.time.LocalDateTime;

public class Event extends Activity {

    private double latitude, longitude;
    private String club;
    private String location;

    public Event(int id, String name, String desc, LocalDateTime dateTime, double latitude, double longitude, String club, String location) {
        super(id, name, desc, dateTime);
        this.latitude = latitude;
        this.longitude = longitude;
        this.club = club;
        this.location = location;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getLocation() {
        return location;
    }

    public String getClub() {
        return club;
    }
}
