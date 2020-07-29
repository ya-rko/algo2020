package algo;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.io.File;
import java.util.Scanner;


public class BloomFilterMain {
    public static void main(String[] args) throws Exception {
        final int expectedInsertions = 1_100_000;
        final double falsePositiveProbability = 0.01;

        BloomFilter<String> bloomFilter =
                BloomFilter.create(
                        Funnels.unencodedCharsFunnel(),
                        expectedInsertions,
                        falsePositiveProbability);

        Scanner scanner1 = new Scanner(new File("testdata\\open-images-dataset-train0.tsv"));
        scanner1.nextLine(); // skip head

        int actualInsertions = 0;
        while (scanner1.hasNext()) {
            String url = scanner1.nextLine().split("\t")[0];
            bloomFilter.put(url);
            actualInsertions++;
        }
        scanner1.close();

        int processedElementCount = 0;
        int actualFalsePositiveCount = 0;
        Scanner scanner2 = new Scanner(new File("testdata\\open-images-dataset-train1.tsv"));
        scanner2.nextLine(); // skip head
        while (scanner2.hasNext()) {
            String url = scanner2.nextLine().split("\t")[0];
            if (bloomFilter.mightContain(url)) {
                actualFalsePositiveCount++;
            }
            processedElementCount++;
        }
        scanner2.close();

        System.out.println("Expected element insertions count: " + expectedInsertions);
        System.out.println("Actual element count: " + actualInsertions);
        long optimalNumOfBits = optimalNumOfBits(expectedInsertions, falsePositiveProbability);
        System.out.println("Optimal number of bits: " + optimalNumOfBits);
        System.out.println("Optimal number of hash functions: " + optimalNumOfHashFunctions(expectedInsertions, optimalNumOfBits));
        System.out.println("Expected false positive probability: " + bloomFilter.expectedFpp());
        System.out.println("Actual false positive count: " + actualFalsePositiveCount);
        System.out.println("Actual false positive probability: " + (double) actualFalsePositiveCount / processedElementCount);
    }


    private static long optimalNumOfBits(long n, double p) {
        return (long)((double)(-n) * Math.log(p) / (Math.log(2.0D) * Math.log(2.0D)));
    }


    static int optimalNumOfHashFunctions(long n, long m) {
        return Math.max(1, (int)Math.round((double)m / (double)n * Math.log(2.0D)));
    }
}