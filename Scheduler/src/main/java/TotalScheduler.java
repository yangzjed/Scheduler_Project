import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class TotalScheduler {
  public static final String ROOT_DIRECTORY = "data/TotalSchedulerTest1/";

  public int numTasks;
  public int numStudents;
  public ArrayList<WebScheduler> allTasks;
  public HashMap<String, Integer> students = new HashMap<>();
  public ArrayList<ArrayList<int[]>> studentSchedules = new ArrayList<>(); //int[2]: [task number, time slot]


  public TotalScheduler(int numStudents, String[] taskInputFiles, String[] taskTimeFiles, String[] taskStudentFiles, String studentListFile) {

    createStudentMap(studentListFile);
    numTasks = taskInputFiles.length;
    this.numStudents = numStudents;
    allTasks = new ArrayList<WebScheduler>();
    for (int i = 0; i < numTasks; i++) {
      WebScheduler s = new WebScheduler(taskInputFiles[i]);
      s.useStudentInfo = true;
      s.useTaskInfo = true;
      s.TASK_INFO_FILE_PATH = taskTimeFiles[i];
      s.STUDENT_INFO_FILE_PATH = taskStudentFiles[i];
      s.setup();
      allTasks.add(s);
    }
  }

  private void createStudentMap(String studentListFile){
    File f = new File(studentListFile);
    try {
      Scanner sc = new Scanner(f);
      int studentID = 0;
      while(sc.hasNext()){
        students.put(sc.next(), studentID);
        studentID++;
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  private void setAsUnavailable(int[] taskDescriptor, String studentName){
    WebScheduler s = allTasks.get(taskDescriptor[0]);
    //TODO: find local student number
    int localStudentNumber = -1;
    for(int i=0; i<s.availableStudentsStatic.size(); i++){
      if(s.availableStudents.get(i).name.equals(studentName)){
        localStudentNumber = i;
      }
    }
    s.availabilityArrayStatic[taskDescriptor[1]][localStudentNumber] = 0;
    s.availabilityArray[taskDescriptor[1]][localStudentNumber] = 0;
    s.availableStudents.get(localStudentNumber).availabilities.set(taskDescriptor[1], 0);
    s.availableStudentsStatic.get(localStudentNumber).availabilities.set(taskDescriptor[1], 0);
  }

  private int[] timeRange(int[] taskDescriptor){
    WebScheduler s = allTasks.get(taskDescriptor[0]);
    return s.taskTimes.get(taskDescriptor[1]);
  }

  public List<int[]> addToSchedule(int student, int[] timeRange, int[] taskDescriptor){

    ArrayList<int[]> studentSchedule = studentSchedules.get(student);
    ArrayList<int[]> conflicts = new ArrayList<>();

    for(int i=timeRange[0]; i<=timeRange[1]; i++){
      if(studentSchedule.get(i)[0]!=-1 && studentSchedule.get(i)[0]!=taskDescriptor[0]){
        if(!((i!=timeRange[0]) && Arrays.equals(studentSchedule.get(i), studentSchedule.get(i - 1)))){
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
        int localTaskNum = 0;
        int studentNum = students.get(w.name); //global
        for(int k=0; k<w.availabilities.size(); k++){
          if(w.availabilities.get(k) != 0) {
            int[] taskDescriptor = new int[2];
            taskDescriptor[0] = i;
            taskDescriptor[1] = localTaskNum;
            List<int[]> res = addToSchedule(studentNum, s.taskTimes.get(localTaskNum), taskDescriptor);
            if (!res.isEmpty()) {
              //conflict found
              ArrayList<int[]> competingTasks = new ArrayList<int[]>(res);
              competingTasks.add(taskDescriptor);
              //TODO: find task with the least number of students offering to complete it
              int[] minTask = keptTask(competingTasks);
              if (Arrays.equals(minTask, taskDescriptor)) {
                //overwrite everything with -1
                for (int[] a : res) {
                  int[] n = new int[2];
                  n[0] = -1;
                  n[1] = -1;
                  a = n;
                }
                //replace entire range with taskDescriptor
                addToSchedule(studentNum, s.taskTimes.get(j), taskDescriptor);

                //set the availabilities in res to 0
                for (int[] a : res) {
                  setAsUnavailable(a, w.name);
                }
              } else {
                //an existing task took precedence. Do nothing except set availabilities in taskDescriptor to 0
                setAsUnavailable(taskDescriptor, w.name);
              }

              //update all availability arrays and student lists

            }
          }
          localTaskNum++;
        }
        j++; //student number (local)
      }
      j = 0;
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

    String[] taskInputFiles =  new String[2];
    taskInputFiles[0] = ROOT_DIRECTORY + "TaskA_array.txt";
    taskInputFiles[1] = ROOT_DIRECTORY + "TaskB_array.txt";
    String[] taskTimeFiles = new String[2];
    taskTimeFiles[0] = ROOT_DIRECTORY + "TaskA_times.txt";
    taskTimeFiles[1] = ROOT_DIRECTORY + "TaskB_times.txt";
    String[] taskStudentFiles = new String[2];
    taskStudentFiles[0] = ROOT_DIRECTORY + "TaskA_students.txt";
    taskStudentFiles[1] = ROOT_DIRECTORY + "TaskB_students.txt";



    TotalScheduler scheduler = new TotalScheduler(3, taskInputFiles, taskTimeFiles, taskStudentFiles, ROOT_DIRECTORY + "StudentList.txt");
    scheduler.schedule();

  }


}