package algo.array;


public class FactorArray<T> extends AbstractArray<T> implements Array<T> {
    private T[] array;
    private int size;


    public FactorArray() {
        array = (T[]) new Object[0];
    }


    private int factor() {
        return size() == 0
               ? 1
               : size();
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public void add(T item) {
        if (size() == array.length) {
            array = resize(array, factor());
        }
        array[size] = item;
        size++;
    }


    @Override
    public void add(T item, int index) {
        T[] newArray = array;

        if (size() == array.length) {
            newArray = (T[]) new Object[size() + factor()];
            System.arraycopy(array, 0, newArray, 0, index);
        }

        System.arraycopy(array, index, newArray, index + 1, size() - index);
        if (array != newArray) {
            array = newArray;
        }

        array[index] = item;
        size++;
    }


    @Override
    public T get(int index) {
        return array[index];
    }


    public T set(int index, T value) {
        if (!checkBounds(index)) {
            throw new IndexOutOfBoundsException();
        }

        T oldValue = array[index];
        array[index] = value;
        return oldValue;
    }


    @Override
    public T remove(int index) {
        T item = array[index];
        System.arraycopy(array, index + 1, array, index, size() - index - 1);
        array[size - 1] = null;
        size--;
        return item;
    }


    private boolean checkBounds(int index) {
        return index >= 0 && index < size;
    }
}
