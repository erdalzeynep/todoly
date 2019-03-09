package todoly.model;

import todoly.helper.DateHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task implements java.io.Serializable, Comparable<Task> {
    private static final long serialVersionUID = 4264367449031469962L;
    private Integer id;
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

    public String getDueDateAsString() {
        DateFormat DATE_FORMATTER = new SimpleDateFormat(DateHelper.DATE_FORMAT);
        String dueDate = DATE_FORMATTER.format(this.getDueDate());
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

    public boolean getIsDoneBoolean() {
        return isDone;
    }

    public String getIsDoneString() {
        String status = this.getIsDoneBoolean() ? "DONE" : "TODO";
        return status;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setId(Integer id) {
        this.id = id;
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

        DateFormat dateFormat = new SimpleDateFormat(DateHelper.DATE_FORMAT);
        String dueDate = dateFormat.format(this.getDueDate());

        return this.getId() + " " + this.getProject() + " " + this.getTitle() + " " + dueDate + " " + getIsDoneString();
    }

    @Override
    public int compareTo(Task o) {
        if (getDueDate() == null || o.getDueDate() == null) {
            return 0;
        }
        return getDueDate().compareTo(o.getDueDate());
    }
}
