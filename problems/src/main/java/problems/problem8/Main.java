package problems.problem8;

import java.util.concurrent.locks.ReentrantLock;

public class Main {
  public static void main(String[] args) {
    ReentrantLock lock = new ReentrantLock();
    lock.lock();
    System.out.println("Main thread lock");
    Thread t1 = new Thread(() -> {
      lock.lock();
      System.out.println("Thread1 lock");
      lock.unlock();
      System.out.println("Thread1 unlock");
    });

    t1.start();
    lock.unlock();
    System.out.println("Main thread unlock");
    try {
      t1.join();
    } catch (InterruptedException e) {      
      e.printStackTrace();
    }
  }
}
