package dct_attendance_app;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Five_Percent_Student extends Attendance_Roster {
	
	String html = "10% absent threshold:";
			Document doc = Jsoup.parse(html);

	Five_Percent_Student(String name, String hoursAttended, String hoursMissed, String percentAttended) {
		super(name, hoursAttended, hoursMissed, percentAttended);
		// TODO Auto-generated constructor stub
		
	}

}
