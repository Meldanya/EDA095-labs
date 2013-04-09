import java.net.*;
import java.io.*;

public class UDPSend2 {

	public static void main(String args[]) throws IOException {
		if (args.length < 2) {
			System.err.println("Usage: java SendUDP port command");
		}

		int port = Integer.parseInt(args[0]);
		String cmd = args[1];

		MulticastSocket ms = new MulticastSocket();
		ms.setTimeToLive(1);
		InetAddress ia = InetAddress.getByName("experiment.mcast.net");

		byte[] recvBuf = new byte[1024];
		String hello = "hello";
		ms.send(new DatagramPacket(hello.getBytes(), hello.length(), ia, 5000));

		DatagramPacket recvPkt = new DatagramPacket(recvBuf, recvBuf.length);
		ms.receive(recvPkt);
		String host = new String(recvPkt.getData(), 0, recvPkt.getLength());

		System.out.println("Sending cmd to: " + host);
		byte[] buf = cmd.getBytes();
		DatagramSocket s = new DatagramSocket();
		DatagramPacket dp = new DatagramPacket(buf, buf.length,
				InetAddress.getByName(host), port);
		s.send(dp);

		s.receive(recvPkt);
		System.out.println(new String(recvPkt.getData(), 0, recvPkt.getLength()));
	}

}
