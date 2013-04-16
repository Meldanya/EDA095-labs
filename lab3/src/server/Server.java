package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ServerSocket serverSocket = new ServerSocket(1337);
		Mailbox m = new Mailbox();
		while(true) {
			Socket client = serverSocket.accept();
			new ConnectionThread(client, m).start();
		}
	}
}
