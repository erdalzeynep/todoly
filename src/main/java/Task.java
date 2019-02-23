public class Task implements java.io.Serializable {
    private Integer id;
    private String project;
    private String title;
    private String dueDate;
    private String status;

    public Task() {
        this.id = null;
        this.project = null;
        this.title = null;
        this.dueDate = null;
        this.status = null;
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

//    @Override
//    public String toString(){
//        return id + "=======" + title;
//    }
//
//    @Override
//    public int compareTo(){
//        if(date.copareTo(date) == 0 )
//            compareTo(title)
//    }


}
