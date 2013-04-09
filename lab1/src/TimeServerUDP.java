import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.text.DateFormat;
import java.util.Date;


public class TimeServerUDP {
	public static void main(String[] args) throws IOException {
		byte[] buf = new byte[256];
		DatagramSocket socket = new DatagramSocket(30000);
		DatagramPacket pkt = new DatagramPacket(buf, buf.length);
		while (true) {
			socket.receive(pkt);
			String cmd = new String(pkt.getData(), 0, pkt.getLength());

			DateFormat formatter = null;
			if (cmd.equals("time")) {
				formatter = DateFormat.getTimeInstance();
			} else if (cmd.equals("date")) {
				formatter = DateFormat.getDateInstance();
			} else {
				System.err.println("FAIL!");
				continue;
			}

			Date d = new Date();
			String response = formatter.format(d);
			DatagramPacket sendPkt = new DatagramPacket(response.getBytes(), response.length(), pkt.getAddress(), pkt.getPort());

			socket.send(sendPkt);
		}

	}
}
