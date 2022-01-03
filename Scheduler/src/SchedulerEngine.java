import java.util.*;
import java.io.*;

public class SchedulerEngine {
    public static void main(String[] args){


        System.out.println("Enter the number of time slots:");
        Scanner sc = new Scanner(System.in);

        File in = new File("src/input.txt");
        Scanner fsc = new Scanner(System.in);
        try{
            fsc = new Scanner(in);
            //System.out.println("HI");
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        int n;

        n = sc.nextInt();
        //n = fsc.nextInt();


        WebScheduler test = new WebScheduler(n);

        test.WebSchedule();
    }
}
