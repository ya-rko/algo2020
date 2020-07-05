package method;

import sorts.MergeSort;
import sorts.QuickSort;
import util.Arrays;
import util.File;

import java.io.IOException;

public class SortFileCombineMethod {
    private static final int CHUNK_SIZE = 100;


    public static void sortFile(String fileName, int numberCount) throws IOException {
        int[] mergedChunk12 = sortAndMergeTwoChunks(fileName, 0, CHUNK_SIZE);
        File.saveNumbers("chunk12.out", mergedChunk12);

        for (int i = 2; i < numberCount / CHUNK_SIZE; i += 2) {
            mergedChunk12 = sortAndMergeTwoChunks(fileName, i * CHUNK_SIZE, (i + 1) * CHUNK_SIZE);

            int[] prevMergedChunk12 = File.readNumbers("chunk12.out", 0, i * CHUNK_SIZE);

            /*int[] mergedChunk_v1 = Arrays.mergeArrays(prevMergedChunk12, mergedChunk12);
            MergeSort.sort(mergedChunk_v1, 0, mergedChunk_v1.length - 1);
            File.saveNumbers("chunk12.out", mergedChunk_v1);*/

            int[] mergedChunk_v2 = MergeSort.mergeArrays(prevMergedChunk12, mergedChunk12);
            File.saveNumbers("chunk12.out", mergedChunk_v2);
        }
    }


    private static int[] sortAndMergeTwoChunks(String chunkSource, int posChunk1, int posChunk2) throws IOException {
        int[] chunk1 = readAndQuickSortChunk(chunkSource, posChunk1);
        int[] chunk2 = readAndQuickSortChunk(chunkSource, posChunk2);

        int[] mergedChunk12 = Arrays.mergeArrays(chunk1, chunk2);
        MergeSort.sort(mergedChunk12, 0, mergedChunk12.length - 1);

        return mergedChunk12;
    }


    private static int[] readAndQuickSortChunk(String chunkSource, int pos) throws IOException {
        int[] sortedChunk = File.readNumbers(chunkSource, pos, CHUNK_SIZE);
        QuickSort.sort(sortedChunk, 0, CHUNK_SIZE - 1);
        return sortedChunk;
    }
}
