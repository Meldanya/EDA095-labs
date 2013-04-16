package server;

import java.net.Socket;

public class ConnectionThread extends Thread {

	private Mailbox m;
	private Socket client;
	
	public ConnectionThread(Socket client, Mailbox m) {
		this.m = m;
		this.client = client;
	}

	public void run() {
		System.out.println("Client connected: " + client.getInetAddress());
		while(true) {
			System.out.println("bu");
		}
	}

}
