package algo.array;


public class VectorArray<T> extends AbstractArray<T> implements Array<T> {
    private static final int DEFAULT_VECTOR = 100;
    private T[] array;
    private int vector;
    private int size;


    public VectorArray() {
        this(DEFAULT_VECTOR);
    }


    public VectorArray(int vector) {
        array = (T[]) new Object[0];
        this.vector = vector;
    }


    @Override
    public int size() {
        return size;
    }


    @Override
    public void add(T item) {
        if (size() == array.length) {
            array = resize(array, vector);
        }
        array[size] = item;
        size++;
    }


    @Override
    public void add(T item, int index) {
        T[] newArray = array;

        if (size() == array.length) {
            newArray = (T[]) new Object[size() + vector];
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


    @Override
    public T remove(int index) {
        T item = array[index];
        System.arraycopy(array, index + 1, array, index, size() - index - 1);
        array[size - 1] = null;
        size--;
        return item;
    }


    @Override
    public String toString() {
        return "VectorArray{" +
                "vector=" + vector +
                '}';
    }
}
