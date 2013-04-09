import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.SocketException;
import java.text.DateFormat;
import java.util.Date;


public class UDPSend {
	public static void main(String[] args) throws NumberFormatException, IOException {
		if (args.length < 3) {
			System.err.println("Usage: java SendUDP machine port command");
		}

		String machine = args[0];
		int port = Integer.parseInt(args[1]);
		String cmd = args[2];

		DatagramSocket socket = new DatagramSocket();
		DatagramPacket send = new DatagramPacket(cmd.getBytes(), cmd.length(), InetAddress.getByName(machine), port);
		byte[] recvBuf = new byte[256];
		DatagramPacket recv = new DatagramPacket(recvBuf, recvBuf.length);

		socket.send(send);
		socket.receive(recv);
		System.out.println(new String(recv.getData()));
	}
}
