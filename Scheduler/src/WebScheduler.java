import java.util.*;
import java.io.*;


/* Web Monitoring Scheduling Algorithm:


while there are still unassigned students:
1. Find the least available student who has not been assigned
2. Assign this student to their time slot(s) that have least coverage.
3. recalculate coverages for all time slots

Algorithm 2:

while there are still unassigned students:
1. Find the time slot with least available students
2. Assign the least available student to this time slot
3. recalculate availabilities for all students

 */

public class WebScheduler {
    public int numSlots;
    int[][] availabilityArray; //max. 20 students
    int[] coverages;
    int[] assignments;

    WebScheduler(int numSlots){
        this.numSlots = numSlots;
        this.availabilityArray = new int[numSlots][20];
        this.coverages = new int[numSlots];
        this.assignments = new int[numSlots];
        for(int i=0; i<numSlots; i++){
            this.assignments[i]=-1;
        }

    }

    public void WebSchedule(){

        System.out.println("Number of students:");
        Scanner sc = new Scanner(System.in);

        File in = new File("src/input.txt");
        Scanner fsc = new Scanner(System.in);
        try{
            fsc = new Scanner(in);

        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }

        int numStudents = fsc.nextInt();
        ArrayList<WebAvailability> availableStudents = new ArrayList<WebAvailability>();
        for(int i=0; i<numStudents; i++){
            WebAvailability s = new WebAvailability(i+1,1);
            for(int j=0; j<numSlots; j++){
                s.availabilities.add(0);
            }
            availableStudents.add(s);
        }

        //set up availabilityArray and coverages
        for(int i=0; i<numSlots; i++){
            System.out.printf("Enter student availabilities for time slot %d\n", i);
            for(int j=0; j<numStudents; j++){
                int t = fsc.nextInt();
                availableStudents.get(j).availabilities.set(i, t);
                availabilityArray[i][j] = availableStudents.get(j).availabilities.get(i);
                coverages[i]++;
                System.out.printf("%d ",availabilityArray[i][j]);
            }
            System.out.println();
        }

        //rank the students by availability


        //assign students
        while(!availableStudents.isEmpty()){
            //TODO: Implement assignment algorithm 2

            //1. find the time slot with least available students;
            ArrayList<Integer> timeSlotAvailabilities = new ArrayList<Integer>();
            for(int i=0; i<availabilityArray.length; i++){
                int totalStudents = 0;
                for(int j=0; j<availabilityArray[0].length; j++){
                    if(availabilityArray[i][j]!=0){
                        totalStudents++;
                    }
                }
                timeSlotAvailabilities.add(totalStudents);
            }

            int currentMin = numStudents+1;
            int currentTask = 0;
            for(int i=0; i<timeSlotAvailabilities.size(); i++){
                if(timeSlotAvailabilities.get(i)<currentMin && timeSlotAvailabilities.get(i)!=0){
                    currentMin = timeSlotAvailabilities.get(i);
                    currentTask = i;
                }
            }


            //2. Find the least available student that can do currentTask
            ArrayList<WebAvailability> temp = new ArrayList<WebAvailability>();
            for(WebAvailability a : availableStudents){
                if(a.availabilities.get(currentTask)!=0){
                    temp.add(a);
                }
            }

            Collections.sort(temp);

            //WebAvailability assignment = temp.get(0);
            if(!temp.isEmpty()){
                assignments[currentTask] = temp.get(0).studentID;
            }


            //3. update availabilities
            if(!availableStudents.isEmpty()){
                for(int j=0; j<availableStudents.size(); j++){
                    availableStudents.get(j).availabilities.set(currentTask, 0);
                    availableStudents.get(j).updateTotalAvailability();
                }
                for(int j=0; j<availabilityArray[0].length; j++){
                    availabilityArray[currentTask][j] = 0;
                }
                if (!temp.isEmpty()) {
                    availableStudents.remove(temp.get(0));
                }
                else{
                    availableStudents.remove(0);
                }

            }


        }


        System.out.println(Arrays.toString(assignments));
    }
}
