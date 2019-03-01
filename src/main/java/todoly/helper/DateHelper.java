package todoly.helper;

import todoly.model.Task;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateHelper {
    public static Date toDate(String enteredDate) {
        DateFormat dateFormat = new SimpleDateFormat(Task.DATE_FORMAT);
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
        }
        else {
            return false;
        }
    }
}
