import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TotalSchedulerTest {
  public static final String ROOT_DIRECTORY = "data/";

  @Test
  void test1(){
    String[] taskInputFiles =  new String[2];
    taskInputFiles[0] = ROOT_DIRECTORY + "TotalSchedulerTest1/TaskA_array.txt";
    taskInputFiles[1] = ROOT_DIRECTORY + "TotalSchedulerTest1/TaskB_array.txt";
    String[] taskTimeFiles = new String[2];
    taskTimeFiles[0] = ROOT_DIRECTORY + "TotalSchedulerTest1/TaskA_times.txt";
    taskTimeFiles[1] = ROOT_DIRECTORY + "TotalSchedulerTest1/TaskB_times.txt";
    String[] taskStudentFiles = new String[2];
    taskStudentFiles[0] = ROOT_DIRECTORY + "TotalSchedulerTest1/TaskA_students.txt";
    taskStudentFiles[1] = ROOT_DIRECTORY + "TotalSchedulerTest1/TaskB_students.txt";



    TotalScheduler scheduler = new TotalScheduler(3, taskInputFiles, taskTimeFiles, taskStudentFiles, ROOT_DIRECTORY + "TotalSchedulerTest1/StudentList.txt");
    scheduler.schedule();


    assertEquals("Carol", scheduler.allTasks.get(1).assignmentsNames[0]);
    assertEquals("Bob", scheduler.allTasks.get(1).assignmentsNames[1]);
  }


  @Test
  void test2(){
    String[] taskInputFiles =  new String[2];
    taskInputFiles[0] = ROOT_DIRECTORY + "TotalSchedulerTest2/OH_array.txt";
    taskInputFiles[1] = ROOT_DIRECTORY + "TotalSchedulerTest2/Ed_array.txt";
    String[] taskTimeFiles = new String[2];
    taskTimeFiles[0] = ROOT_DIRECTORY + "TotalSchedulerTest2/OH_times.txt";
    taskTimeFiles[1] = ROOT_DIRECTORY + "TotalSchedulerTest2/Ed_times.txt";
    String[] taskStudentFiles = new String[2];
    taskStudentFiles[0] = ROOT_DIRECTORY + "TotalSchedulerTest2/OH_students.txt";
    taskStudentFiles[1] = ROOT_DIRECTORY + "TotalSchedulerTest2/Ed_students.txt";


    TotalScheduler scheduler = new TotalScheduler(14, taskInputFiles, taskTimeFiles, taskStudentFiles, ROOT_DIRECTORY + "TotalSchedulerTest2/StudentList.txt");
    scheduler.schedule();

  }


  @Test
  void testCS210_Fa22(){
    String[] taskInputFiles =  new String[2];
    taskInputFiles[0] = ROOT_DIRECTORY + "CS210_Fa22/Ed_array.txt";
    taskInputFiles[1] = ROOT_DIRECTORY + "CS210_Fa22/OH_array.txt";
    String[] taskTimeFiles = new String[2];
    taskTimeFiles[0] = ROOT_DIRECTORY + "CS210_Fa22/Ed_times.txt";
    taskTimeFiles[1] = ROOT_DIRECTORY + "CS210_Fa22/OH_times.txt";
    String[] taskStudentFiles = new String[2];
    taskStudentFiles[0] = ROOT_DIRECTORY + "CS210_Fa22/Ed_students.txt";
    taskStudentFiles[1] = ROOT_DIRECTORY + "CS210_Fa22/OH_students.txt";

    TotalScheduler scheduler = new TotalScheduler(15, taskInputFiles, taskTimeFiles, taskStudentFiles, ROOT_DIRECTORY + "CS210_Fa22/StudentList.txt");
    scheduler.schedule();

  }
}
