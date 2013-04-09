import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;


public class DatePrinter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Date d = new Date();
		DateFormat format = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.FRENCH);
		System.out.println(format.format(d));
	}

}
