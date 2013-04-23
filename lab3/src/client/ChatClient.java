package client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClient {

	public static void main(String[] args) throws UnknownHostException,
			IOException {
		if (args.length < 2) {
			System.err.println("Usage: java ChatClient machine port");
		}

		String host = args[0];
		int port = Integer.parseInt(args[1]);

		Socket s = new Socket(host, port);
		ChatClientReader ccr = new ChatClientReader(s);
		ccr.start();

		while (true) {
			String input = new String();
			char ch;
			try {
				do {
					ch = (char) System.in.read();
					input = input + ch;
				} while (ch != '\n');

				s.getOutputStream().write(input.getBytes(), 0, input.length());
				if (input.length() < 3 && input.charAt(0) == 'Q') {
					ccr.interrupt();
					break;
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

}
