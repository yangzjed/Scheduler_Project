import java.util.*;


/* Web Monitoring Scheduling Algorithm:


while there are still unassigned students:
1. Find the least available student who has not been assigned
2. Assign this student to their time slot(s) that have least coverage.
3. recalculate coverages for all time slots

 */

public class WebScheduler {
    public int numSlots;
    public int f;
    int[][] availabilityArray; //max. 500 students
    int[] coverages;
    int[] assignments;

    WebScheduler(int numSlots){
        this.numSlots = numSlots;
        this.availabilityArray = new int[numSlots][500];
        this.coverages = new int[numSlots];
        this.assignments = new int[numSlots];

    }

    public void WebSchedule(){

        int numStudents = 2;
        ArrayList<WebAvailability> availableStudents = new ArrayList<WebAvailability>();
        WebAvailability a1 = new WebAvailability();
        WebAvailability a2 = new WebAvailability();
        for(int i=0; i<numSlots; i++){
            a1.availabilities.add(0);
            a2.availabilities.add(0);
        }

        a1.availabilities.set(0,1);
        a1.availabilities.set(3,1);
        a2.availabilities.set(0,1);
        a2.availabilities.set(6,1);

        availableStudents.add(a1);
        availableStudents.add(a2);

        //set up availabilityArray and coverages
        for(int i=0; i<numStudents; i++){
            WebAvailability currStudent = availableStudents.get(i);
            for(int j=0; j<numSlots; j++){
                availabilityArray[j][i] = currStudent.availabilities.get(j);
                coverages[j]++;
                System.out.printf("%d ",availabilityArray[j][i]);
            }
            System.out.println();
        }

        //rank the students by availability


        //assign students
        while(!availableStudents.isEmpty()){
            //TODO: Implement assignment algorithm
            availableStudents.remove(0);
        }
    }
}
