package threaded;

import java.util.ArrayList;
import java.util.Queue;

public class Main {
	public static void main(String[] args) {
		URLMonitor mon = new URLMonitor();
		mon.addRemaining("http://cs.lth.se/eda095/");

		ArrayList<Processor> processors = new ArrayList<Processor>();
		for (int i = 0; i < 10; i++) {
			Processor p = new Processor(mon);
			processors.add(p);
			p.start();
		}
		for (Processor p : processors) {
			try {
				p.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Queue<String> links = mon.getTraversed();
		for (String s : links) {
			System.out.println(s);
		}
	}
}
