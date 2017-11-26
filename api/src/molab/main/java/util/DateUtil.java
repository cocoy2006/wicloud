package molab.main.java.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {
	
	public static String day() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(System.currentTimeMillis());
		return sdf.format(c.getTime());
	}

	public static String day(int diff) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(System.currentTimeMillis() + diff * 86400000l);
		return sdf.format(c.getTime());
	}
	
	public static int dayInMillis(int diff) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(System.currentTimeMillis() + diff * 86400000l);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return (int) (c.getTimeInMillis() / 1000);
	}
	
	public static String dateOnStart(int diff) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(System.currentTimeMillis() + diff * 86400000l);
		return sdf.format(c.getTime()) + "000000";
	}
	
	public static String dateOnEnd(int diff) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(System.currentTimeMillis() + diff * 86400000l);
		return sdf.format(c.getTime()) + "235959";
	}
	
	public static String dateOnNow(int diff) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(System.currentTimeMillis() + diff * 86400000l);
		return sdf.format(c.getTime());
	}

	public static long parse(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.parse(date).getTime();
	}
	
	public static String parse(long date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(date);
		return sdf.format(c.getTime());
	}
	
}
