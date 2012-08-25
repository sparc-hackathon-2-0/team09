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

        System.out.println(googleEvent.toPrettyString());

//            if (!googleEvent.getEndTimeUnspecified()){
//                Date endDate = DateUtils.parseDate(googleEvent.getEnd().getDate());
//
//                internalEvent.setEndTime(DateUtil.toTime(endDate));
//            }
//            Date startDate = DateUtils.parseDate(googleEvent.getStart().getDate());
                internalEvent.setEndTime(googleEvent.getEnd().getDate());

            internalEvent.setEventDate(googleEvent.getEnd().getDate());

            internalEvent.setId(googleEvent.getId());
            internalEvent.setLocation(googleEvent.getLocation());
            internalEvent.setStartTime(googleEvent.getStart().getDate());
            internalEvent.setTitle(googleEvent.getDescription());
        return internalEvent;
    }
}
