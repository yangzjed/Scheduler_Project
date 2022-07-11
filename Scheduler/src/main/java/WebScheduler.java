

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
  public static int MAX_SLOTS = 1000;
  public static String INPUT_FILE_PATH = "";

  public int numSlots;
  int[][] availabilityArray; //max. 20 students
  int[][] availabilityArrayStatic;
  int[] coverages;
  int[] assignments;
  int[] previousAssignments;
  public int numPreferencesSatisifed;

  WebScheduler(String inputfile){
    INPUT_FILE_PATH = inputfile;
    this.numSlots = -1;
    //this.coverages = new int[numSlots];
    this.assignments = new int[MAX_SLOTS];
    for(int i=0; i<MAX_SLOTS; i++){
      this.assignments[i]=-1;
    }

  }

  private void copyIntArray(int[] src, int[] dst, int len){
    for(int i=0; i<len; i++){
      dst[i] = src[i];
    }
  }

  private int countUnassignedSlots(int assignments[]){
    int result = 0;
    for(int i=0; i<numSlots; i++){
      if(assignments[i]<=0){
        result++;
      }
    }
    return result;
  }

  private int countSatisfiedPreferences(int assignments[]){
    int result = 0;
    for(int i=0; i<numSlots; i++){
      if(assignments[i]<=0){
        continue;
      }
      if(availabilityArrayStatic[i][assignments[i]-1]==2){
        result++;
      }
    }
    return result;
  }

  private void initializeScheduler(int numStudents, int numSlots){
    this.availabilityArray = new int[numSlots][numStudents];
    this.availabilityArrayStatic = new int[numSlots][numStudents];
    this.assignments = new int[numSlots];
    previousAssignments = new int[numSlots];
    Arrays.fill(previousAssignments, -1);
    for(int i=0; i<numSlots; i++){
      this.assignments[i]=-1;
    }
  }

  private void initializeStudents(ArrayList<WebAvailability> students, int numStudents){
    for(int i=0; i<numStudents; i++){
      WebAvailability s = new WebAvailability(i+1,1);
      for(int j=0; j<numSlots; j++){
        s.availabilities.add(0);
      }
      students.add(s);
    }
  }

  private int findLeastPopularSlot(int numStudents){
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
    return currentTask;
  }

  private void updateAvailabilities(ArrayList<WebAvailability> availableStudents, int currentTask){
    for(int j=0; j<availableStudents.size(); j++){
      availableStudents.get(j).availabilities.set(currentTask, 0);
      availableStudents.get(j).updateTotalAvailability();
    }
    for(int j=0; j<availabilityArray[0].length; j++){
      availabilityArray[currentTask][j] = 0;
    }
  }

  public void WebSchedule(){

    File in = new File(INPUT_FILE_PATH);
    Scanner fsc = new Scanner(System.in);
    try{
      fsc = new Scanner(in);

    }
    catch(FileNotFoundException e){
      e.printStackTrace();
    }

    int numStudents = fsc.nextInt();
    this.numSlots = fsc.nextInt();
    initializeScheduler(numStudents, numSlots);
    ArrayList<WebAvailability> availableStudents = new ArrayList<WebAvailability>();
    ArrayList<WebAvailability> availableStudentsStatic = new ArrayList<WebAvailability>();

    initializeStudents(availableStudents,numStudents);
    initializeStudents(availableStudentsStatic,numStudents);

    //set up availabilityArray and coverages
    for(int i=0; i<numSlots; i++){
      //System.out.printf("Enter student availabilities for time slot %d\n", i);
      for(int j=0; j<numStudents; j++){
        int t = fsc.nextInt();
        availableStudents.get(j).availabilities.set(i, t);
        availableStudentsStatic.get(j).availabilities.set(i,t);
        availabilityArray[i][j] = availableStudents.get(j).availabilities.get(i);
        availabilityArrayStatic[i][j] = availableStudents.get(j).availabilities.get(i);
        //coverages[i]++;
        //System.out.printf("%d ",availabilityArray[i][j]);
      }
      //System.out.println();
    }


    int prefToSatisfy = 0;
    int prevAssigned = 0;
    for(prefToSatisfy = 0; prefToSatisfy<numStudents; prefToSatisfy++){
      int prefLeft = prefToSatisfy;
      //assign students
      while(!availableStudents.isEmpty()){
        //TODO: Implement assignment algorithm 2

        int currentTask = findLeastPopularSlot(numStudents);

        //2. Find the least available student that can do currentTask
        ArrayList<WebAvailability> temp = new ArrayList<WebAvailability>();
        ArrayList<WebAvailability> tempPref = new ArrayList<WebAvailability>();
        for(WebAvailability a : availableStudents){
          if(a.availabilities.get(currentTask)!=0){
            temp.add(a);
          }
          if(a.availabilities.get(currentTask)==2){
            tempPref.add(a);
          }
        }

        if(!tempPref.isEmpty() && prefLeft >0){
          temp = tempPref;
          prefLeft--;
        }

        Collections.sort(temp);
        //WebAvailability assignment = temp.get(0);
        if(!temp.isEmpty()){
          assignments[currentTask] = temp.get(0).studentID;
        }


        //3. update availabilities
        if(!availableStudents.isEmpty()){
          updateAvailabilities(availableStudents, currentTask);
          if (!temp.isEmpty()) {
            availableStudents.remove(temp.get(0));
          }
          else{
            availableStudents.remove(0);
          }
        }

      }


      if(countUnassignedSlots(assignments) > countUnassignedSlots(previousAssignments)){
        //System.out.println("Break");
        copyIntArray(previousAssignments, assignments, assignments.length);
        break;
      }


      //restore original variables
      for(int i=0; i<numStudents; i++){
        WebAvailability s = new WebAvailability(i+1,1);
        for(int j=0; j<numSlots; j++){
          s.availabilities.add(0);
          s.availabilities.set(j,availableStudentsStatic.get(i).availabilities.get(j));
        }
        availableStudents.add(s);
      }
      for(int i=0; i<numSlots; i++){
        for(int j=0; j<numStudents; j++){
          availabilityArray[i][j] = availabilityArrayStatic[i][j];
        }
      }

      if(prefToSatisfy==numStudents-1){
        break;
      }
      //System.out.println(Arrays.toString(assignments));
      copyIntArray(assignments, previousAssignments, assignments.length);

      for(int i=0; i<assignments.length; i++){
        assignments[i] = -1;
      }
      //System.out.println(Arrays.toString(assignments));
    }

    //swap students if more preferences can be satisfied after swap
    for(int i=0; i<numSlots; i++){
      for(int j=i; j<numSlots; j++){
        if(assignments[i]<=0 || assignments[j] <=0){
          continue;
        }
        if(availabilityArrayStatic[i][assignments[j]-1]!=0 && availabilityArrayStatic[j][assignments[i]-1]!=0){
          if(availabilityArrayStatic[i][assignments[j]-1] + availabilityArrayStatic[j][assignments[i]-1] > availabilityArrayStatic[i][assignments[i]-1] + availabilityArrayStatic[j][assignments[j]-1]){
            int temp = assignments[i];
            assignments[i] = assignments[j];
            assignments[j] = temp;
          }
        }
      }
    }

    System.out.println("Task assignments:");
    System.out.println(Arrays.toString(assignments));
    System.out.printf("Time slots assigned: %d/%d\n", numSlots-countUnassignedSlots(assignments), numSlots);
    System.out.printf("Preferred time slots granted:%d", countSatisfiedPreferences(assignments));
    numPreferencesSatisifed = countSatisfiedPreferences(assignments);
  }
}
