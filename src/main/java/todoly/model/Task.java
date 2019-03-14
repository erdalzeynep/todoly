package todoly.model;

import java.util.Date;

import static todoly.helper.DateHelper.getDueDateAsString;
import static todoly.helper.TaskHelper.getIsDoneAsString;

public class Task implements java.io.Serializable, Comparable<Task> {
    private static final long serialVersionUID = 4264367449031469962L;
    private final Integer id;
    private String project;
    private String title;
    private Date dueDate;
    private boolean isDone;

    public Task(Integer id, String project, String title, Date dueDate, boolean isDone) {
        this.id = id;
        this.project = project;
        this.title = title;
        this.dueDate = dueDate;
        this.isDone = isDone;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public String getProject() {
        return project;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {

        return this.getId() + " " + this.getProject() + " " + this.getTitle() + " " + getDueDateAsString(this) + " " + getIsDoneAsString(this);
    }

    @Override
    public int compareTo(Task o) {
        if (getDueDate() == null || o.getDueDate() == null) {
            return 0;
        }
        return getDueDate().compareTo(o.getDueDate());
    }
}
