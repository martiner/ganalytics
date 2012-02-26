package cz.geek.ganalytics.cookies;

import java.util.Date;

/**
 * @author martin
 */
public class ParseUtils {

	static int parseInt(String source, String field) throws CookieParseException {
		try {
			return Integer.parseInt(source);
		} catch (NumberFormatException e) {
			throw new CookieParseException("Can not parse " + field + " from " + source, e);
		}
	}

	static long parseLong(String source, String field) throws CookieParseException {
		try {
			return Long.parseLong(source);
		} catch (NumberFormatException e) {
			throw new CookieParseException("Can not parse " + field + " from " + source, e);
		}
	}

	static Date parseDate(String source, String field) throws CookieParseException {
		return new Date(parseLong(source, field) * 1000);
	}

}
