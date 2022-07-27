import java.util.ArrayList;
import java.util.HashMap;

public class TotalScheduler {
  public int numTasks;
  public ArrayList<WebScheduler> allTasks;
  public HashMap<String, Integer> students;
  public ArrayList<int[]> studentSchedules;

  public TotalScheduler(int numTasks, String[] taskInputFiles){
    numTasks = taskInputFiles.length;
    allTasks = new ArrayList<WebScheduler>();
    for(int i=0; i<numTasks; i++){
      WebScheduler s = new WebScheduler(taskInputFiles[i]);
      s.setup();
      allTasks.add(s);
    }
  }

  public void removeConflicts(){
    //TODO: implement time conflict resolution


    //build student schedules, removing conflicts as necessary
    int numStudents = studentSchedules.size();
    for(WebScheduler s : allTasks){
      for(WebAvailability w : s.availableStudentsStatic){
        int studentNum = students.get(w.name);

      }
    }
  }


}
