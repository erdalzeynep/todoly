import java.util.Date;

public class Task implements java.io.Serializable {
    private static final long serialVersionUID = 4264367449031469962L;
    private Integer id;
    private String project;
    private String title;
    private Date dueDate;
    private boolean isDone;

    public Task() {
        this.id = null;
        this.project = null;
        this.title = null;
        this.dueDate = null;
        this.isDone = false;
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

    public boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }
}

