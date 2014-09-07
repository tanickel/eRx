package com.walgreens.pharmacy.rules.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {
	
	public int absoulteValue(int v){
		return Math.abs(v);
	}

	public static Date subtractDays(Date date, int days) {
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.setTime(date);
		cal.add(java.util.Calendar.DATE,-days);
		return cal.getTime();
	}
	
	public static Date addDays(Date date, int days) {
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.setTime(date);
		cal.add(java.util.Calendar.DATE,days);
		return cal.getTime();
	}
	
	public static Date addYers(Date date, int years) {
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.setTime(date);
		cal.add(java.util.Calendar.YEAR,years);
		return cal.getTime();
	}

	public boolean validateWholeNumber(double d) {

		if ((int) d == d)
			return true;
		else
			return false;
	}

	public static String dateToString(Date date1) {
		if (date1 == null)
			return new String("");
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		return df.format(date1);
	}

	public static String intToString(Integer integer1) {
		if (integer1 == 0)
			return "";
		return integer1.toString();
	}

	public static Integer stringToInt(String string1) {
		if (string1 == "" || string1 == null)
			return 0;
		return Integer.parseInt(string1);
	}

	public static boolean isMultiple(double dbl1, double dbl2) {
		return dbl1 % dbl2 == 0;
	}

	public static int absolute(Integer difference) {
		return (Math.abs(difference));
	}

	public static boolean checkGpi12(String gpi1, String gpi2) {
		if (gpi1 == null || gpi2 == null || gpi1.length() < 12
				|| gpi1.length() < 12)
			return false;
		return gpi1.substring(0, 12).equals(gpi2.substring(0, 12));
	}

	public static boolean within24hours(Date date1, Date date2) {
		if (date1 == null || date2 == null)
			return false;
		else
			return java.lang.Math.abs(date1.getTime() - date2.getTime()) < 86400000;
	}
}
