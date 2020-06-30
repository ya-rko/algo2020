package algo.priorityqueue;

public interface PriorityQueue<T> {
    void enqueue(int priority, T item);
    T dequeue();
}
