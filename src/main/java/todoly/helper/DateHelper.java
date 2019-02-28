package todoly.helper;

import todoly.model.Task;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {
    public static Date toDate(String enteredDate) {
        DateFormat dueDateFormat = new SimpleDateFormat(Task.DATE_FORMAT);
        Date date;
        try {
            date = dueDateFormat.parse(enteredDate);

        } catch (ParseException e) {
            System.out.println("Date format is wrong! Try again..");
            return null;
        }
        return date;

    }
}
