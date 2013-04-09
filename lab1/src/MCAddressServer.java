import java.net.*;
import java.io.*;

public class MCAddressServer {

	public static void main(String args[]) {
		try {
			InetAddress local = InetAddress.getLocalHost();
			String hostName = local.getHostAddress();
			MulticastSocket ms = new MulticastSocket(5000);
			InetAddress ia = InetAddress.getByName("experiment.mcast.net");
			ms.joinGroup(ia);
			while (true) {
				byte[] buf = new byte[65536];
				DatagramPacket dp = new DatagramPacket(buf, buf.length);
				ms.receive(dp);
				String s = new String(dp.getData(), 0, dp.getLength());
				System.out.println("Received: " + s);
				
				DatagramPacket sendPkt = new DatagramPacket(hostName.getBytes(), hostName.length(), dp.getAddress(), dp.getPort());
				ms.send(sendPkt);
			}
		} catch (IOException e) {
			System.out.println("Exception:" + e);
		}
	}

}
