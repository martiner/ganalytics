package cz.geek.ganalytics.cookies;

import org.junit.Test;

import java.util.Date;

import static junit.framework.Assert.assertEquals;

public class UTMZTest {

    @Test
    public void direct() throws CookieParseException {
        UTMZ utmz = new UTMZ("114442907.1315070575.1.1.utmccn=(direct)|utmcsr=(direct)|utmcmd=(none)");
        assertEquals("114442907", utmz.getDomainNameHash());
        assertEquals(new Date(1315070575 * 1000L), utmz.getTimestamp());
        assertEquals(1, utmz.getVisits());
        assertEquals(1, utmz.getSources());
        assertEquals("(direct)", utmz.getCampaign());
        assertEquals("(direct)", utmz.getSource());
        assertEquals("(none)", utmz.getMedium());
    }

    @Test
    public void organic() throws CookieParseException {
        UTMZ utmz = new UTMZ("114442907.1327488330.1.1.utmccn=(organic)|utmcsr=google|utmctr=magvia|utmcmd=organic");
        assertEquals("114442907", utmz.getDomainNameHash());
        assertEquals(new Date(1327488330 * 1000L), utmz.getTimestamp());
        assertEquals(1, utmz.getVisits());
        assertEquals(1, utmz.getSources());
        assertEquals("(organic)", utmz.getCampaign());
        assertEquals("google", utmz.getSource());
        assertEquals("organic", utmz.getMedium());
        assertEquals("magvia", utmz.getKeyword());
    }

    @Test
    public void referral() throws CookieParseException {
        UTMZ utmz = new UTMZ("114442907.1327488399.1.1.utmccn=(referral)|utmcsr=slatina.macroware.cz|utmcct=/magvia.jsp|utmcmd=referral");
        assertEquals("114442907", utmz.getDomainNameHash());
        assertEquals(new Date(1327488399 * 1000L), utmz.getTimestamp());
        assertEquals(1, utmz.getVisits());
        assertEquals(1, utmz.getSources());
        assertEquals("(referral)", utmz.getCampaign());
        assertEquals("slatina.macroware.cz", utmz.getSource());
        assertEquals("referral", utmz.getMedium());
        assertEquals("/magvia.jsp", utmz.getContent());
    }

    @Test
    public void cpc() throws CookieParseException {
        UTMZ utmz = new UTMZ("129313107.1327488555.1.1.utmcsr=google|utmccn=vk_vk%20-%20csob%20pojistovna%20S_csob%20pojistovna|utmcmd=cpc|utmctr=%C4%8Dsob%20poji%C5%A1%C5%A5ovna|utmcct=online");
        assertEquals("129313107", utmz.getDomainNameHash());
        assertEquals(new Date(1327488555 * 1000L), utmz.getTimestamp());
        assertEquals(1, utmz.getVisits());
        assertEquals(1, utmz.getSources());
        assertEquals("vk_vk%20-%20csob%20pojistovna%20S_csob%20pojistovna", utmz.getCampaign());
        assertEquals("google", utmz.getSource());
        assertEquals("cpc", utmz.getMedium());
        assertEquals("%C4%8Dsob%20poji%C5%A1%C5%A5ovna", utmz.getKeyword());
        assertEquals("online", utmz.getContent());
    }

}
