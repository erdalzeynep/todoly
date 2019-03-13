import org.junit.Before;
import org.junit.Test;
import todoly.App;
import todoly.helper.DateHelper;
import todoly.helper.TaskHelper;
import todoly.model.Task;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertEquals;

public class AppTest {

    private App app = new App();
    private static final String RESOURCE_FILE = "tasklist.ser";

    @Before
    public void setUp() {
        app.taskStore.clear();
    }

    @Test
    public void shouldAddAndPersistTaskWithValidInput() {
        String projectName = "Test Project";
        String title = "Test Title";
        String dueDate = "02-02-2050";
        String input = 2 + "\n" + projectName + "\n" + title + "\n" + dueDate + "\n" + 4 + "\n";

        System.setIn(new ByteArrayInputStream(input.getBytes()));

        assertEquals(0, app.taskStore.size());
        app.startApp();
        assertEquals(1, app.taskStore.size());
        Task task = app.taskStore.get(0);
        assertEquals(projectName, task.getProject());
        assertEquals(title, task.getTitle());
        assertEquals(DateHelper.toDate(dueDate), task.getDueDate());

        List<Task> persistedTasks = TaskHelper.readTasksFromFile(getResourceFile());
        assertEquals(1, persistedTasks.size());
        Task persistedTask = persistedTasks.get(0);
        assertEquals(projectName, persistedTask.getProject());
        assertEquals(title, persistedTask.getTitle());
        assertEquals(DateHelper.toDate(dueDate), persistedTask.getDueDate());
    }

    private File getResourceFile() {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(Objects.requireNonNull(classLoader.getResource(RESOURCE_FILE)).getFile());
    }
}
