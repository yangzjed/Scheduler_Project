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


  public TotalScheduler(int numStudents, String[] taskInputFiles, String[] taskTimeFiles, String[] taskStudentFiles, String studentListFile){
    numTasks = taskInputFiles.length;
    this.numStudents = numStudents;
    allTasks = new ArrayList<WebScheduler>();
    for(int i=0; i<numTasks; i++){
      WebScheduler s = new WebScheduler(taskInputFiles[i]);
      s.useStudentInfo = true;
      s.useTaskInfo = true;
      s.setup();
      allTasks.add(s);
    }
  }

  private void setAsUnavailable(int[] taskDescriptor, int studentNumber){
    WebScheduler s = allTasks.get(taskDescriptor[0]);
    s.availabilityArrayStatic[taskDescriptor[1]][studentNumber] = 0;
    s.availabilityArray[taskDescriptor[1]][studentNumber] = 0;
    s.availableStudents.get(studentNumber).availabilities.set(taskDescriptor[1], 0);
    s.availableStudentsStatic.get(studentNumber).availabilities.set(taskDescriptor[1], 0);
  }

  private int[] timeRange(int[] taskDescriptor){
    WebScheduler s = allTasks.get(taskDescriptor[0]);
    return s.taskTimes.get(taskDescriptor[1]);
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

  public int[] keptTask(ArrayList<int[]> taskDescriptors){

    //find the task that has the minimum number of students available

    int min = Integer.MAX_VALUE;
    int[] minTask = new int[2];
    for(int[] taskDescriptor : taskDescriptors){
      WebScheduler task = allTasks.get(taskDescriptor[0]);
      int slot = taskDescriptor[1];
      int a = task.countStudents(slot);
      if(min > a){
        min = a;
        minTask = taskDescriptor;
      }
    }


    return minTask;
  }

  public void removeConflicts(){
    //TODO: implement time conflict resolution

    //create initial student schedules
    for(int i=0; i<numStudents; i++){
      ArrayList<int[]> schedule = new ArrayList<>();
      for(int j=0; j<168; j++){
        int[] empty = new int[2];
        empty[0] = -1;
        empty[1] = -1;
        schedule.add(empty);
      }
      studentSchedules.add(schedule);
    }


    //build student schedules, removing conflicts as necessary
    int numStudents = studentSchedules.size();
    int i = 0;
    int j = 0;
    for(WebScheduler s : allTasks){
      for(WebAvailability w : s.availableStudentsStatic){
        //student w
        int studentNum = students.get(w.name); //global
        for(int k=0; k<w.availabilities.size(); k++){
          if(w.availabilities.get(k) != 0){
            int[] taskDescriptor = new int[2];
            taskDescriptor[0] = i;
            taskDescriptor[1] = k;
            List<int[]> res = addToSchedule(studentNum, s.taskTimes.get(j), taskDescriptor);
            if(!res.isEmpty()){
              //conflict found
              ArrayList<int[]> competingTasks = new ArrayList<int[]>(res);
              competingTasks.add(taskDescriptor);
              //TODO: find task with the least number of students offering to complete it
              int[] minTask = keptTask(competingTasks);
              if(Arrays.equals(minTask, taskDescriptor)){
                //overwrite everything with -1
                for(int[] a : res){
                  int[] n = new int[2];
                  n[0] = -1;
                  n[1] = -1;
                  a = n;
                }
                //replace entire range with taskDescriptor
                addToSchedule(studentNum, s.taskTimes.get(j), taskDescriptor);

                //set the availabilities in res to 0
                for(int[] a : res){
                  setAsUnavailable(a, j);
                }
              }
              else{
                //an existing task took precedence. Do nothing except set availabilities in taskDescriptor to 0
                setAsUnavailable(taskDescriptor, j);
              }

              //update all availability arrays and student lists

            }
          }
        }
        j++; //student number (local)
      }
      i++; //task number
    }
  }

  public void schedule(){
    removeConflicts();
    for(WebScheduler s : allTasks){
      s.WebSchedule();
    }
    //TODO: write output to file
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
