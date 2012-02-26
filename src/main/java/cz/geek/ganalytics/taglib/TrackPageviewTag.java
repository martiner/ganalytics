package cz.geek.ganalytics.taglib;

import org.apache.commons.lang.StringUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;

public class TrackPageviewTag extends AbstractTag {

	private String pageURL;

	public String getPageURL() {
		return pageURL;
	}

	public void setPageURL(String pageURL) {
		this.pageURL = pageURL;
	}

	@Override
	public int doStartTag() throws JspException {
		if (StringUtils.isEmpty(pageURL))
			getGaq().add("_trackPageview");
		else
			getGaq().add("_trackPageview", pageURL);
		return Tag.SKIP_BODY;
	}
}
