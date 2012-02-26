package cz.geek.ganalytics.cookies;

import org.apache.commons.lang.Validate;

import javax.servlet.http.Cookie;
import java.util.Date;

import static cz.geek.ganalytics.cookies.ParseUtils.parseDate;
import static cz.geek.ganalytics.cookies.ParseUtils.parseInt;

/**
 * @author martin
 */
public class UTMB {

	public static final String COOKIE_NAME = "__utmb";

	private String domainNameHash;

	private int pages;

	private Date current;

	public UTMB(Cookie... cookies) throws CookieParseException {
		parse(GACookies.cookie(COOKIE_NAME, cookies).getValue());
	}

	protected void parse(String cookie) throws CookieParseException {
		Validate.notEmpty(cookie);
		String[] dots = cookie.split("\\.", 4);
		if (dots.length != 4)
			throw new CookieParseException("Expecting 4 elements divided by dot, but have " + dots.length);
		domainNameHash = dots[0];
		pages = parseInt(dots[1], "pages");
		current = parseDate(dots[3], "current");
	}

	public String getDomainNameHash() {
		return domainNameHash;
	}

	public int getPages() {
		return pages;
	}

	public Date getCurrent() {
		return current;
	}

	/**
	 * Parse UTMB from given cookie array
	 *
	 * @param cookies cookies from request
	 * @return UTMB instance or null if not found or parsing failed
	 */
	public static UTMB valueOf(Cookie... cookies) {
		try {
			return new UTMB(cookies);
		} catch (CookieParseException e) {
			return null;
		}
	}

}
