import java.util.*;

public class SchedulerEngine {
    public static void main(String[] args){


        System.out.println("Enter the number of time slots:");
        Scanner sc = new Scanner(System.in);
        int n;

        n = sc.nextInt();


        WebScheduler test = new WebScheduler(n);

        test.WebSchedule();
    }
}
