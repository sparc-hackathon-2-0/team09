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

            internalEvent.setEventDate(dateFormat.format(googleEvent.getStart().getDateTime()));
            internalEvent.setEndTime(timeFormat.format(googleEvent.getEnd().getDateTime()));
            internalEvent.setStartTime(timeFormat.format(googleEvent.getStart().getDateTime()));

        internalEvent.setId(googleEvent.getId());
            internalEvent.setLocation(googleEvent.getLocation());
            internalEvent.setStartTime(googleEvent.getStart().getDateTime().toString());
            internalEvent.setTitle(googleEvent.getSummary());
        return internalEvent;
    }
}
