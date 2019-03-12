import org.junit.Test;
import todoly.helper.TaskHelper;
import todoly.model.Task;

import java.util.Date;

import static junit.framework.TestCase.assertEquals;

public class TaskHelperTest {

    @Test
    public void shouldStatusBeDoneIfIsDoneIsTrue() {
        Task task = new Task(1, "project", "title", new Date(), true);
        assertEquals("DONE", TaskHelper.getIsDoneAsString(task));
    }

    @Test
    public void shouldStatusBeToDoIfIsDoneIsFalse() {
        Task task = new Task(1, "project", "title", new Date(), false);
        assertEquals("TODO", TaskHelper.getIsDoneAsString(task));
    }
}
