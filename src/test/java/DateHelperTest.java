import org.junit.Test;
import todoly.helper.DateHelper;
import todoly.model.Task;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;
import static todoly.helper.DateHelper.DATE_FORMAT;
import static todoly.helper.DateHelper.DATE_FORMATTER;

public class DateHelperTest {

    @Test
    public void shouldGetTheDueDateAsString() {
        Date date = new Date();
        String dateString = DATE_FORMATTER.format(date);
        Task task = new Task(21, "project test", "title test", date, true);
        assertEquals(dateString, DateHelper.getDueDateAsString(task));
    }

    @Test
    public void shouldMarkItAsPassedIfDateIsOlderThanNow() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        assertTrue(DateHelper.isDatePassed(calendar.getTime()));
    }

    @Test
    public void shouldNotMarkItAsPassedIfDateIsLaterThanNow() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        assertFalse(DateHelper.isDatePassed(calendar.getTime()));
    }

    @Test
    public void shouldConvertEnteredStringToDate() throws ParseException {
        String enteredDate = "09-02-2020";
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        Date date = dateFormat.parse(enteredDate);
        assertEquals(date, DateHelper.toDate(enteredDate));
    }

    @Test
    public void shouldBeNullWhenEnterAnInvalidDate() {
        assertNull(DateHelper.toDate("21212"));
    }
}
