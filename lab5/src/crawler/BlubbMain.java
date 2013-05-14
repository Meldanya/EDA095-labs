package crawler;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BlubbMain implements Blubb {
	private URLMonitor mon;
	private ArrayList<Processor> processors;

	public BlubbMain() {
		this.mon = new URLMonitor();
		mon.addRemaining("http://cs.lth.se/eda095");

		processors = new ArrayList<Processor>();
	}

	public void startCrawling() {
		for (int i = 0; i < 10; i++) {
			Processor p = new Processor(mon, "http://cs.lth.se/eda095");
			processors.add(p);
			p.start();
		}
	}

	public static void main(String[] args) {
		try {
			BlubbMain m = new BlubbMain();
			Blubb stub = (Blubb) UnicastRemoteObject.exportObject(m, 0);

			Registry registry = LocateRegistry.getRegistry();
			registry.bind("Blubb", stub);

			System.out.println("Server ready");
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}

	}

	@Override
	public List<String>[] fetch() {
		@SuppressWarnings("unchecked")
		List<String>[] ret = (List<String>[]) new List[2];
		ret[0] = (LinkedList<String>) mon.getTraversed();
		ret[1] = (LinkedList<String>) mon.getRemaining();

		return ret;
	}

	@Override
	public List<String>[] fetchAndSet(List<String> trav, List<String> rem) {
		List<String>[] ret = fetch();
		mon.setRemaining((Queue<String>) rem);
		mon.setTraversed((Queue<String>) trav);

		return ret;
	}

}
