package dix.walton.moore.model;

import java.io.Serializable;

/**
 * user: ryan.moore
 * date: 8/25/12
 */
public class Event implements Serializable {

    String title;
    String startTime;
    String endTime;
    String eventDate;
    String location;
    String id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object eventObj) {

        Event event = null;

        if (eventObj instanceof Event) {
            event = (Event) eventObj;
        }

        if (event.getId() == this.getId() &&
                event.getEndTime().equals(this.getEndTime()) &&
                event.getStartTime().equals(this.getStartTime()) &&
//                DateUtil.isSameDay(event.getEventDate(), this.getEventDate()) &&
                event.getEventDate().equals(this.getEventDate()) &&
                event.getLocation().equals(this.getLocation()) &&
                event.getTitle().equals(this.getTitle())) {
            return true;
        } else {
            return false;
        }
    }
}
