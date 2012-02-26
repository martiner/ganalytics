package cz.geek.ganalytics.cookies;

import javax.servlet.http.Cookie;

public class GACookies {

	private UTMA utma;

	private UTMB utmb;

	private UTMC utmc;

	private UTMZ utmz;

	public GACookies(Cookie utma, Cookie utmb, Cookie utmc, Cookie utmz) throws CookieParseException {
		this.utma = new UTMA(utma);
		this.utmb = new UTMB(utmb);
		this.utmc = new UTMC(utmc);
		this.utmz = new UTMZ(utmz);
	}

	public GACookies(Cookie[] cookies) throws CookieParseException {
		this.utma = new UTMA(cookies);
		this.utmb = new UTMB(cookies);
		this.utmc = new UTMC(cookies);
		this.utmz = new UTMZ(cookies);
	}

	protected GACookies(UTMA utma, UTMB utmb, UTMC utmc, UTMZ utmz) {
		this.utma = utma;
		this.utmb = utmb;
		this.utmc = utmc;
		this.utmz = utmz;
	}

	public UTMA getUtma() {
		return utma;
	}

	public UTMB getUtmb() {
		return utmb;
	}

	public UTMC getUtmc() {
		return utmc;
	}

	public UTMZ getUtmz() {
		return utmz;
	}

	public static GACookies valueOf(Cookie utma, Cookie utmb, Cookie utmc, Cookie utmz) {
		return new GACookies(UTMA.valueOf(utma), UTMB.valueOf(utmb), UTMC.valueOf(utmc), UTMZ.valueOf(utmz));
	}

	public static GACookies valueOf(Cookie[] cookies) {
		return new GACookies(UTMA.valueOf(cookies), UTMB.valueOf(cookies), UTMC.valueOf(cookies), UTMZ.valueOf(cookies));
	}

	public static Cookie cookie(String name, Cookie... cookies) throws CookieParseException {
		if (cookies == null)
			throw new CookieParseException("Cookies argument can not be null");
		if (name == null)
			throw new CookieParseException("Name argument can not be null");
		for (Cookie cookie : cookies) {
			if (name.equals(cookie.getName())) {
				return cookie;
			}
		}
		return null;
	}

}
