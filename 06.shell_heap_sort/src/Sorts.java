import sorts.HeapSort;
import sorts.IntSortFunction;
import sorts.ShellSortCiura;
import sorts.ShellSortClassic;
import sorts.ShellSortKnuth;

import java.util.Random;


public class Sorts {
    public static void main(String[] args) {
        runSortTestRandomArray(1_000_000, 100);
        runSortTestAlmostOrderedArray(1_000_000, 100);
    }


    private static void runSortTestRandomArray(int arrayLength, int passCount) {
        long test1_DurationSum = 0;
        long test2_DurationSum = 0;
        long test3_DurationSum = 0;
        long test4_DurationSum = 0;

        for (int c = 1; c <= passCount; c++) {
            int[] testArrayOriginal = createRandomTestArray(arrayLength);
            int[] testArray = new int[testArrayOriginal.length];

            System.arraycopy(testArrayOriginal,0, testArray, 0, testArray.length);
            test1_DurationSum += sortTest("ShellSort (classic version)", testArray, new ShellSortClassic());

            System.arraycopy(testArrayOriginal,0, testArray, 0, testArray.length);
            test2_DurationSum += sortTest("ShellSort (Knuth version)", testArray, new ShellSortKnuth());

            System.arraycopy(testArrayOriginal,0, testArray, 0, testArray.length);
            test3_DurationSum += sortTest("ShellSort (Marcin Ciura version)", testArray, new ShellSortCiura());

            System.arraycopy(testArrayOriginal,0, testArray, 0, testArray.length);
            test4_DurationSum += sortTest("HeapSort", testArray, new HeapSort());
        }

        System.out.println("\nRandom Array. Total result" + " [count: " + arrayLength + "]");
        System.out.println("ShellSort (classic version) average sort duration: " + test1_DurationSum / passCount + " ms");
        System.out.println("ShellSort (Knuth version) average sort duration: " + test2_DurationSum / passCount + " ms");
        System.out.println("ShellSort (Marcin Ciura version) average sort duration: " + test3_DurationSum / passCount + " ms");
        System.out.println("HeapSort average sort duration: " + test4_DurationSum / passCount + " ms");
    }


    private static void runSortTestAlmostOrderedArray(int arrayLength, int passCount) {
        long test1_DurationSum = 0;
        long test2_DurationSum = 0;
        long test3_DurationSum = 0;
        long test4_DurationSum = 0;

        for (int c = 1; c <= passCount; c++) {
            int[] testArrayOriginal = createAlmostOrderedTestArray(arrayLength);
            int[] testArray = new int[testArrayOriginal.length];

            System.arraycopy(testArrayOriginal,0, testArray, 0, testArray.length);
            test1_DurationSum += sortTest("ShellSort (classic version)", testArray, new ShellSortClassic());

            System.arraycopy(testArrayOriginal,0, testArray, 0, testArray.length);
            test2_DurationSum += sortTest("ShellSort (Knuth version)", testArray, new ShellSortKnuth());

            System.arraycopy(testArrayOriginal,0, testArray, 0, testArray.length);
            test3_DurationSum += sortTest("ShellSort (Marcin Ciura version)", testArray, new ShellSortCiura());

            System.arraycopy(testArrayOriginal,0, testArray, 0, testArray.length);
            test4_DurationSum += sortTest("HeapSort", testArray, new HeapSort());
        }

        System.out.println("\nAlmost Ordered Array. Total result" + " [count: " + arrayLength + "]");
        System.out.println("ShellSort (classic version) average sort duration: " + test1_DurationSum / passCount + " ms");
        System.out.println("ShellSort (Knuth version) average sort duration: " + test2_DurationSum / passCount + " ms");
        System.out.println("ShellSort (Marcin Ciura version) average sort duration: " + test3_DurationSum / passCount + " ms");
        System.out.println("HeapSort average sort duration: " + test4_DurationSum / passCount + " ms");
    }


    private static long sortTest(String description, int[] array, IntSortFunction sortFunction) {
        //System.out.println(description + " [count: " + array.length + "]");

        long startTime = System.currentTimeMillis();
        //long startTime = System.nanoTime();
        sortFunction.sort(array);
        //long endTime = System.nanoTime();
        long endTime = System.currentTimeMillis();

        long duration = endTime - startTime;
        //System.out.println("Sort duration: " + duration + " ms");
        return duration;
    }


    private static int[] createRandomTestArray(int count) {
        int[] array = new int[count];
        for (int i = 0; i < count; i++) {
            array[i] = new Random().nextInt(array.length * 10);
        }

        return array;
    }


    private static int[] createAlmostOrderedTestArray(int count) {
        int[] array = new int[count];
        for (int i = 0; i < count; i++) {
            array[i] = i;
        }

        for (int j = 0; j < count * 0.05; j++) {
            int index1 = new Random().nextInt(array.length);
            int index2 = new Random().nextInt(array.length);

            if (index1 != index2) {
                int tmp = array[index1];
                array[index1] = array[index2];
                array[index2] = tmp;
            }
        }

        return array;
    }
}