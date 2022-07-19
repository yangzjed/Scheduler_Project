import java.util.Arrays;

public class SchedulerEngine {
  public static void main(String[] args){

    WebScheduler test = new WebScheduler("data/test1.txt");

    test.WebSchedule();
    System.out.println();
    System.out.println(Arrays.toString(test.assignments));
  }
}
