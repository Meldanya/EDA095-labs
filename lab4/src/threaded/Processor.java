package threaded;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import javax.swing.text.html.HTMLEditorKit;

import parser.ParserGetter;

public class Processor extends Thread {
	private URLMonitor mon;
	Spider s;
	private String baseURL;

	public Processor(URLMonitor mon) {
		this.mon = mon;
		s = new Spider(this, mon);
	}

	public void run() {
		ParserGetter kit = new ParserGetter();
		HTMLEditorKit.Parser parser = kit.getParser();

		while (mon.getNbrTraversed() < 2000) {
			try {
				String href = mon.getNext();
				URL url = null;
				if (href.startsWith("mailto")) {
					continue;
				} else if (href.startsWith("http")) {
					url = new URL(href);
				} else {
					url = new URL(new URL(baseURL), href);
				}

				InputStream in = new BufferedInputStream(url.openStream());
				InputStreamReader r = new InputStreamReader(in);
				parser.parse(r, s, true);
				mon.addTraversed(href);
			} catch (IOException ex) {
				System.err.println(ex.getMessage());
			}
		}

//		System.out.println();
//		System.out.println();
//		for (String s : mailto)
//			System.out.println(s);
//		System.out.println();
//		for (String s : urls) {
//			System.out.println(s);
//		}
	}
	
	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}
}
