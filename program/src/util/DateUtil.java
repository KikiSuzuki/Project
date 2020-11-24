package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public Date FromStringToDate(String dateString) {
		// TODO Auto-generated method stub
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	    Date convertedDate = new Date();
	    try {
	        convertedDate = dateFormat.parse(dateString);
	    } catch (ParseException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	   return convertedDate;
	}
	
	public String FromDateToString(Date date) {
		DateFormat fmt = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		return fmt.format(date);
	}
	
	public String FromStringToString(String string) {
		DateFormat fmt = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		return fmt.format(FromStringToDate(string));
	}

	public static java.sql.Date convertFromJAVADateToSQLDate(Date javaDate) {
	    java.sql.Date sqlDate = new java.sql.Date(javaDate.getTime());
	    return sqlDate;
	}
}
