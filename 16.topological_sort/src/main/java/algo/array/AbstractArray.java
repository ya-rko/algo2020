package algo.array;


public class AbstractArray<T> {
    T[] resize(T[] originalArray, int delta) {
        int newArraySize = originalArray.length + delta;
        T[] newArray = (T[]) new Object[newArraySize];

        int copyLength = (delta >= 0)
                         ? originalArray.length
                         : newArraySize;

        System.arraycopy(originalArray, 0, newArray, 0, copyLength);
        return newArray;
    }
}
