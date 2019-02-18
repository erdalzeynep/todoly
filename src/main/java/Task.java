import java.util.Date;

public class Task {
    private Integer id;
    private String project;
    private String title;
    private String dueDate;
    private String status;

    public Task(Integer id, String project, String title, String dueDate, String status) {
        this.id = id;
        this.project = project;
        this.title= title;
        this.dueDate=dueDate;
        this.status=status;


    }

    public Task() {
        this.id = null;
        this.project = null;
        this.title= null;
        this.dueDate=null;
        this.status=null;


    }

    public String getDueDate() {
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

    public void setDueDate(String dueDate) {
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



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
