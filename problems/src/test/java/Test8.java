import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import problems.problem8.CustomReentrantLock;

public class Test8 {
  @Test
  public void testLock() {
    ArrayList<Integer> arr = new ArrayList<>();
    CustomReentrantLock lock = new CustomReentrantLock();
    lock.lock();
    Thread t1 = new Thread(() -> {
      lock.lock();
      arr.add(2);
      lock.unlock();
    });

    t1.start();
    arr.add(1);
    lock.unlock();
    try {
      t1.join();
    } catch (InterruptedException e) {      
      e.printStackTrace();
    }
    
    assertArrayEquals(new Integer[]{1, 2}, arr.toArray());    
  }

  @Test
  public void testFairLock() {
    ArrayList<Integer> arr = new ArrayList<>();
    CustomReentrantLock lock = new CustomReentrantLock();
    lock.lock();
    Thread t1 = new Thread(() -> {
      lock.lock();
      arr.add(2);
      lock.unlock();
    });
    Thread t2 = new Thread(() -> {
      lock.lock();
      arr.add(3);
      lock.unlock();
    });

    t1.start();
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {      
      e.printStackTrace();
    }
    t2.start();

    arr.add(1);
    lock.unlock();
    try {
      t1.join();
      t2.join();
    } catch (InterruptedException e) {      
      e.printStackTrace();
    }
    
    assertArrayEquals(new Integer[]{1, 2, 3}, arr.toArray());    
  }

  @Test
  public void testTryLock() {
    ArrayList<Integer> arr = new ArrayList<>();
    CustomReentrantLock lock = new CustomReentrantLock();
    assertEquals(true, lock.tryLock());   
    Thread t1 = new Thread(() -> {
      assertEquals(false, lock.tryLock());
      lock.lock();
      arr.add(2);
      lock.unlock();
    });

    t1.start();
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {      
      e.printStackTrace();
    }
    arr.add(1);
    lock.unlock();
    try {
      t1.join();
    } catch (InterruptedException e) {      
      e.printStackTrace();
    }
    
    assertArrayEquals(new Integer[]{1, 2}, arr.toArray());    
  }
}
