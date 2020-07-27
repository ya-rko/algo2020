package algo.priorityqueue;


import algo.array.FactorArray;

import java.util.EmptyStackException;


public class PriorityQueueImpl<T> implements PriorityQueue<T> {
    /** Храним по два значения для каждого массива элементов:
     *  приоритет и кол-во элементав с этим приоритетом
     */
    FactorArray<Integer> prioritiesAndCount;
    FactorArray<FactorArray<T>> priorityArray;


    public PriorityQueueImpl() {
        prioritiesAndCount = new FactorArray<>();
        priorityArray = new FactorArray<>();
    }

    @Override
    public void enqueue(int priority, T item) {
        int priorityArrayIndex = getPriorityArrayIndex(priority);
        if (priorityArrayIndex == -1) {
            final int count = 0;
            priorityArrayIndex = prioritiesAndCount.size() / 2;
            prioritiesAndCount.add(priority);
            prioritiesAndCount.add(count);
            priorityArray.add(new FactorArray<>());
        }

        priorityArray.get(priorityArrayIndex).add(item);
        incrementCount(priorityArrayIndex);
    }

    @Override
    public T dequeue() {
        final int maxPriorityArrayIndex = getMaxPriorityArrayIndex();
        if (maxPriorityArrayIndex == -1) {
            throw new EmptyStackException();
        }

        FactorArray<T> array = priorityArray.get(maxPriorityArrayIndex);
        T item = array.remove(0);
        decrementCount(maxPriorityArrayIndex);

        return item;
    }


    private int getPriorityArrayIndex(int priority) {
        int priorityArrayIndex = -1;
        for (int i = 0; i < prioritiesAndCount.size(); i += 2) {
            if (prioritiesAndCount.get(i) == priority) {
                priorityArrayIndex = i / 2;
                break;
            }
        }
        return priorityArrayIndex;
    }

    private int getMaxPriorityArrayIndex() {
        int maxPriorityArrayIndex = -1;
        int maxPriority = -1;
        for (int i = 0; i < prioritiesAndCount.size(); i += 2) {
            final int count = prioritiesAndCount.get(i + 1);
            if (count == 0) {
                continue;
            }

            final int priority = prioritiesAndCount.get(i);
            if (priority > maxPriority) {
                maxPriority = priority;
                maxPriorityArrayIndex = i / 2;
            }
        }
        return maxPriorityArrayIndex;
    }


    private void incrementCount(int priorityArrayIndex) {
        int countIndex = 2 * priorityArrayIndex + 1;
        Integer count = prioritiesAndCount.get(countIndex);
        prioritiesAndCount.set(countIndex, ++count);
    }


    private void decrementCount(int priorityArrayIndex) {
        int countIndex = 2 * priorityArrayIndex + 1;
        Integer count = prioritiesAndCount.get(countIndex);
        if (count > 0) {
            prioritiesAndCount.set(countIndex, --count);
        }
    }
}

