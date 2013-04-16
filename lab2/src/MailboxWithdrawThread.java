import java.util.ArrayList;


public class MailboxWithdrawThread extends Thread {
	private Mailbox m;

	public MailboxWithdrawThread(Mailbox m) {
		this.m = m;
	}

	public void run() {
		while(true) {
			try {
				System.out.println(m.withdraw());
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				
				sleep((long) (Math.random() * 100));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
