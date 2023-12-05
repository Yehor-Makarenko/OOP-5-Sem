package problems.problem6;

public class Main {
  public static void main(String[] args) {
    MichaelScottQueue<Integer> queue = new MichaelScottQueue<>();
    Thread t1 = new Thread(() -> {
      queue.push(3);
      queue.push(5);
      queue.push(7);
      System.out.println(queue.pop());
      System.out.println(queue.pop());
    });

    Thread t2 = new Thread(() -> {
      queue.push(2);
      queue.push(1);      
      System.out.println(queue.pop());
      System.out.println(queue.pop());
    });

    t1.start();
    t2.start();
    try {
      t1.join();
      t2.join();
    } catch (InterruptedException e) {      
      e.printStackTrace();
    }    

    System.out.println(queue.pop());
  }  
}
