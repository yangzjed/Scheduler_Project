import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class WebSchedulerTest {
  @Test
  public void test1(){
    WebScheduler s = new WebScheduler("data/input.txt");
    s.WebSchedule();
    assert(s.numPreferencesSatisifed>=8);
  }

}
