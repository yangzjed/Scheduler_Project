public class Student {

  public static int HOURS_PER_WEEK = 168;

  public String name;
  public int[] schedule;

  public Student(String name){
    this.name = name;
    this.schedule = new int[HOURS_PER_WEEK];
  }




}
