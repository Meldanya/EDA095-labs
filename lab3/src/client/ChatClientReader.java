package client;

import java.io.IOException;
import java.net.Socket;

public class ChatClientReader extends Thread {
	private Socket s;

	public ChatClientReader(Socket s) {
		this.s = s;
	}

	public void run() {
		while (!isInterrupted()) {
			String input = new String();
			char ch;
			try {
				do {
					ch = (char) s.getInputStream().read();
					input = input + ch;
				} while (ch != '\n' && !isInterrupted());
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (!isInterrupted())
				System.out.print(input);
		}
	}
}
