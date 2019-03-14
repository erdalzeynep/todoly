import org.junit.Before;
import org.junit.Test;
import todoly.App;
import todoly.helper.DateHelper;
import todoly.helper.TaskHelper;
import todoly.model.Task;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
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
    public void shouldAddAndPersistTaskWithValidInput() throws IOException {
        String projectName = "Test Project";
        String title = "Test Title";
        String dueDate = "02-02-2050";

        assertEquals(0, app.taskStore.size());

        addTask(projectName, title, dueDate);

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

    @Test
    public void shouldUpdateAndPersistTaskWithValidInput() throws IOException {
        String projectName = "Test Project";
        String title = "Test Title";
        String dueDate = "02-02-2050";
        addTask(projectName, title, dueDate);

        Task task = app.taskStore.get(0);
        String taskId = task.getId().toString();
        String newProjectName = "Updated Test Project";
        String newTitle = "Updated Test Title";
        String newDueDate = "03-03-2053";
        String input2 = 3 + "\n" + 1 + "\n" + taskId + "\n" + newProjectName + "\n" + newTitle + "\n" + newDueDate + "\n" + 4 + "\n";
        System.setIn(new ByteArrayInputStream(input2.getBytes()));
        app.startApp();

        assertEquals(newProjectName, task.getProject());
        assertEquals(newTitle, task.getTitle());
        assertEquals(newDueDate, DateHelper.getDueDateAsString(task));
    }

    private void addTask(String projectName, String title, String dueDate) throws IOException {
        String input = 2 + "\n" + projectName + "\n" + title + "\n" + dueDate + "\n" + 4 + "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        app.startApp();
    }

    private File getResourceFile() {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(Objects.requireNonNull(classLoader.getResource(RESOURCE_FILE)).getFile());
    }
}
