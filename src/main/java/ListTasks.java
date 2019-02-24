import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class ListTasks {
    private List<Task> taskStore;

    public ListTasks(List<Task> taskStore) {

        this.taskStore = taskStore;
    }


    public void showTaskList() {

        System.out.println("TASK LIST: (ID-PROJECT-TITLE-DUE DATE-STATUS)");
        System.out.println("=============================================");
        if (taskStore.size()==0){
            System.out.println("There is no task to list");
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");


        for (int i = 0; i < taskStore.size(); i++) {
            Task currentTask = taskStore.get(i);
            String dueDate = dateFormat.format(currentTask.getDueDate());
            String status;
            if (currentTask.getIsDone()){
                 status = "DONE";
            }
            else{
                status = "TODO";
            }
            System.out.println(currentTask.getId() + " " + currentTask.getProject() + " " + currentTask.getTitle() + " " + dueDate + " " + status);
        }

    }
}