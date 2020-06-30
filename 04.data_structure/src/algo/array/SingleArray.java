package algo.array;


public class SingleArray<T> extends AbstractArray<T> implements Array<T> {
    private T[] array;


    public SingleArray() {
        this.array = (T[]) new Object[0];
    }


    @Override
    public int size() {
        return array.length;
    }


    @Override
    public void add(T item) {
        array = resize(array, 1);
        array[size() - 1] = item;
    }


    @Override
    public void add(T item, int index) {
        T[] newArray = (T[]) new Object[size() + 1];
        System.arraycopy(array, 0, newArray, 0, index);
        newArray[index] = item;
        System.arraycopy(array, index, newArray, index + 1, size() - index);
        array = newArray;
    }


    @Override
    public T get(int index) {
        return array[index];
    }


    @Override
    public T remove(int index) {
        T item = array[index];
        System.arraycopy(array, index + 1, array, index, size() - index - 1);
        array = resize(array, -1);
        return item;
    }
}
