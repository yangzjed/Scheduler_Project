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
    //taskInputFiles[2] = ROOT_DIRECTORY + "CS210_Fa22/DiscussionA_array.txt";
    //taskInputFiles[3] = ROOT_DIRECTORY + "CS210_Fa22/DiscussionB_array.txt";
    String[] taskTimeFiles = new String[2];
    taskTimeFiles[0] = ROOT_DIRECTORY + "CS210_Fa22/Ed_times.txt";
    taskTimeFiles[1] = ROOT_DIRECTORY + "CS210_Fa22/OH_times.txt";
    //taskTimeFiles[2] = ROOT_DIRECTORY + "CS210_Fa22/DiscussionA_times.txt";
    //taskTimeFiles[3] = ROOT_DIRECTORY + "CS210_Fa22/DiscussionB_times.txt";
    String[] taskStudentFiles = new String[2];
    taskStudentFiles[0] = ROOT_DIRECTORY + "CS210_Fa22/Ed_students.txt";
    taskStudentFiles[1] = ROOT_DIRECTORY + "CS210_Fa22/OH_students.txt";
    //taskStudentFiles[2] = ROOT_DIRECTORY + "CS210_Fa22/DiscussionA_students.txt";
    //taskStudentFiles[3] = ROOT_DIRECTORY + "CS210_Fa22/DiscussionB_students.txt";

    TotalScheduler scheduler = new TotalScheduler(15, taskInputFiles, taskTimeFiles, taskStudentFiles, ROOT_DIRECTORY + "CS210_Fa22/StudentList.txt");
    scheduler.schedule();

    WebScheduler s = new WebScheduler("data/CS210_Fa22/DiscussionA_array.txt");
    s.useStudentInfo = true;
    s.STUDENT_INFO_FILE_PATH = "data/CS210_Fa22/DiscussionA_students.txt";
    s.setup();
    s.WebSchedule();
    WebScheduler s2 = new WebScheduler("data/CS210_Fa22/DiscussionB_array.txt");
    s2.useStudentInfo = true;
    s2.STUDENT_INFO_FILE_PATH = "data/CS210_Fa22/DiscussionB_students.txt";
    s2.setup();
    s2.WebSchedule();


  }



  @Test
  void testCS210_Sp23(){
    String[] taskInputFiles =  new String[2];
    taskInputFiles[0] = ROOT_DIRECTORY + "CS210_Fa22/Ed_array.txt";
    taskInputFiles[1] = ROOT_DIRECTORY + "CS210_Fa22/OH_array.txt";
    //taskInputFiles[2] = ROOT_DIRECTORY + "CS210_Fa22/DiscussionA_array.txt";
    //taskInputFiles[3] = ROOT_DIRECTORY + "CS210_Fa22/DiscussionB_array.txt";
    String[] taskTimeFiles = new String[2];
    taskTimeFiles[0] = ROOT_DIRECTORY + "CS210_Fa22/Ed_times.txt";
    taskTimeFiles[1] = ROOT_DIRECTORY + "CS210_Fa22/OH_times.txt";
    //taskTimeFiles[2] = ROOT_DIRECTORY + "CS210_Fa22/DiscussionA_times.txt";
    //taskTimeFiles[3] = ROOT_DIRECTORY + "CS210_Fa22/DiscussionB_times.txt";
    String[] taskStudentFiles = new String[2];
    taskStudentFiles[0] = ROOT_DIRECTORY + "CS210_Fa22/Ed_students.txt";
    taskStudentFiles[1] = ROOT_DIRECTORY + "CS210_Fa22/OH_students.txt";
    //taskStudentFiles[2] = ROOT_DIRECTORY + "CS210_Fa22/DiscussionA_students.txt";
    //taskStudentFiles[3] = ROOT_DIRECTORY + "CS210_Fa22/DiscussionB_students.txt";

    //TotalScheduler scheduler = new TotalScheduler(15, taskInputFiles, taskTimeFiles, taskStudentFiles, ROOT_DIRECTORY + "CS210_Fa22/StudentList.txt");
    //scheduler.schedule();

    WebScheduler e = new WebScheduler("data/CS210_Sp23/Ed_array.txt");
    e.useStudentInfo = true;
    e.STUDENT_INFO_FILE_PATH = "data/CS210_Sp23/Ed_students.txt";
    e.setup();
    e.WebSchedule();

    WebScheduler o = new WebScheduler("data/CS210_Sp23/OH_array.txt");
    o.useStudentInfo = true;
    o.STUDENT_INFO_FILE_PATH = "data/CS210_Sp23/OH_students.txt";
    o.setup();
    o.WebSchedule();

    WebScheduler s = new WebScheduler("data/CS210_Sp23/DiscussionA_array.txt");
    s.useStudentInfo = true;
    s.STUDENT_INFO_FILE_PATH = "data/CS210_Sp23/DiscussionA_students.txt";
    s.setup();
    s.WebSchedule();
    WebScheduler s2 = new WebScheduler("data/CS210_Sp23/DiscussionB_array.txt");
    s2.useStudentInfo = true;
    s2.STUDENT_INFO_FILE_PATH = "data/CS210_Sp23/DiscussionB_students.txt";
    s2.setup();
    s2.WebSchedule();


  }
}
