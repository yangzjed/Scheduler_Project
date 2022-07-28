import java.util.ArrayList;
import java.util.HashMap;

public class TotalScheduler {
  public int numTasks;
  public int numStudents;
  public ArrayList<WebScheduler> allTasks;
  public HashMap<String, Integer> students;
  public ArrayList<int[]> studentSchedules;

  public TotalScheduler(int numStudents, String[] taskInputFiles){
    numTasks = taskInputFiles.length;
    this.numStudents = numStudents;
    allTasks = new ArrayList<WebScheduler>();
    for(int i=0; i<numTasks; i++){
      WebScheduler s = new WebScheduler(taskInputFiles[i]);
      s.setup();
      allTasks.add(s);
    }
  }

  public void removeConflicts(){
    //TODO: implement time conflict resolution
    for(int i=0; i<numStudents; i++){
      studentSchedules.add(new int[168]);
    }


    //build student schedules, removing conflicts as necessary
    int numStudents = studentSchedules.size();
    for(WebScheduler s : allTasks){
      for(WebAvailability w : s.availableStudentsStatic){
        int studentNum = students.get(w.name);

      }
    }
  }


}
