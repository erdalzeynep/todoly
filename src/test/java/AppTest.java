import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import todoly.App;
import todoly.helper.DateHelper;
import todoly.helper.TaskHelper;
import todoly.model.Task;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class AppTest {

    private File tempFile;
    private App app;

    /**
     * The TemporaryFolder Rule creates files and folders that should
     * be deleted when the test method finishes
     */
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    @Before
    public void setUp() throws IOException {
        tempFile = testFolder.newFile("temp_data_file");
        app = new App(tempFile);
    }

    @Test
    public void shouldAddAndPersistTaskWithValidInput() {
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

        List<Task> persistedTasks = TaskHelper.readTasksFromFile(tempFile);
        assertEquals(1, persistedTasks.size());
        Task persistedTask = persistedTasks.get(0);
        assertEquals(projectName, persistedTask.getProject());
        assertEquals(title, persistedTask.getTitle());
        assertEquals(DateHelper.toDate(dueDate), persistedTask.getDueDate());
    }

    @Test
    public void shouldUpdateAndPersistTaskWithValidInput() {
        String projectName = "Test Project";
        String title = "Test Title";
        String dueDate = "02-02-2050";
        addTask(projectName, title, dueDate);

        Task task = app.taskStore.get(0);
        String taskId = task.getId().toString();
        String newProjectName = "Updated Test Project";
        String newTitle = "Updated Test Title";
        String newDueDate = "03-03-2053";
        String input = 3 + "\n" + 1 + "\n" + taskId + "\n" + newProjectName + "\n" + newTitle + "\n" + newDueDate + "\n" + 4 + "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        app.startApp();

        assertEquals(newProjectName, task.getProject());
        assertEquals(newTitle, task.getTitle());
        assertEquals(newDueDate, DateHelper.getDueDateAsString(task));
    }

    @Test
    public void shouldMarkTaskAsDone() {
        String projectName = "Test Project";
        String title = "Test Title";
        String dueDate = "02-02-2050";
        addTask(projectName, title, dueDate);
        Task task = app.taskStore.get(0);

        assertFalse(task.getIsDone());

        /**
         * input variable simulates entering 3 in main menu, than entering 2 option(which is mark as done option)
         * and entering task id and quiting the app with 4
         */
        String input = 3 + "\n" + 2 + "\n" + task.getId() + "\n" + 4 + "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        app.startApp();

        assertTrue(task.getIsDone());
    }

    @Test
    public void shouldDeleteTask() {
        String projectName = "Test Project";
        String title = "Test Title";
        String dueDate = "02-02-2050";
        addTask(projectName, title, dueDate);
        Task task = app.taskStore.get(0);
        assertEquals(1, app.taskStore.size());
        List<Task> persistedTasks = TaskHelper.readTasksFromFile(tempFile);
        assertEquals(1, persistedTasks.size());

        String input = 3 + "\n" + 3 + "\n" + task.getId() + "\n" + 4 + "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        app.startApp();
        persistedTasks = TaskHelper.readTasksFromFile(tempFile);
        assertEquals(0, app.taskStore.size());
        assertEquals(0, persistedTasks.size());

    }

    private void addTask(String projectName, String title, String dueDate) {
        /**
         * input variable simulates entering 2 in main menu, than entering task details and quiting the app with 4
         */
        String input = 2 + "\n" + projectName + "\n" + title + "\n" + dueDate + "\n" + 4 + "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        app.startApp();
    }
}
