import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Monday:1 Tuesday:2, etc.
 */

public class TimeDiscretize {

  public static final int NUM_TIME_SEGMENTS = 168;
  public static int NUM_STUDENTS;

  public static int[] timeRange(int[] taskTimeBounds){
    int[] range = new int[2];

    int dayFactor = taskTimeBounds[0]-1;
    range[0] = dayFactor * 24 + taskTimeBounds[1];
    range[1] = dayFactor * 24 + taskTimeBounds[2];
    return range;
  }

  public static int[][] createAvailabilityArray(String inputFile) throws FileNotFoundException {
    File in = new File(inputFile);
    Scanner fsc = new Scanner(in);
    int numStudents = fsc.nextInt();
    NUM_STUDENTS = numStudents;
    fsc.nextLine();

    int[][] availabilityArray = new int[NUM_TIME_SEGMENTS][numStudents];
    for(int student=0; student<numStudents; student++){
      int[] studentSchedule = Arrays.stream(fsc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
      for(int j=0; j <studentSchedule.length/3; j++){
        int dayFactor = studentSchedule[3*j] - 1;
        int startHour = dayFactor * 24 + studentSchedule[3*j+1];
        int endHour = dayFactor * 24 + studentSchedule[3*j+2];
        for(int i=startHour; i<=endHour; i++){
          availabilityArray[i][student] = 1;
        }

      }
    }
    fsc.close();


    return availabilityArray;
  }

  public static void main(String[] args) throws FileNotFoundException {
    int[][] answer = createAvailabilityArray("data/continuous_time.txt");
    answer[0][0] = 0;
  }

}
