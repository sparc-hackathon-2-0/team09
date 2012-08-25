package dix.walton.moore.service;

import android.content.Context;
import dix.walton.moore.model.Event;

/**
 * user: ryan.moore
 * date: 8/25/12
 */
public class GoogleCalendarService {

    Context context;

    public GoogleCalendarService(Context context) {
        this.context = context;
    }

    public Event createEvent(String eventString) {

        //walton does stuff here
        return new Event();
    }
}
