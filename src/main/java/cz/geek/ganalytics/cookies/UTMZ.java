package cz.geek.ganalytics.cookies;

import org.apache.commons.lang.Validate;

import javax.servlet.http.Cookie;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * This cookie stores the type of referral used by the visitor to reach your site, whether via a direct method, a referring link,
 * a website search, or a campaign such as an ad or an email link. It is used to calculate search engine traffic,
 * ad campaigns and page navigation within your own site. The cookie is updated with each page view to your site.
 * Expires 6 months from set/update.
 * http://code.google.com/intl/cs/apis/analytics/docs/concepts/gaConceptsCookies.html#cookiesSet
 */
public class UTMZ {

	public static final String COOKIE_NAME = "__utmz";

	private String domainNameHash;

    private Date timestamp;

    private int visits;

    private int sources;

    private String campaign;

    private String source;

    private String keyword;

    private String medium;

    private String content;

    /**
     *
     * @param cookie
     * @see #valueOf(javax.servlet.http.Cookie)
     */
    public UTMZ(String cookie) throws CookieParseException {
        Validate.notEmpty(cookie);
        parse(cookie);
    }

    protected void parse(String cookie) throws CookieParseException {
        String[] dots = cookie.split("\\.", 5);
        if (dots.length != 5)
            throw new CookieParseException("Expecting 5 elements divided by dot, but have " + dots.length);
        domainNameHash = dots[0];
        timestamp = new Date(parseLong(dots[1], "date") * 1000);
        visits = parseInt(dots[2], "visits");
        sources = parseInt(dots[3], "sources");

        String[] utm = dots[4].split("\\|");
        Map<String,String> map = new HashMap<String, String>(utm.length);
        for (String i: utm) {
            String[] split = i.split("=", 2);
            map.put(split[0], split[1]);
        }
        campaign = map.get("utmccn");
        source = map.get("utmcsr");
        medium = map.get("utmcmd");
        keyword = map.get("utmctr");
        content = map.get("utmcct");
    }

	private int parseInt(String source, String field) throws CookieParseException {
	    try {
	        return Integer.parseInt(source);
	    } catch (NumberFormatException e) {
	        throw new CookieParseException("Can not parse " + field + " from " + source, e);
	    }
	}

	private long parseLong(String source, String field) throws CookieParseException {
	    try {
	        return Long.parseLong(source);
	    } catch (NumberFormatException e) {
	        throw new CookieParseException("Can not parse " + field + " from " + source, e);
	    }
	}

    /**
     * hash of your domain name
     * @return domain name hash
     */
    public String getDomainNameHash() {
        return domainNameHash;
    }

    /**
     * Timestamp when the cookie was set
     * @return timestamp
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * How many visits they had made when the cookie was set
     * @return visits
     */
    public int getVisits() {
        return visits;
    }

    /**
     * How many different sources this visitor has come from
     * @return sources
     */
    public int getSources() {
        return sources;
    }

    /**
     * utmccn
     * @return
     */
    public String getCampaign() {
        return campaign;
    }

    /**
     * utmcsr
     * @return
     */
    public String getSource() {
        return source;
    }

    /**
     *
     * @return
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     *
     * @return
     */
    public String getMedium() {
        return medium;
    }

    /**
     *
     * @return
     */
    public String getContent() {
        return content;
    }

    public static UTMZ valueOf(Cookie cookie) {
	    if (cookie == null)
			return null;
	    try {
		    return new UTMZ(cookie.getValue());
	    } catch (CookieParseException e) {
		    return null;
	    }
    }

    /**
     * Parse UTMZ for given domain name hash from given cookie array
     * @param cookies cookies from request
     * @return UTMZ instance or null if not found or parsing failed
     */
    public static UTMZ valueOf(Cookie[] cookies) {
        if (cookies == null)
            return null;
        for (Cookie cookie: cookies) {
            if (COOKIE_NAME.equals(cookie.getName())) {
				return valueOf(cookie);
            }
        }
        return null;
    }

}
