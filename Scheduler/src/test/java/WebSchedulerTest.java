import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class WebSchedulerTest {
  @Test
  public void test1(){
    WebScheduler s = new WebScheduler("data/input.txt");
    s.WebSchedule();
    assert(s.numPreferencesSatisifed>=7);
    assertEquals(8, s.assignments[0]);
    assertEquals(11, s.assignments[10]);
  }

  @Test
  public void test2(){
    WebScheduler s = new WebScheduler("data/OH_new.txt");
    s.WebSchedule();
    assert(s.numPreferencesSatisifed>=2);
    assertEquals(3, s.assignments[0]);
  }

  @Test
  public void test3(){
    WebScheduler s = new WebScheduler("data/OH_returning.txt");
    s.WebSchedule();
    assert(s.numPreferencesSatisifed>=3);
    assertEquals(5, s.assignments[0]);
  }


  @Test
  public void test4(){
    WebScheduler s = new WebScheduler("data/continuous_test.txt");
    s.WebSchedule();
    assertEquals(2, s.assignments[7]);
  }




}
