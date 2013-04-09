import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;


public class TimeServer2 {
	public static void main(String[] args) throws IOException {
		byte[] buf = new byte[5];
		while (true) {
			System.in.read(buf);
			String cmd = new String(buf, 0, buf.length - 1);

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
			System.out.println(formatter.format(d));
		}
		
	}
}
