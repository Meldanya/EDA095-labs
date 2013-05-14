package crawler;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Queue;

import javax.swing.text.html.HTMLEditorKit;


public class Processor extends Thread {
	private URLMonitor mon;
	Spider s;
	private String baseURL;

	public Processor(URLMonitor mon, String baseURL) {
		this.mon = mon;
		this.baseURL = baseURL;
		s = new Spider(mon, baseURL);
	}

	public void run() {
		ParserGetter kit = new ParserGetter();
		HTMLEditorKit.Parser parser = kit.getParser();

		while (mon.getNbrTraversed() < 2000) {
			try {
				String href = mon.getNext();
				URL url = null;
				if (href.startsWith("mailto")) {
					mon.addTraversed(href);
					continue;
				} else if (href.startsWith("http")) {
					url = new URL(href);
				} else {
					url = new URL(new URL(baseURL), href);
				}

				InputStream in = new BufferedInputStream(url.openStream());
				InputStreamReader r = new InputStreamReader(in);
				parser.parse(r, s, true);
				s.add(href, true);
			} catch (IOException ex) {
				System.err.println(ex.getMessage());
			}
		}

	}
	
	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}
}
