package problems.problem6;

import java.util.concurrent.atomic.AtomicReference;

public class MichaelScottQueue<T> {
  private AtomicReference<Node<T>> head;
  private AtomicReference<Node<T>> tail;

  public MichaelScottQueue() {
    Node<T> dummy = new Node<T>(null);
    head = new AtomicReference<>(dummy);
    tail = new AtomicReference<>(dummy);
  }

  public void push(T value) {
    Node<T> newTail = new Node<>(value);

    while (true) {
      Node<T> currTail = tail.get();
      if (currTail.getNext().compareAndSet(null, newTail)) {
        tail.compareAndSet(currTail, newTail);
        return;
      } else {
        tail.compareAndSet(currTail, currTail.getNext().get());
      }
    }
  }

  public T pop() {
    while (true) {
      Node<T> currHead = head.get();
      Node<T> currTail = tail.get();
      Node<T> currHeadNext = currHead.getNext().get();

      if (currHead.equals(currTail)) {
        if (currHeadNext == null) {
          return null;
        } else {
          tail.compareAndSet(currTail, currHeadNext);
        }
      } else {
        T result = currHeadNext.getValue();
        if (head.compareAndSet(currHead, currHeadNext)) {
          return result;
        }
      }
    }
  }
}
