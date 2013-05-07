package parser;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;

public class LinkGetter extends HTMLEditorKit.ParserCallback {
	private String baseURL;
	private ArrayList<String> links;
	private HashSet<String> urls;
	private HashSet<String> mailto;
	private HashSet<String> visited;

	public LinkGetter(String baseURL) {
		this.baseURL = baseURL;
		this.links = new ArrayList<String>();
		this.urls = new HashSet<String>();
		this.mailto = new HashSet<String>();
		this.visited = new HashSet<String>();
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
			links.add(href);
			if (href.contains("mailto")) {
				mailto.add(href);
			} else {
				if (href.startsWith("http")) {
					urls.add(href);
				} else {
					urls.add(baseURL + href);
				}
			}
		}
	}

	public void handleSimpleTag(HTML.Tag t, MutableAttributeSet a, int pos) {
		String href = null;
		if (t == HTML.Tag.BASE) {
			href = (String) a.getAttribute(HTML.Attribute.HREF);
			baseURL = href;
		}
		if (t == HTML.Tag.IMG) {
			href = (String) a.getAttribute(HTML.Attribute.SRC);
		}
		if (href != null) {
			links.add(href);
			if (href.startsWith("http")) {
				urls.add(href);
			} else {
				urls.add(baseURL + href);
			}
		}
	}
	
	public void run() {
		links.add(baseURL);
		ParserGetter kit = new ParserGetter();
		HTMLEditorKit.Parser parser = kit.getParser();

		while (!links.isEmpty() && mailto.size() + urls.size() <= 2000) {
			try {
				String href = links.remove(0);
				URL url = null; 
				if (visited.contains(href) || href.startsWith("mailto")) {
					continue;
				} else if (href.startsWith("http")) {
					url = new URL(href);
				} else {
					url = new URL(new URL(baseURL), href);
				}

				InputStream in = new BufferedInputStream(url.openStream());
				InputStreamReader r = new InputStreamReader(in);
				parser.parse(r, this, true);
				visited.add(href);
			} catch (IOException ex) {
				System.err.println(ex.getMessage());
			}
		}

		System.out.println();System.out.println();
		for (String s : mailto)
			System.out.println(s);
		System.out.println();
		for (String s : urls) {
			System.out.println(s);
		}
	}

	public static void main(String[] args) {
		LinkGetter l = new LinkGetter("http://cs.lth.se/eda095/");
		l.run();
	}
}
