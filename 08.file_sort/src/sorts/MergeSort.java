package sorts;

public class MergeSort {
    public static void sort(int[] array, int l, int r)
    {
        if (l < r) {
            int mid = (l + r) / 2;

            sort(array, l, mid);
            sort(array, mid + 1, r);

            merge(array, l, mid, r);
        }
    }


    private static void merge(int[] array, int l, int mid, int r)
    {
        int sizeL = mid - l + 1;
        int sizeR = r - mid;

        int[] tmpL = new int[sizeL];
        int[] tmpR = new int[sizeR];

        for (int i = 0; i < sizeL; i++) {
            tmpL[i] = array[l + i];
        }
        for (int j = 0; j < sizeR; j++) {
            tmpR[j] = array[mid + 1 + j];
        }

        int i = 0;
        int j = 0;

        int k = l;
        while (i < sizeL && j < sizeR) {
            if (tmpL[i] <= tmpR[j]) {
                array[k] = tmpL[i];
                i++;
            }
            else {
                array[k] = tmpR[j];
                j++;
            }
            k++;
        }

        while (i < sizeL) {
            array[k] = tmpL[i];
            i++;
            k++;
        }

        while (j < sizeR) {
            array[k] = tmpR[j];
            j++;
            k++;
        }
    }


    public static int[] mergeArrays(int[] orderedArray1, int[] orderedArray2)
    {
        int[] mergedArray = new int[orderedArray1.length + orderedArray2.length];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < orderedArray1.length && j < orderedArray2.length) {
            if (orderedArray1[i] <= orderedArray2[j]) {
                mergedArray[k] = orderedArray1[i];
                i++;
            }
            else {
                mergedArray[k] = orderedArray2[j];
                j++;
            }
            k++;
        }

        while (i < orderedArray1.length) {
            mergedArray[k] = orderedArray1[i];
            i++;
            k++;
        }

        while (j < orderedArray2.length) {
            mergedArray[k] = orderedArray2[j];
            j++;
            k++;
        }

        return mergedArray;
    }
}
