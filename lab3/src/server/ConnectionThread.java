package server;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ConnectionThread extends Thread {

	private Mailbox m;
	private Socket client;
	private ArrayList<ConnectionThread> tp;

	public ConnectionThread(Socket client, Mailbox m,
			ArrayList<ConnectionThread> tp) {
		this.m = m;
		this.client = client;
		this.tp = tp;
	}

	public void run() {
		System.out.println("Client connected: " + client.getInetAddress());
		while (true) {
			String input = new String();
			char ch;
			try {
				do {
					ch = (char) client.getInputStream().read();
					input = input + ch;
				} while (ch != '\n');

				if (input.length() < 3 && input.charAt(0) == 'Q') {
					client.close();
					break;
				}

				String cmd = input.substring(0, 3);
				input = input.substring(3);
				if (cmd.equals("E: ")) {
					sendMsg(input);
				} else if (cmd.equals("M: ")) {
					m.deposit(input);
				} else {
					sendMsg("Unknown Command\n");
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		tp.remove(this);
	}

	public void sendMsg(String msg) {
		try {
			client.getOutputStream().write(msg.getBytes(), 0, msg.length());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
