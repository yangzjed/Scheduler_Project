import java.util.Arrays;

public class SchedulerEngine {
  public static void main(String[] args){

    WebScheduler test = new WebScheduler("data/continuous_test.txt");
    test.useStudentInfo = true;
    test.useTaskInfo = true;

    test.STUDENT_INFO_FILE_PATH = "data/continuous_test_volunteeredBlocks.txt";
    test.TASK_INFO_FILE_PATH = "data/continuous_test_taskTimes.txt";

    test.setup();
    test.WebSchedule();
    System.out.println();
    System.out.println(Arrays.toString(test.assignments));
  }
}
