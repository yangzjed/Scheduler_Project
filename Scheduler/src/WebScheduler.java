import java.util.*;

public class WebScheduler {
    public static int numSlots;
    ArrayList<ArrayList<Integer>> availabilityArray; //dim 1 length = numSlots

    public void OHSchedule(){
        int numStudents = 2;
        numSlots = 14;
        ArrayList<WebAvailability> availabilityList = new ArrayList<WebAvailability>();
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

        availabilityList.add(a1);
        availabilityList.add(a2);


        for(int i=0; i<numStudents; i++){
            WebAvailability currStudent = availabilityList.get(i);
            for(int j=0; j<numSlots; j++){
                if(currStudent.availabilities.get(j)==1){
                    availabilityArray.get(j).add(i);
                }
            }
        }
    }
}
