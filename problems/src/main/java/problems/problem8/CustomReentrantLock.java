package problems.problem8;

import java.util.LinkedList;
import java.util.Queue;

public class CustomReentrantLock {
  private boolean isLocked = false;
  private boolean isFair;
  private Thread lockThread = null;
  private Queue<Thread> fairQueue = new LinkedList<>();
  private int lockCounter = 0;

  public CustomReentrantLock() {
    isFair = false;
  }

  public CustomReentrantLock(boolean isFair) {    
    this.isFair = isFair;    
  }

  public synchronized void lock() {
    Thread callingThread = Thread.currentThread();

    while (isLocked && lockThread != callingThread) {
      if (isFair) {
        fairQueue.add(callingThread);
      }
      try {
        wait();
      } catch (InterruptedException e) {        
        e.printStackTrace();
      }
    }

    isLocked = true;
    lockThread = callingThread;
    lockCounter++;
  }

  public synchronized boolean tryLock() {
    Thread callingThread = Thread.currentThread();

    if (isLocked && lockThread != callingThread) {
      return false;
    }

    isLocked = true;
    lockThread = callingThread;
    lockCounter++;
    return true;
  }

  public synchronized void unlock() {
    if (Thread.currentThread() == lockThread) {
      lockCounter--;

      if (lockCounter == 0) {
        isLocked = false;
        lockThread = null;

        if (isFair) {
          Thread nextThread = fairQueue.poll();
          if (nextThread != null) {
            nextThread.notify();
          }
        } else {
          notify();
        }        
      }
    }
  }
}
