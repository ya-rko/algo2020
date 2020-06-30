package algo.array;

public class MatrixArray<T> extends AbstractArray<T> implements Array<T> {
    private static final int DEFAULT_VECTOR = 100;
    private Array<Array<T>> array;
    private int size;
    private int vector;


    public MatrixArray() {
        this(DEFAULT_VECTOR);
    }


    public MatrixArray(int vector) {
        this.array = new FactorArray<>();
        this.vector = vector;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T item) {
        if (size() == array.size() * vector) {
            array.add(new VectorArray<>(vector));
        }

        final int index1 = size() / vector;
        array.get(index1).add(item);
        size++;
    }

    @Override
    public void add(T item, int index) {
        if (size() == array.size() * vector) {
            array.add(new VectorArray<>(vector));
        }

        final int arrayIndexInWhichAdd = index/vector;

        for (int i = array.size() - 1; i > arrayIndexInWhichAdd; i--) {
            Array<T> lastArray = array.get(i);
            Array<T> beforeLastArray = array.get(i - 1);

            final int firstIndex = 0;
            final int lastIndex = vector - 1;
            T lastItem = beforeLastArray.remove(lastIndex);
            lastArray.add(lastItem, firstIndex);
        }

        array.get(arrayIndexInWhichAdd).add(item, index % vector);
        size++;
    }

    @Override
    public T get(int index) {
        final int index1 = index / vector;
        final int index2 = index % vector;
        return array.get(index1).get(index2);
    }

    @Override
    public T remove(int index) {
        T item = get(index);

        final int arrayIndexInWhichRemove = index/vector;
        array.get(arrayIndexInWhichRemove).remove(index % vector);

        for (int i = arrayIndexInWhichRemove; i < array.size() - 1; i++) {
            Array<T> currentArray = array.get(i);
            Array<T> nextArray = array.get(i + 1);

            if (nextArray.size() > 0) {
                final int firstIndex = 0;
                final int lastIndex = vector - 1;
                T firstItem = nextArray.remove(firstIndex);
                currentArray.add(firstItem, lastIndex);
            }
        }
        size--;

        return item;
    }


    @Override
    public String toString() {
        return "MatrixArray{" +
                "vector=" + vector +
                '}';
    }
}
