package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ServerSocket serverSocket = new ServerSocket(1337);
		Mailbox m = new Mailbox();
		ArrayList<ConnectionThread> threadPool = new ArrayList<ConnectionThread>();

		MailboxSender ms = new MailboxSender(threadPool, m);
		ms.start();

		while (true) {
			Socket client = serverSocket.accept();
			ConnectionThread c = new ConnectionThread(client, m, threadPool);
			threadPool.add(c);
			c.start();
		}
	}
}
