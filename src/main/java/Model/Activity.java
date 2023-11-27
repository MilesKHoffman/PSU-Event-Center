package Model;

import java.time.LocalDateTime;

public class Activity {

    public Activity(String name, String desc, LocalDateTime dateTime) {
        this.name = name;
        this.desc = desc;
        this.dateTime = dateTime;
    }
    private String name;
    private String desc;
    private LocalDateTime dateTime;

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
