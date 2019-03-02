package todoly.helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {
    public static final String DATE_FORMAT = "dd-MM-yyyy";

    public static Date toDate(String enteredDate) {
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        dateFormat.setLenient(false);
        Date date;
        try {
            date = dateFormat.parse(enteredDate);
        } catch (ParseException e) {
            System.out.println("Date format is wrong! Try again..");
            return null;
        }
        return date;
    }

    public static boolean isDatePassed(Date inputDate) {
        Date dateOfToday = new Date();
        if (inputDate.compareTo(dateOfToday) < 0) {
            return true;
        } else {
            return false;
        }
    }
    public static void datePassedMessage(){
        System.out.println("Operation is not successful, Due Date should not be a passed date");
    }
}
