import java.util.Arrays;

public class SchedulerEngine {
  public static void main(String[] args){

    WebScheduler test = new WebScheduler("data/continuous_test.txt");
    test.useStudentInfo = true;
    test.useTaskInfo = true;

    test.setup();
    test.WebSchedule();
    System.out.println();
    System.out.println(Arrays.toString(test.assignments));
  }
}
