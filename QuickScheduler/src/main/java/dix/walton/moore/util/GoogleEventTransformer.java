package dix.walton.moore.util;

import com.google.api.services.calendar.model.Event;
import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * user: ryan.moore
 * date: 8/25/12
 */
public class GoogleEventTransformer {

    public static dix.walton.moore.model.Event convertToOurEvent(Event googleEvent)  {

        dix.walton.moore.model.Event internalEvent = new dix.walton.moore.model.Event();

        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        DateFormat timeFormat = new SimpleDateFormat("hh:mm aaa");

        try {
            internalEvent.setEventDate(dateFormat.parse(googleEvent.getStart().getDateTime().toString()).toString());
            internalEvent.setEndTime(timeFormat.parse(googleEvent.getEnd().getDateTime().toString()).toString());
            internalEvent.setStartTime(timeFormat.parse(googleEvent.getStart().getDateTime().toString()).toString());
        } catch (ParseException e) {

        }

        internalEvent.setId(googleEvent.getId());
            internalEvent.setLocation(googleEvent.getLocation());
            internalEvent.setStartTime(googleEvent.getStart().getDateTime().toString());
            internalEvent.setTitle(googleEvent.getSummary());
        return internalEvent;
    }
}
