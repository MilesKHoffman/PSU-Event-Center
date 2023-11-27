package Model;

import java.time.LocalDateTime;

public class Event extends Activity {

    private double latitude, longitude;
    public Event(String name, String desc, LocalDateTime dateTime) {
        super(name, desc, dateTime);
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
