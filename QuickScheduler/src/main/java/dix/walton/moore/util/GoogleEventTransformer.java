package dix.walton.moore.util;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * user: ryan.moore
 * date: 8/25/12                                                                                      4
 */
public class GoogleEventTransformer {

    public static dix.walton.moore.model.Event convertToOurEvent(Event googleEvent)  {

        dix.walton.moore.model.Event internalEvent = new dix.walton.moore.model.Event();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM");
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String startString;
        try {
            startString = googleEvent.getStart().getDateTime().toString();
            internalEvent.setEventDate(dateFormat.parse(startString).toString());
            if(!googleEvent.getEndTimeUnspecified())
            {
                String endString = googleEvent.getEnd().getDateTime().toString();
                internalEvent.setEndTime(timeFormat.parse(endString, new ParsePosition(startString.indexOf('T') + 1)).toString());
            }
            internalEvent.setStartTime(timeFormat.parse(startString, new ParsePosition(startString.indexOf('T') + 1)).toString());
        } catch (ParseException e) {
            System.out.println("Exception thrown");
        }

        internalEvent.setId(googleEvent.getId());
            internalEvent.setLocation(googleEvent.getLocation());
            internalEvent.setStartTime(googleEvent.getStart().getDateTime().toString());
            internalEvent.setTitle(googleEvent.getSummary());
        return internalEvent;
    }
}
