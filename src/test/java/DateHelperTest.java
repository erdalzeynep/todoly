import org.junit.Test;
import todoly.helper.DateHelper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;
import static todoly.helper.DateHelper.DATE_FORMAT;

public class DateHelperTest {

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
    public void shouldBeNullWhenEnterAnInvalidDate(){
        assertNull(DateHelper.toDate("21212"));
    }
}
