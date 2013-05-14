package crawler;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;

public class Spider extends HTMLEditorKit.ParserCallback {
	private URLMonitor mon;
	private String baseURL;

	public Spider(URLMonitor mon, String baseUrl) {
		this.mon = mon;
		this.baseURL = baseUrl;
	}

	public void handleStartTag(HTML.Tag tag, MutableAttributeSet a, int position) {
		String href = null;
		if (tag == HTML.Tag.A) {
			href = (String) a.getAttribute(HTML.Attribute.HREF);
		}
		if (tag == HTML.Tag.FRAME) {
			href = (String) a.getAttribute(HTML.Attribute.SRC);
		}
		add(href, false);
	}

	public void handleSimpleTag(HTML.Tag t, MutableAttributeSet a, int pos) {
		String href = null;
		if (t == HTML.Tag.BASE) {
			href = (String) a.getAttribute(HTML.Attribute.HREF);
			baseURL = href;
		}
		if (t == HTML.Tag.IMG) {
			href = (String) a.getAttribute(HTML.Attribute.SRC);
			add(href, true);
		}
	}

	public void add(String href, boolean traversed) {
		if (href != null) {
			if (href.startsWith("http")) {
				if (traversed) {
					mon.addTraversed(href);
				} else {
					mon.addRemaining(href);
				}
			} else {
				if (traversed) {
					mon.addTraversed(baseURL + href);
				} else {
					mon.addRemaining(baseURL + href);
				}
			}
		}
	}
}
