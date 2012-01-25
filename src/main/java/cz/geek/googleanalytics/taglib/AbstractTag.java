package cz.geek.googleanalytics.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * @author martin
 */
public class AbstractTag extends TagSupport {
	protected GAQ getGaq() {
		String name = GAQ.class.getName();
		GAQ gaq = (GAQ) pageContext.getRequest().getAttribute(name);
		if (gaq == null) {
			gaq = new GAQ();
			pageContext.getRequest().setAttribute(name, gaq);
		}
		return gaq;
	}

	protected void println(Object o) throws JspException {
		try {
			pageContext.getOut().println(o);
		} catch (IOException e) {
			throw new JspException(e);
		}
	}

	protected void print(Object o) throws JspException {
		try {
			pageContext.getOut().println(o);
		} catch (IOException e) {
			throw new JspException(e);
		}
	}
}
