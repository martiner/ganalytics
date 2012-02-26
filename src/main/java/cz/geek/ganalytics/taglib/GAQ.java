package cz.geek.ganalytics.taglib;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author martin
 */
public class GAQ {

	public static final String TRACK_PAGEVIEW = "_trackPageview";
	public static final String SET_ACCOUNT = "_setAccount";

	private List<Object[]> items = new ArrayList<Object[]>();

	private Object account;

	private Object trackPageview;

	public void add(Object... value) {
		Validate.notEmpty(value);
		if (TRACK_PAGEVIEW.equals(value[0]))
			trackPageview = value[1];
		else if (SET_ACCOUNT.equals(value[0]))
			account = value[1];
		else
			items.add(value);
	}

	public void defaultAccount(String account) {
		if (this.account != null)
			this.account = account;
	}

	public void defaultTrackPageview(String trackPageview) {
		if (this.trackPageview != null)
			this.trackPageview = trackPageview;
	}

	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		Deque<Object[]> items = new LinkedList<Object[]>(this.items);
		items.addFirst(new Object[] {TRACK_PAGEVIEW, trackPageview});
		items.addFirst(new Object[] {SET_ACCOUNT, account});
		for (Object[] a: items) {
			b.append("_gaq.push([");
			b.append(StringUtils.join(values(a), ", "));
			b.append("]);\n");
		}
		return b.toString();
	}

	private List<String> values(Object[] a) {
		List<String> result = new ArrayList<String>(a.length);
		for (Object i: a) {
			if (i == null)
				result.add("undef");
			else if (i instanceof Number || i instanceof Boolean)
				result.add(i.toString());
			else
				result.add("'" + i.toString() + "'");
		}
		return result;
	}
}
