import java.util.*;


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
    int[][] availabilityArray; //max. 500 students
    int[] coverages;
    int[] assignments;

    WebScheduler(int numSlots){
        this.numSlots = numSlots;
        this.availabilityArray = new int[numSlots][500];
        this.coverages = new int[numSlots];
        this.assignments = new int[numSlots];
        for(int i=0; i<numSlots; i++){
            this.assignments[i]=-1;
        }

    }

    public void WebSchedule(){

        int numStudents = 2;
        ArrayList<WebAvailability> availableStudents = new ArrayList<WebAvailability>();
        WebAvailability a1 = new WebAvailability(1, 1);
        WebAvailability a2 = new WebAvailability(2, 1);
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
            //TODO: Implement assignment algorithm 2

            //1. find the time slot with least available students;
            ArrayList<Integer> timeSlotAvailabilities = new ArrayList<Integer>();
            for(int i=0; i<availabilityArray[0].length; i++){
                int totalStudents = 0;
                for(int j=0; j<availabilityArray.length; j++){
                    if(availabilityArray[j][i]!=0){
                        totalStudents++;
                    }
                }
                timeSlotAvailabilities.add(totalStudents);
            }

            int currentMin = 0;
            int currentTask = 0;
            for(int i=0; i<timeSlotAvailabilities.size(); i++){
                if(timeSlotAvailabilities.get(i)<currentMin){
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
                if (!temp.isEmpty()) {
                    availableStudents.remove(temp.get(0));
                }
                else{
                    availableStudents.remove(0);
                }

            }


        }
    }
}
