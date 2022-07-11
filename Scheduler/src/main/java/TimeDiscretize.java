import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Monday:1 Tuesday:2, etc.
 */

public class TimeDiscretize {

  public static final int NUM_TIME_SEGMENTS = 168;

  public static int[][] createAvailabilityArray(String inputFile) throws FileNotFoundException {
    File in = new File(inputFile);
    Scanner fsc = new Scanner(in);
    int numStudents = fsc.nextInt();
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


    return availabilityArray;
  }

  public static void main(String[] args) throws FileNotFoundException {
    int[][] answer = createAvailabilityArray("data/continuous_time.txt");
    answer[0][0] = 0;
  }

}
