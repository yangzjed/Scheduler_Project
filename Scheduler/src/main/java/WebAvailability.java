

import java.util.*;

public class WebAvailability implements Comparable<WebAvailability> {
  public ArrayList<Integer> availabilities; //0:unavailable, 1: available, 2: preferred
  public int numSlotsVolunteered;
  public int totalAvailability;
  public int studentID;
  public String name;

  WebAvailability(int studentID, int numSlotsVolunteered){
    availabilities = new ArrayList<Integer>();
    this.numSlotsVolunteered = numSlotsVolunteered;
    this.studentID = studentID;

  }

  public void updateTotalAvailability(){
    int count = 0;
    for(int i=0; i<availabilities.size(); i++){
      if(availabilities.get(i)!=0){
        count++;
      }
    }
    totalAvailability = count;
  }

  @Override
  public int compareTo(WebAvailability other){
    return this.totalAvailability - other.totalAvailability;
  }

}
