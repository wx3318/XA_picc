package com.picc.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MonthUtil {
	/**
	 * 判断周末
	 * @param bDate
	 * @return
	 * @throws ParseException
	 */
    public static String isWeekend(String bDate) throws ParseException {
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        Date bdate = format1.parse(bDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(bdate);
        if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            return "OK";
        } else{
            return "NO";
        }
    }
    /**
     * 年月判断
     * @param date1
     * @param date2
     * @return
     */
	public static boolean isSameDate(Date date1, Date date2) {
		try {
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(date1);

			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(date2);

			boolean isSameYear = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
			boolean isSameMonth = isSameYear && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
			boolean isSameDate = isSameMonth && cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);

			return isSameDate;
		} catch (Exception e) {
			
		}
		return false;
	}
}
