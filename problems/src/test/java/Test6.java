import static org.junit.Assert.assertEquals;

import org.junit.Test;

import problems.problem6.MichaelScottQueue;

public class Test6 {
  @Test
  public void testPush() {
    MichaelScottQueue<Integer> queue = new MichaelScottQueue<>();
    Thread t1 = new Thread(() -> {
      queue.push(3);
    });

    Thread t2 = new Thread(() -> {
      queue.push(2);
    });

    t1.start();
    t2.start();
    try {
      t1.join();
      t2.join();
    } catch (InterruptedException e) {      
      e.printStackTrace();
    }  

    int a = queue.pop();
    int b = queue.pop();

    assertEquals(5, a + b);
  }

  @Test
  public void testPop() {
    MichaelScottQueue<Integer> queue = new MichaelScottQueue<>();
    queue.push(2);
    queue.push(3);    
    int arr[] = new int[2];
    Thread t1 = new Thread(() -> {
      arr[0] = queue.pop();      
    });

    Thread t2 = new Thread(() -> {
      arr[1] = queue.pop();
      return;
    });

    t1.start();
    t2.start();
    try {
      t1.join();
      t2.join();
    } catch (InterruptedException e) {      
      e.printStackTrace();
    }      

    assertEquals(null, queue.pop());
  }
}
