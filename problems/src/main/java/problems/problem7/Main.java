package problems.problem7;

public class Main {
  public static void main(String[] args) {
    CustomCyclicBarrier barrier = new CustomCyclicBarrier(2, () -> {
      System.out.println("Ready");
    });

    Thread t1 = new Thread(() -> {
      System.out.println(1);
      barrier.await();
      System.out.println(2);
      barrier.await();
    });
    Thread t2 = new Thread(() -> {
      System.out.println(3);
      barrier.await();
      System.out.println(4);
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
  }
}
