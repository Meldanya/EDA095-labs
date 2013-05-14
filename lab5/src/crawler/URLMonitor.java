package crawler;

import java.util.LinkedList;
import java.util.Queue;

public class URLMonitor {
	private Queue<String> traversedURLs = new LinkedList<String>();
	private Queue<String> remainingURLs = new LinkedList<String>();
	
	public synchronized String getNext() {
		while (remainingURLs.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return remainingURLs.poll();
	}
	
	public synchronized void addRemaining(String s) {
		if (!traversedURLs.contains(s)) {
			remainingURLs.add(s);
			notifyAll();
		}
	}

	public synchronized void addTraversed(String s) {
		traversedURLs.add(s);
	}
	
	public synchronized int getNbrTraversed() {
		return traversedURLs.size();
	}

	public synchronized int getNbrRemaining() {
		return traversedURLs.size();
	}
	
	public synchronized Queue<String> getTraversed() {
		return traversedURLs;
	}
	
	public synchronized void setTraversed(Queue<String> trav) {
		this.traversedURLs = trav;
	}
	
	public synchronized Queue<String> getRemaining() {
		return remainingURLs;
	}
	
	public synchronized void setRemaining(Queue<String> rem) {
		this.remainingURLs = rem;
	}
}
