package sorts;

public class HeapSort implements IntSortFunction {
    int[] array;

    @Override
    public void sort(int[] inputArray) {
        this.array = inputArray;

        for (int i = this.array.length / 2 - 1; i >= 0; i--) {
            Down(this.array.length, i);
        }

        for (int i = this.array.length - 1; i >= 0; i--) {
            Swap(0, i);
            Down(i, 0);
        }
    }


    private void Down(int size, int root) {
        int L = 2 * root + 1;
        int R = L  + 1;
        int maxElement = root;
        if (L < size && array[L] > array[maxElement]) {
            maxElement = L;
        }
        if (R < size && array[R] > array[maxElement]) {
            maxElement = R;
        }

        if (maxElement == root) {
            return;
        }

        Swap(root, maxElement);
        Down(size, maxElement);
    }


    private void Swap(int a, int b) {
        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }
}