package algo.algebra.primenumber;


public class PrimeNumberSieve extends PrimeNumber {
    private long[] createNumberArray(int maxNumber) {
        long[] numbers = new long[maxNumber+1];

        for (int i = 2; i < maxNumber; i++) {
            numbers[i] = i;
        }
        return numbers;
    }


    public long count(int maxNumber) {
        long count = 0;
        long[] numbers = createNumberArray(maxNumber);

        int i = 1;
        while (Math.pow(i++, 2) < numbers.length) {
            if (numbers[i] == -1) {
                continue;
            }

            long currentNum = numbers[i];
            for (int j = (int)Math.pow(currentNum, 2); j < numbers.length; j += currentNum) {
                numbers[j] = -1;
            }
        }

        for (int k = 2; k < maxNumber; k++) {
            if (numbers[k] != -1) {
                count++;
            }
        }

        return count;
    }


    @Override
    boolean isNumberPrime(long maxNumber) {
        return false;
    }


    @Override
    public String getDescription() {
        return "Prime numbers count: sieve of Eratosthenes";
    }
}
