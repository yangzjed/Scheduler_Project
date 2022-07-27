import java.util.ArrayList;
import java.util.HashMap;

public class TotalScheduler {
  public ArrayList<WebScheduler> allTasks;
  public HashMap<String, Integer> students;
  public ArrayList<int[]> studentSchedules;

  public TotalScheduler(int numTasks, String[] taskInputFiles){
    allTasks = new ArrayList<WebScheduler>();
    for(int i=0; i<numTasks; i++){
      WebScheduler s = new WebScheduler(taskInputFiles[i]);
      s.setup();
      allTasks.add(s);
    }
  }

  public void removeConflicts(){
    //TODO: implement time conflict resolution
    for(WebScheduler s : allTasks){

    }
  }


}
