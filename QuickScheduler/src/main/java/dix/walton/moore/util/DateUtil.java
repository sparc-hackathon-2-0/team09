package dix.walton.moore.util;

import java.util.Date;

/**
 * user: ryan.moore
 * date: 8/25/12
 */
public class DateUtil {

    public static boolean isSameDay(Date date1, Date date2) {

        if (date1.getDay() == date2.getDay() &&
                date1.getMonth() == date2.getMonth() &&
                date1.getYear() == date2.getYear()) {
            return true;
        } else {
            return false;
        }
    }
}
