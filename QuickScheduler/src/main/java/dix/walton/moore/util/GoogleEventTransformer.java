package dix.walton.moore.util;

import com.google.api.services.calendar.model.Event;
import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;

import java.util.Date;

/**
 * user: ryan.moore
 * date: 8/25/12
 */
public class GoogleEventTransformer {

    public static dix.walton.moore.model.Event convertToOurEvent(Event googleEvent)  {

        dix.walton.moore.model.Event internalEvent = new dix.walton.moore.model.Event();

        try {
            if (!googleEvent.getEndTimeUnspecified()){
                Date endDate = DateUtils.parseDate(googleEvent.getEnd().getDate());

                internalEvent.setEndTime(DateUtil.toTime(endDate));
            }
            Date startDate = DateUtils.parseDate(googleEvent.getStart().getDate());

            internalEvent.setEventDate(DateUtils.parseDate(googleEvent.getEnd().getDate()));

            internalEvent.setId(Long.valueOf(googleEvent.getId()));
            internalEvent.setLocation(googleEvent.getLocation());
            internalEvent.setStartTime(DateUtil.toTime(startDate));
            internalEvent.setTitle(googleEvent.getDescription());
        } catch (DateParseException dpe) {
            System.out.println("DATE PARSE ERROR BERRY BERRY BAD MAN");
        }

        return internalEvent;
    }
}
