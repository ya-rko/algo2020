package algo.array;

public interface Array<T> {
    int size();
    void add(T item);
    void add(T item, int index);
    T get(int index);
    T remove(int index);
}
