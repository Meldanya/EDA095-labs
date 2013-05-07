package threaded;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;

public class Spider extends HTMLEditorKit.ParserCallback {
	private URLMonitor mon;
	private Processor p;

	public Spider(Processor p, URLMonitor mon) {
		this.p = p;
		this.mon = mon;
	}

	public void handleStartTag(HTML.Tag tag, MutableAttributeSet a, int position) {
		String href = null;
		if (tag == HTML.Tag.A) {
			href = (String) a.getAttribute(HTML.Attribute.HREF);
		}
		if (tag == HTML.Tag.FRAME) {
			href = (String) a.getAttribute(HTML.Attribute.SRC);
		}
		if (href != null) {
			mon.addRemaining(href);
		}
	}

	public void handleSimpleTag(HTML.Tag t, MutableAttributeSet a, int pos) {
		String href = null;
		if (t == HTML.Tag.BASE) {
			href = (String) a.getAttribute(HTML.Attribute.HREF);
			p.setBaseURL(href);
		}
		if (t == HTML.Tag.IMG) {
			href = (String) a.getAttribute(HTML.Attribute.SRC);
		}
		if (href != null) {
			mon.addRemaining(href);
		}
	}
}
