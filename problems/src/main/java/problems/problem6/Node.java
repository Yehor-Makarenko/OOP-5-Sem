package problems.problem6;

import java.util.concurrent.atomic.AtomicReference;

public class Node<T> {
  private T value;
  private AtomicReference<Node<T>> next;

  public Node(T value) {
    this.value = value;
    next = new AtomicReference<>(null);
  }

  public T getValue() {
    return value;
  }

  public AtomicReference<Node<T>> getNext() {
    return next;
  }
}
