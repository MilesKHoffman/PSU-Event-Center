package Model;

import java.time.LocalDateTime;

public class Announcement extends Activity {
    public Announcement(int id, String name, String desc, LocalDateTime dateTime) {
        super(id, name, desc, dateTime);
    }
}
