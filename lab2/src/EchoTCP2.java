import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoTCP2 {
	private ServerSocket serverSocket;

	public static void main(String[] args) throws IOException {
		new EchoTCP2().run();
	}

	public EchoTCP2() throws IOException {
		serverSocket = new ServerSocket(1337);
	}

	public void run() throws IOException {
		while (true) {
			Socket client = serverSocket.accept();

			new AnswerThread(client).start();
		}
	}

	private class AnswerThread extends Thread {
		private Socket client;

		public AnswerThread(Socket client) {
			this.client = client;
		}

		public void run() {
			try {
				System.out.println(client.getInetAddress());
				while (true) {
					String input = new String();
					char ch;
					do {
						ch = (char) client.getInputStream().read();
						input = input + ch;
					} while (ch != '\n');

					if (input.contains("quit")) {
						client.close();
						break;
					}

					client.getOutputStream().write(input.getBytes());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
