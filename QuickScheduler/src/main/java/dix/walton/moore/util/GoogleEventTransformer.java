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
            if(googleEvent.getStart().getDateTime() != null)
            {
            startString = googleEvent.getStart().getDateTime().toString();
<<<<<<< HEAD
            int T = startString.indexOf('T');
            internalEvent.setEventDate(startString.substring(0,T));
            internalEvent.setStartTime(startString.substring(T+1, T+6));

            if(googleEvent.getEnd() != null)
            {
                String endString = googleEvent.getEnd().getDateTime().toString();
                T = endString.indexOf('T');
                internalEvent.setEndTime(endString.substring(T + 1, T+6));
            }
        } catch (Exception e) {
=======
            internalEvent.setEventDate(dateFormat.parse(startString).toString());
                internalEvent.setStartTime(timeFormat.parse(startString, new ParsePosition(startString.indexOf('T') + 1)).toString());

            }
            if(googleEvent.getEnd().getDateTime() != null)
            {
                String endString = googleEvent.getEnd().getDateTime().toString();
                internalEvent.setEndTime(timeFormat.parse(endString, new ParsePosition(endString.indexOf('T') + 1)).toString());
            }

            } catch (ParseException e) {
>>>>>>> happy birthday!!!!
            System.out.println("Exception thrown");
        }

        internalEvent.setId(googleEvent.getId());
<<<<<<< HEAD
            internalEvent.setLocation(googleEvent.getLocation());
            internalEvent.setTitle(googleEvent.getSummary());
=======
        internalEvent.setLocation(googleEvent.getLocation());
        internalEvent.setTitle(googleEvent.getSummary());
>>>>>>> happy birthday!!!!
        return internalEvent;
    }
}
