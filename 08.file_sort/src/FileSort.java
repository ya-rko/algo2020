import method.SortFileCombineMethod;
import method.SortFileMergeMethod;
import sorts.CountingSort;
import util.File;

import java.io.IOException;


public class FileSort {
    private static final String FILE_NAME = "numbers.bin";
    private static final int NUMBER_COUNT = 1_000_000;
    private static final int MAX_NUMBER = 65535;


    public static void main(String[] args) throws IOException {
        File.createGBFile(FILE_NAME, NUMBER_COUNT);
        runSortFileMergeMethod();
        runSortFileCombineMethod();
        runSortFileLinearMethod();
    }


    private static void runSortFileMergeMethod() throws IOException {
        long startTime = System.currentTimeMillis();
        SortFileMergeMethod.sortFile(FILE_NAME, NUMBER_COUNT);
        long endTime = System.currentTimeMillis();

        System.out.println("Merge Method Sort duration: " + (endTime - startTime) + " ms");
    }


    private static void runSortFileCombineMethod() throws IOException {
        long startTime = System.currentTimeMillis();
        SortFileCombineMethod.sortFile(FILE_NAME, NUMBER_COUNT);
        long endTime = System.currentTimeMillis();

        System.out.println("Combine Method Sort duration: " + (endTime - startTime) + " ms");
    }


    private static void runSortFileLinearMethod() throws IOException {
        int[] numbers = File.readNumbers(FILE_NAME, 0, NUMBER_COUNT);

        long startTime = System.currentTimeMillis();
        CountingSort.sort(numbers, MAX_NUMBER);
        long endTime = System.currentTimeMillis();

        File.saveNumbers("ordered_numbers.bin", numbers);

        System.out.println("Linear Method Sort duration: " + (endTime - startTime) + " ms");
    }
}