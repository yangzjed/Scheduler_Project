import java.util.*;
import java.io.*;

public class SchedulerEngine {
    public static void main(String[] args){

        WebScheduler test = new WebScheduler("src/data/input.txt");

        test.WebSchedule();
    }
}
