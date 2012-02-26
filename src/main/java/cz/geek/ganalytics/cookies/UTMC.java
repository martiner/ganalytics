package cz.geek.ganalytics.cookies;

import org.apache.commons.lang.Validate;

import javax.servlet.http.Cookie;

/**
 * @author martin
 */
public class UTMC {

	public static final String COOKIE_NAME = "__utmc";

	private String domainNameHash;

	public UTMC(Cookie... cookies) throws CookieParseException {
		parse(GACookies.cookie(COOKIE_NAME, cookies).getValue());
	}

	protected void parse(String cookie) throws CookieParseException {
		Validate.notEmpty(cookie);
		domainNameHash = cookie;
	}

	public String getDomainNameHash() {
		return domainNameHash;
	}

	/**
	 * Parse UTMC from given cookie array
	 *
	 * @param cookies cookies from request
	 * @return UTMC instance or null if not found or parsing failed
	 */
	public static UTMC valueOf(Cookie... cookies) {
		try {
			return new UTMC(cookies);
		} catch (CookieParseException e) {
			return null;
		}
	}

}
