package sorts;

// Gap sequence: Marcin Ciura, 2001

public class ShellSortCiura implements IntSortFunction {
    private static final int[] GAP = {1, 4, 10, 23, 57, 132, 301, 701, 1750};

    @Override
    public void sort(int[] array) {
        for (int k = GAP.length - 1; k >= 0; k--) {
            int gap = GAP[k];
            for (int i = 0; i + gap < array.length; i++) {
                int j = i + gap;
                int tmp = array[j];

                while (j - gap >= 0 && array[j - gap] > tmp) {
                    array[j] = array[j - gap];
                    j -= gap;
                }

                array[j] = tmp;
            }
        }
    }
}
