package Model;

import java.time.LocalDateTime;

public class Event extends Activity {

    private boolean isFollow;
    private double latitude, longitude;
    private String club;
    public Event(int id, String name, String desc, LocalDateTime dateTime, double latitude, double longitude) {
        super(id, name, desc, dateTime);
        this.latitude = latitude;
        this.longitude = longitude;
        this.isFollow = isFollow;
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
}
