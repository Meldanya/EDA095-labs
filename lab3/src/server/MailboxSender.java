package server;

import java.util.ArrayList;

public class MailboxSender extends Thread {
	private ArrayList<ConnectionThread> tp;
	private Mailbox m;

	public MailboxSender(ArrayList<ConnectionThread> tp, Mailbox m) {
		this.tp = tp;
		this.m = m;
	}

	public void run() {
		while (true) {
			String msg;
			try {
				msg = m.withdraw();
				for (ConnectionThread c : tp) {
					c.sendMsg(msg);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}