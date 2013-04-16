
public class MailboxDepositThread extends Thread {
	private String name;
	private Mailbox m;

	public MailboxDepositThread(String name, Mailbox m) {
		this.name = name;		
		this.m = m;
	}

	public void run() {
		for (int i = 0; i < 5; i++) {
			try {
				m.deposit(name);
				sleep((long) (Math.random() * 100));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Mailbox m = new Mailbox();
		for (int i = 0; i < 10; i++) {
			new MailboxDepositThread(Integer.toString(i), m).start();
		}
		new MailboxWithdrawThread(m).start();
	}
}
