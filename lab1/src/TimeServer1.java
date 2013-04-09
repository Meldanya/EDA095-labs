import java.text.DateFormat;
import java.util.Date;


public class TimeServer1 {
	public static void main(String[] args) {
		if (args.length < 1) {
			System.err.println("Usage: java TimeServer1 time|date");
		}
		
		DateFormat formatter = null;
		Date d = new Date();
		if (args[0].equals("time")) {
			formatter = DateFormat.getTimeInstance();
		} else if (args[0].equals("date")) {
			formatter = DateFormat.getDateInstance();
		} else {
			System.err.println("FAIL!");
			System.exit(1);
		}

		System.out.println(formatter.format(d));
	}
}
