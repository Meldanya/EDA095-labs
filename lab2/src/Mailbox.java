import java.util.ArrayList;


public class Mailbox {

	private ArrayList<String> list;
	
	public Mailbox(){
		list = new ArrayList<String>();
	}

		
	public synchronized void deposit(String name) {
		list.add(name);
		notifyAll();
	}
	
	public synchronized String withdraw() throws InterruptedException {
		while(list.isEmpty()) wait();
		String tmp = list.remove(0);
		notifyAll();
		return tmp;
	}
}
