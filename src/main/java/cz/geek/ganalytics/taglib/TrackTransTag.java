package cz.geek.ganalytics.taglib;

import javax.servlet.jsp.JspException;

/**
 * Sends both the transaction and item data to the Google Analytics server. This method should be called after _trackPageview(),
 * and used in conjunction with the _addItem() and addTrans() methods.
 * It should be called after items and transaction elements have been set up.
 */
public class TrackTransTag extends AbstractTag {
	
	@Override
	public int doStartTag() throws JspException {
		getGaq().add("_trackTrans");
		return super.doStartTag();
	}
}
