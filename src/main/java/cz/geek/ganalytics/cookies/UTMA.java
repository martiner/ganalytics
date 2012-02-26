package cz.geek.ganalytics.cookies;

import org.apache.commons.lang.Validate;

import javax.servlet.http.Cookie;
import java.util.Date;

import static cz.geek.ganalytics.cookies.ParseUtils.parseDate;
import static cz.geek.ganalytics.cookies.ParseUtils.parseInt;

/**
 * @author martin
 */
public class UTMA {

	public static final String COOKIE_NAME = "__utma";

	private String domainNameHash;

	private String visitorHash;

	private Date first;

	private Date previous;

	private Date current;

	private int visits;

	public UTMA(Cookie... cookies) throws CookieParseException {
		parse(GACookies.cookie(COOKIE_NAME, cookies).getValue());
	}

	protected void parse(String cookie) throws CookieParseException {
		Validate.notEmpty(cookie);
		String[] dots = cookie.split("\\.", 6);
		if (dots.length != 6)
			throw new CookieParseException("Expecting 6 elements divided by dot, but have " + dots.length);
		domainNameHash = dots[0];
		visitorHash = dots[1];
		first = parseDate(dots[2], "first visit");
		previous = parseDate(dots[3], "previous visit");
		current = parseDate(dots[4], "current visit");
		visits = parseInt(dots[5], "visits");
	}

	public String getDomainNameHash() {
		return domainNameHash;
	}

	public String getVisitorHash() {
		return visitorHash;
	}

	public Date getFirst() {
		return first;
	}

	public Date getPrevious() {
		return previous;
	}

	public Date getCurrent() {
		return current;
	}

	public int getVisits() {
		return visits;
	}

	/**
	 * Parse UTMA from given cookie array
	 *
	 * @param cookies cookies from request
	 * @return UTMA instance or null if not found or parsing failed
	 */
	public static UTMA valueOf(Cookie... cookies) {
		try {
			return new UTMA(cookies);
		} catch (CookieParseException e) {
			return null;
		}
	}

}
