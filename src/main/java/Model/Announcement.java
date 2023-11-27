package Model;

import java.time.LocalDateTime;

public class Announcement extends Activity {
    public Announcement(String name, String desc, LocalDateTime dateTime) {
        super(name, desc, dateTime);
    }
}
