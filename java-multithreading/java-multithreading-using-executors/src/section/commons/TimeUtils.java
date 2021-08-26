package section.commons;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeUtils {

	private TimeUtils() {
		// DO NOTHING
	}

	public static Date getFutureTime(Date initialTime, long millisToAdd) {
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTimeInMillis(cal.getTimeInMillis() + millisToAdd);

		return cal.getTime();
	}

}
