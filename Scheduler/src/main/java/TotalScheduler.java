import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TotalScheduler {
  public int numTasks;
  public int numStudents;
  public ArrayList<WebScheduler> allTasks;
  public HashMap<String, Integer> students;
  public ArrayList<ArrayList<int[]>> studentSchedules; //int[2]: [task number, time slot]

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

  public List<int[]> addToSchedule(int student, int[] timeRange, int[] taskDescriptor){

    ArrayList<int[]> studentSchedule = studentSchedules.get(student);
    ArrayList<int[]> conflicts = new ArrayList<>();

    for(int i=timeRange[0]; i<=timeRange[1]; i++){
      if(studentSchedule.get(i)[0]!=0){
        if((i!=0) && Arrays.equals(studentSchedule.get(i), studentSchedule.get(i - 1))){
          int[] conflict = new int[2];
          conflict[0] = studentSchedule.get(i)[0];
          conflict[1] = studentSchedule.get(i)[1];
          conflicts.add(conflict);
        }

      }
    }

    if(!conflicts.isEmpty()){
      return conflicts;
    }

    for(int i=timeRange[0]; i<=timeRange[1]; i++){
      studentSchedule.set(i, taskDescriptor);
    }

    return new ArrayList<int[]>();
  }

  public void removeConflicts(){
    //TODO: implement time conflict resolution

    //create initial student schedules
    for(int i=0; i<numStudents; i++){
      ArrayList<int[]> schedule = new ArrayList<>();
      for(int j=0; j<168; j++){
        schedule.add(new int[2]);
      }
      studentSchedules.add(schedule);
    }


    //build student schedules, removing conflicts as necessary
    int numStudents = studentSchedules.size();
    int i = 0;
    int j = 0;
    for(WebScheduler s : allTasks){
      for(WebAvailability w : s.availableStudentsStatic){
        int studentNum = students.get(w.name);
        for(int k=0; k<w.availabilities.size(); k++){
          if(w.availabilities.get(k) != 0){
            int[] taskDescriptor = new int[2];
            taskDescriptor[0] = i;
            taskDescriptor[1] = k;
            List<int[]> res = addToSchedule(studentNum, s.taskTimes.get(j), taskDescriptor);
            if(!res.isEmpty()){
              //conflict found
            }
          }
        }
        j++;
      }
      i++;
    }
  }

  public static void main(String[] args){
    int[] test = new int[2];
    test[0] = 3;
    test[1] = 4;

    ArrayList<int[]> a = new ArrayList<>();
    a.add(new int[2]);
    a.add(new int[2]);
    a.set(0, test);
    a.set(1, test);
    test[0] = 1;

  }


}
