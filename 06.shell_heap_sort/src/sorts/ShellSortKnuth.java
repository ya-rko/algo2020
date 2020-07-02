package sorts;

// Gap sequence: Knuth, 1973

public class ShellSortKnuth implements IntSortFunction {

    @Override
    public void sort(int[] array) {
        int max = 1;
        while (max < array.length/3) {
            max = 3*max + 1;
        }

        for (int gap = max; gap >= 1; gap /= 3) {
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
