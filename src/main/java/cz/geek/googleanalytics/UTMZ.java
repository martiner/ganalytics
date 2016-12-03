package cz.geek.googleanalytics;

import org.apache.commons.lang.Validate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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
    public UTMZ(String cookie) throws UTMZParseException {
        Validate.notEmpty(cookie);
        parse(cookie);
    }

    protected void parse(String cookie) throws UTMZParseException {
        String[] dots = cookie.split("\\.", 5);
        if (dots.length != 5)
            throw new UTMZParseException("Expecting 5 elements divided by dot, but have " + dots.length);
        domainNameHash = dots[0];
        timestamp = new Date(parseInt(dots[1], "date"));
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

    private int parseInt(String source, String field) throws UTMZParseException {
        try {
            return Integer.parseInt(source);
        } catch (NumberFormatException e) {
            throw new UTMZParseException("Can not parse " + field + " from " + source, e);
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

    public static UTMZ valueOf(Cookie cookie) throws UTMZParseException {
        Validate.notNull(cookie);
        return new UTMZ(cookie.getValue());
    }

    /**
     * Parse UTMZ for given domain name hash from given cookie array
     * @param cookies cookies from request
     * @param domainNameHash domain name hash
     * @return UTMZ instance or null if not found or parsing failed
     */
    public static UTMZ valueOf(Cookie[] cookies, String domainNameHash) {
        Validate.notEmpty(domainNameHash);
        if (cookies == null)
            return null;
        for (Cookie cookie: cookies) {
            if ("__utmz".equals(cookie.getName())) {
                try {
                    UTMZ utmz = valueOf(cookie);
                    if (domainNameHash.equals(utmz.getDomainNameHash()))
                        return utmz;
                } catch (UTMZParseException ignored) { }
            }
        }
        return null;
    }

}

