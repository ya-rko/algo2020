package algo.priorityqueue;

import algo.array.FactorArray;

import java.util.EmptyStackException;

public class PriorityQueueImpl<T> implements PriorityQueue<T> {
    FactorArray<FactorArray<T>> priorityArray;


    public PriorityQueueImpl() {
        priorityArray = new FactorArray<>();
        priorityArray.add(new FactorArray<>());
    }

    @Override
    public void enqueue(int priority, T item) {
        FactorArray<T> array = null;

        if (priority <= priorityArray.size()) {
            array = priorityArray.get(priority - 1);
        }

        if (priority > priorityArray.size()) {
            // Массив приоритетов не делаем разряженным
            for (int i = priorityArray.size(); i < priority; i++) {
                priorityArray.add(new FactorArray<>(), i);
            }
        }

        priorityArray.get(priority - 1).add(item);
    }

    @Override
    public T dequeue() {
        for (int i = priorityArray.size() - 1; i >= 0; i--) {
            FactorArray<T> array = priorityArray.get(i);

            if (array == null || array.size() == 0) {
                continue;
            }

            return array.remove(0);
        }
        throw new EmptyStackException();
    }
}
