package problems.problem7;

public class CustomCyclicBarrier {
  private Integer parties;
  private Runnable barrierAction;
  private Integer counter;

  public CustomCyclicBarrier(Integer parties) {
    this.parties = parties;
    barrierAction = null;
    counter = 0;
  }

  public CustomCyclicBarrier(Integer parties, Runnable barrierAction) {
    this.parties = parties;
    this.barrierAction = barrierAction;
    counter = 0;
  }

  public synchronized void await() {
    counter++;

    if (counter < parties) {
      try {
        wait();
      } catch (InterruptedException e) {        
        e.printStackTrace();
      }
    } else {
      counter = 0;
      Thread t = new Thread(barrierAction);
      t.start();
      try {
        t.join();
      } catch (InterruptedException e) {        
        e.printStackTrace();
      }
      notifyAll();
    }
  }
}
