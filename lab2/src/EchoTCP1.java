import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoTCP1 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ServerSocket ss = new ServerSocket(1337);
		while (true) {
			Socket client = ss.accept();
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
		} 
	}

}
