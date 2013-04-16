
public class UselessThread extends Thread {
	private String name;

	public UselessThread(String name) {
		this.name = name;
	}

	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println(name);
			try {
				
				sleep((long) (Math.random() * 100));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new UselessThread(Integer.toString(i)).start();
		}
	}
}
