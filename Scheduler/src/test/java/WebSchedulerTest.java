import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class WebSchedulerTest {
  @Test
  public void test1(){
    WebScheduler s = new WebScheduler("data/input.txt");
    s.setup();
    s.WebSchedule();
    assert(s.numPreferencesSatisifed>=7);
    assertEquals(8, s.assignments[0]);
    assertEquals(11, s.assignments[10]);
  }

  @Test
  public void test2(){
    WebScheduler s = new WebScheduler("data/OH_new.txt");
    s.setup();
    s.WebSchedule();
    assert(s.numPreferencesSatisifed>=2);
    assertEquals(3, s.assignments[0]);
  }

  @Test
  public void test3(){
    WebScheduler s = new WebScheduler("data/OH_returning.txt");
    s.setup();
    s.WebSchedule();
    assert(s.numPreferencesSatisifed>=3);
    assertEquals(5, s.assignments[0]);
  }


  @Test
  public void test4(){
    WebScheduler s = new WebScheduler("data/continuous_test.txt");
    s.setup();
    s.WebSchedule();
    assertEquals(2, s.assignments[7]);
  }


  @Test
  public void testSample(){
    WebScheduler s = new WebScheduler("data/test1.txt");
    s.setup();
    s.WebSchedule();
    assert(s.numPreferencesSatisifed==1);
    assertEquals(1, s.assignments[1]);
  }





}
