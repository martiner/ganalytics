package cz.geek.ganalytics.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;

/**
 * http://code.google.com/apis/analytics/docs/tracking/asyncUsageGuide.html
 */
public class TrackTag extends AbstractTag {

	private String account;

	private String trackPageview;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getTrackPageview() {
		return trackPageview;
	}

	public void setTrackPageview(String trackPageview) {
		this.trackPageview = trackPageview;
	}

	@Override
	public int doStartTag() throws JspException {
		println("<script type=\"text/javascript\">");
		println("var _gaq = _gaq || [];");
		getGaq().defaultAccount(account);
		getGaq().defaultTrackPageview(trackPageview);
		println(getGaq());
		return Tag.EVAL_BODY_INCLUDE;
	}

	@Override
	public int doEndTag() throws JspException {
		println("(function() {");
		println("var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;");
		println("ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';");
		println("var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);");
		println("})();");
		println("</script>");
		return Tag.EVAL_PAGE;
	}
}
