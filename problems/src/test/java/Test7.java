import static org.junit.Assert.assertEquals;

import org.junit.Test;

import problems.problem7.CustomCyclicBarrier;

public class Test7 {
  @Test
  public void testBarrier() {
    int arr[] = {0};
    CustomCyclicBarrier barrier = new CustomCyclicBarrier(2, () -> {
      arr[0]++;
    });

    Thread t1 = new Thread(() -> {      
      barrier.await();      
      barrier.await();
    });
    Thread t2 = new Thread(() -> {      
      barrier.await();      
      barrier.await();
    });

    t1.start();
    t2.start();

    try {
      t1.join();
      t2.join();
    } catch (InterruptedException e) {      
      e.printStackTrace();
    }

    assertEquals(2, arr[0]);
  }
}
