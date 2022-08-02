import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TotalSchedulerTest {
  public static final String ROOT_DIRECTORY = "data/TotalSchedulerTest1/";

  @Test
  void test1(){
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


    assertEquals("Carol", scheduler.allTasks.get(1).assignmentsNames[0]);
    assertEquals("Bob", scheduler.allTasks.get(1).assignmentsNames[1]);
  }
}
