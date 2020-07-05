package sorts;

public class CountingSort {

    public static void sort(int[] array, int maxElement) {
        int[] countArray = new int[maxElement];

        for (int element : array) {
            countArray[element]++;
        }

        int i = 0;
        for (int n = 0; n < maxElement; n++) {
            for (int c = 0; c < countArray[n]; c++) {
                array[i++] = n;
            }
        }
    }
}
