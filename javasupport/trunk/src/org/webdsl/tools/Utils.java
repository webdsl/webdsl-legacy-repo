package org.webdsl.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Utils {
	public static Date parseDate(String date, String format) {
		try {
			return new SimpleDateFormat(format).parse(date);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
	/*public static boolean equal(int a, int b) {
		return a == b;
	}
	
	public static boolean equal(double a, double b) {
		return a == b;
	}
	
	public static boolean equal(int a, Integer b) {
		return b.equals(a);
	}

	public static boolean equal(double a, Double b) {
		return b.equals(a);
	}*/
	
	public static boolean equal(Object a, Object b) {
		if(a == null && b == null) {
			return true;
		} else if(a == null || b == null) {
			return false;
		}
		return a.equals(b);
	}
}